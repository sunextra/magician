package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/16.
 */
public class Test9 implements Runnable{
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("test9 thread running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        executor.execute(new Test9());
        TimeUnit.SECONDS.sleep(3);//will shutdown after 3 seconds

        /* never shut down
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Test9());
        executorService.shutdown();*/
    }
}

class DaemonThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
