# java动态代理
本文主要记录对常见的几种java代理源码进行的分析。
## jdk动态代理
jdk代理比较简单，主要关注如何生成动态代理对象。

#### java.lang.reflect.Proxy
    private static final Class<?>[] constructorParams =
            { InvocationHandler.class };

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) throws IllegalArgumentException
    {
        Objects.requireNonNull(h);

        //mark1 为什么要clone一份
        final Class<?>[] intfs = interfaces.clone();
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }

        /*
         * core:从缓存中查找代理类，如果不存在在这里创建
         */
        Class<?> cl = getProxyClass0(loader, intfs);


        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
            //mark2 创造构造方法，为什么需要constructorParams
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            //mark2 目测是改变非public接口的包访问权限，为什么要这样
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
简单过一下，有3个问题，这个并不是主线，先来看如何创建代理类的
#### java.lang.reflect.Proxy
    //mark4 关注KeyFactory, ProxyClassFactory
    private static final WeakCache<ClassLoader, Class<?>[], Class<?>>
            proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());

    private static Class<?> getProxyClass0(ClassLoader loader, Class<?>... interfaces) {
        //mark5 65535是限制什么的
        if (interfaces.length > 65535) {
            throw new IllegalArgumentException("interface limit exceeded");
        }

        return proxyClassCache.get(loader, interfaces);
    }

去看cache.get的实现
#### java.lang.reflect.WeakCache
    public V get(K key, P parameter) {
        Objects.requireNonNull(parameter);
        expungeStaleEntries();
        //计算cacheKey，这里可以看出来用的是一个二级的缓存，cacheKey->ConcurrentHashMap
        Object cacheKey = CacheKey.valueOf(key, refQueue);

        ConcurrentMap<Object, Supplier<V>> valuesMap = map.get(cacheKey);
        if (valuesMap == null) {
            ConcurrentMap<Object, Supplier<V>> oldValuesMap
                = map.putIfAbsent(cacheKey, valuesMap = new ConcurrentHashMap<>());
            if (oldValuesMap != null) {
                valuesMap = oldValuesMap;
            }
        }
        //计算subKey
        Object subKey = Objects.requireNonNull(subKeyFactory.apply(key, parameter));
        Supplier<V> supplier = valuesMap.get(subKey);
        Factory factory = null;

        while (true) {
            if (supplier != null) {
                //core缓存命中，supplier.get()是出参,mark6 supplier是什么
                V value = supplier.get();
                if (value != null) {
                    return value;
                }
            }

            if (factory == null) {
                factory = new Factory(key, parameter, subKey, valuesMap);
            }

            //mark1 如果supplier不存在，用上面创建的factory替换，这里解答mark6
            if (supplier == null) {
                supplier = valuesMap.putIfAbsent(subKey, factory);
                if (supplier == null) {
                    supplier = factory;
                }
            } else {
                if (valuesMap.replace(subKey, supplier, factory)) {
                    supplier = factory;
                } else {
                    supplier = valuesMap.get(subKey);
                }
            }
        }
    }

继续关注factory.get
#### java.lang.reflect.WeakCache.Factory
    private final ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map = new ConcurrentHashMap<>();
    private final BiFunction<K, P, V> valueFactory;

    public synchronized V get() {
        //valuesMap的值在mark4里设置的 mark7 这个校验是在做什么
        Supplier<V> supplier = valuesMap.get(subKey);
        if (supplier != this) {
            return null;
        }

        V value = null;
        try {
            //core 关注valueFactory
            value = Objects.requireNonNull(valueFactory.apply(key, parameter));
        } finally {
            if (value == null) {
                valuesMap.remove(subKey, this);
            }
        }
        assert value != null;

        CacheValue<V> cacheValue = new CacheValue<>(value);
        //mark8 关注一下这个replace的含义
        if (valuesMap.replace(subKey, this, cacheValue)) {
            reverseMap.put(cacheValue, Boolean.TRUE);
        } else {
            throw new AssertionError("Should not reach here");
        }

        return value;
    }
继续关注BiFunction.apply BiFunction有两个实现KeyFactory和ProxyClassFactory，参考Q4，这里使用的是ProxyClassFactory
#### java.lang.reflect.Proxy.ProxyClassFactory
    private static final class ProxyClassFactory implements BiFunction<ClassLoader, Class<?>[], Class<?>>
    {

        private static final String proxyClassNamePrefix = "$Proxy";
        private static final AtomicLong nextUniqueNumber = new AtomicLong();

        @Override
        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {

            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            //mark9 关注为何要做这里面的校验
            for (Class<?> intf : interfaces) {
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(
                        intf + " is not visible from class loader");
                }

                if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(
                        interfaceClass.getName() + " is not an interface");
                }
                if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException(
                        "repeated interface: " + interfaceClass.getName());
                }
            }

            String proxyPkg = null;
            //mark10 这是干啥
            int accessFlags = Modifier.PUBLIC | Modifier.FINAL;

            //简单来说 就是在拼包路径的名字
            for (Class<?> intf : interfaces) {
                int flags = intf.getModifiers();
                if (!Modifier.isPublic(flags)) {
                    accessFlags = Modifier.FINAL;
                    String name = intf.getName();
                    int n = name.lastIndexOf('.');
                    String pkg = ((n == -1) ? "" : name.substring(0, n + 1));
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException(
                            "non-public interfaces from different packages");
                    }
                }
            }
            //实际更多的会走这个分支，直接建在PROXY_PACKAGE下
            if (proxyPkg == null) {
                proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
            }
            long num = nextUniqueNumber.getAndIncrement();
            String proxyName = proxyPkg + proxyClassNamePrefix + num;
            //core 接下来两部分 mark11:generateProxyClass，mark12:defineClass0
            byte[] proxyClassFile = ProxyGenerator.generateProxyClass(
                proxyName, interfaces, accessFlags);
            try {
                return defineClass0(loader, proxyName, proxyClassFile, 0, proxyClassFile.length);
            } catch (ClassFormatError e) {
                throw new IllegalArgumentException(e.toString());
            }
        }
    }
