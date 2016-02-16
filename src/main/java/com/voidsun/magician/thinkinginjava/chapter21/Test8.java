package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/16.
 */
public class Test8 implements Runnable{

    @Override
    public void run() {
        while(true){
            try {
                System.out.println("test8 thread running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Test8());
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(2);
        /**
         * Exception in thread "main" java.lang.IllegalThreadStateException
         *   at java.lang.Thread.setDaemon(Thread.java:1352)
         */
        //t.setDaemon(true);

    }
}
