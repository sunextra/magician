package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/19.
 */
public class Test18{
    static class Task{
        public void execute() throws InterruptedException {
            while(true) {
                System.out.println("do something");
                TimeUnit.SECONDS.sleep(100);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                new Task().execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}

