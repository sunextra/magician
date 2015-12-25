package com.voidsun.magician.java.dynamic.jdk;

import sun.reflect.misc.ReflectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/11/30
 * @Email voidsun@126.com
 */
public class MyProxy {
    private static final Class<?>[] constructorParams =
            { InvocationHandler.class };

    private static final AtomicLong nextUniqueNumber = new AtomicLong();
    private static final String proxyClassNamePrefix = "$Proxy";

    private static Class<?> apply(ClassLoader loader, Class<?>[] interfaces){
        String proxyPkg = null;
        int accessFlags = Modifier.PUBLIC | Modifier.FINAL;
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
        if (proxyPkg == null) {
            proxyPkg = ReflectUtil.PROXY_PACKAGE + ".";
        }
        long num = nextUniqueNumber.getAndIncrement();
        String proxyName = proxyPkg + proxyClassNamePrefix + num;
        return null;
    }

    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h){
        Class<?> cl = apply(loader, interfaces);
        try {
            Constructor<?> cons = cl.getConstructor(constructorParams);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