mark11,创建代理类文件 这里开始是sun包下的东西 已经没有源码了
#### sun.misc.ProxyGenerator
    private static final boolean saveGeneratedFiles = ((Boolean)AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"))).booleanValue();

    public static byte[] generateProxyClass(final String var0, Class<?>[] var1, int var2) {
        ProxyGenerator var3 = new ProxyGenerator(var0, var1, var2);
        //core
        final byte[] var4 = var3.generateClassFile();
        //mark12这里可以确定，代理类是否保存在硬盘上由sun.misc.ProxyGenerator.saveGeneratedFiles(win系统)决定，如果没有显示配置应该是false，这里关注下文件路径
        if(saveGeneratedFiles) {
            AccessController.doPrivileged(new PrivilegedAction() {
                public Void run() {
                    try {
                        int var1 = var0.lastIndexOf(46);
                        Path var2;
                        if(var1 > 0) {
                            Path var3 = Paths.get(var0.substring(0, var1).replace('.', File.separatorChar), new String[0]);
                            Files.createDirectories(var3, new FileAttribute[0]);
                            var2 = var3.resolve(var0.substring(var1 + 1, var0.length()) + ".class");
                        } else {
                            var2 = Paths.get(var0 + ".class", new String[0]);
                        }

                        Files.write(var2, var4, new OpenOption[0]);
                        return null;
                    } catch (IOException var4x) {
                        throw new InternalError("I/O exception saving generated file: " + var4x);
                    }
                }
            });
        }

        return var4;
    }

#### sun.misc.ProxyGenerator
    private byte[] generateClassFile() {
        //添加object的三个方法 mark13 关注addProxyMethod，另外所有的类都继承Object是一个编译时行为还是运行时行为？
        this.addProxyMethod(hashCodeMethod, Object.class);
        this.addProxyMethod(equalsMethod, Object.class);
        this.addProxyMethod(toStringMethod, Object.class);

        //把所有interface的方法添加进来
        Class[] var1 = this.interfaces;
        int var2 = var1.length;
        int var3;
        Class var4;
        for(var3 = 0; var3 < var2; ++var3) {
            var4 = var1[var3];
            Method[] var5 = var4.getMethods();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Method var8 = var5[var7];
                this.addProxyMethod(var8, var4);
            }
        }

        Iterator var11 = this.proxyMethods.values().iterator();

        List var12;
        while(var11.hasNext()) {
            var12 = (List)var11.next();
            checkReturnTypes(var12);
        }

        Iterator var15;
        try {
            this.methods.add(this.generateConstructor());
            var11 = this.proxyMethods.values().iterator();

            while(var11.hasNext()) {
                var12 = (List)var11.next();
                var15 = var12.iterator();

                while(var15.hasNext()) {
                    ProxyGenerator.ProxyMethod var16 = (ProxyGenerator.ProxyMethod)var15.next();
                    this.fields.add(new ProxyGenerator.FieldInfo(var16.methodFieldName, "Ljava/lang/reflect/Method;", 10));
                    this.methods.add(var16.generateMethod());
                }
            }

            this.methods.add(this.generateStaticInitializer());
        } catch (IOException var10) {
            throw new InternalError("unexpected I/O Exception", var10);
        }

        if(this.methods.size() > '\uffff') {
            throw new IllegalArgumentException("method limit exceeded");
        } else if(this.fields.size() > '\uffff') {
            throw new IllegalArgumentException("field limit exceeded");
        } else {
            this.cp.getClass(dotToSlash(this.className));
            this.cp.getClass("java/lang/reflect/Proxy");
            var1 = this.interfaces;
            var2 = var1.length;

            for(var3 = 0; var3 < var2; ++var3) {
                var4 = var1[var3];
                this.cp.getClass(dotToSlash(var4.getName()));
            }

            this.cp.setReadOnly();
            ByteArrayOutputStream var13 = new ByteArrayOutputStream();
            DataOutputStream var14 = new DataOutputStream(var13);

            try {
                var14.writeInt(-889275714);
                var14.writeShort(0);
                var14.writeShort(49);
                this.cp.write(var14);
                var14.writeShort(this.accessFlags);
                var14.writeShort(this.cp.getClass(dotToSlash(this.className)));
                var14.writeShort(this.cp.getClass("java/lang/reflect/Proxy"));
                var14.writeShort(this.interfaces.length);
                Class[] var17 = this.interfaces;
                int var18 = var17.length;

                for(int var19 = 0; var19 < var18; ++var19) {
                    Class var22 = var17[var19];
                    var14.writeShort(this.cp.getClass(dotToSlash(var22.getName())));
                }

                var14.writeShort(this.fields.size());
                var15 = this.fields.iterator();

                while(var15.hasNext()) {
                    ProxyGenerator.FieldInfo var20 = (ProxyGenerator.FieldInfo)var15.next();
                    var20.write(var14);
                }

                var14.writeShort(this.methods.size());
                var15 = this.methods.iterator();

                while(var15.hasNext()) {
                    ProxyGenerator.MethodInfo var21 = (ProxyGenerator.MethodInfo)var15.next();
                    var21.write(var14);
                }

                var14.writeShort(0);
                return var13.toByteArray();
            } catch (IOException var9) {
                throw new InternalError("unexpected I/O Exception", var9);
            }
        }
    }