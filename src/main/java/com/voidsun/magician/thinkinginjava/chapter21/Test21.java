package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/20.
 */
public class Test21 {
    static class Task1 implements Runnable{
        @Override
        public void run() {
            try {
                synchronized (this){
                    System.out.println("task1 run");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task1 wait");
                    wait();
                    System.out.println("task1 resume");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Task2 implements Runnable{
        final private Task1 task1;

        public Task2(Task1 task1){
            this.task1 = task1;
        }

        @Override
        public void run() {
            synchronized (task1) {
                System.out.println("task2 run");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task2 notify task1");
                task1.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2(task1);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task1);
        service.execute(task2);
        service.shutdown();
    }
}
