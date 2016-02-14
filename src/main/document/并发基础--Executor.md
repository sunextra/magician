#java并发基础--Executor

一般来说,java5以后应该使用Executor来管理Thread了,常见的几个Executor构造如下
Executors.newCachedThreadPool()
Executors.newFixedThreadPool(3)
Executors.newSingleThreadExecutor()
从实现上来看,最终都是构造了一个ThreadPoolExecutor,single执行器稍微特殊一些,在外面会在包装一次

    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()));
    }
从FinalizableDelegatedExecutorService和DelegatedExecutorService的注释来看
    
    * {@code newFixedThreadPool(1)} the returned executor is
    * guaranteed not to be reconfigurable to use additional threads.
      
    * A wrapper class that exposes only the ExecutorService methods
    * of an ExecutorService implementation.
就是封闭了修改保持线程数的方法,DelegatedExecutorService上做了一个简单的包装,就酱.
所以其实还是看ThreadPoolExecutor

    ThreadPoolExecutor(int corePoolSize,
        int maximumPoolSize,
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue,
        ThreadFactory threadFactory,
        RejectedExecutionHandler handler)
        
corePoolSize可以认为是minPoolSize,cachedPool这个值为0,fixed则依赖入参,single在各种参数上和fix是一致的,不说了.

对应的max值,cached是Integer.Max,fixed和corePoolSize一致.

当执行器维护的线程数>corePoolSize时,如果有线程处于空闲状态,keepAliveTime和unit决定了最大的等待时间.所以fixed肯定是不需要这个值的,对于cached这个值为60S

workQueue阻塞队列,队列暂存的任务.通过使用不同的实现cachedPool和fixedPool表现出不同的特性.
从void execute(Runnable command)的实现上看,executor遵循以下策略执行一个任务:
1 当前的线程数小于corePoolSize,不队列直接尝试创建Thread执行.
2 如果无法立即执行,尝试队列任务.
3 如果队列失败,判断是否达到了maxPoolSize,如未达到则新开一个线程执行,否则reject.


