package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/20.
 */
public class Test23 {
    static class Modify implements Runnable{
        public static volatile boolean flag = false;
        @Override
        public void run() {
            while(true) {
                synchronized (this) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = true;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class LoopNotify implements Runnable{
        private long waitCost;
        private long trueCount;
        private Modify modify;
        public LoopNotify(Modify modify){
            this.modify = modify;
        }
        @Override
        public void run() {
            Long start = System.currentTimeMillis();
            while(true){
                if(!Modify.flag){
                    continue;
                }
                synchronized (modify){
                    Long end = System.currentTimeMillis();
                    waitCost += end-start;
                    Modify.flag = false;
                    trueCount ++;
                    System.out.println("count ["+trueCount+"] cost ["+waitCost+"]");
                    modify.notify();
                    start = System.currentTimeMillis();
                }
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Modify modify = new Modify();
        service.execute(modify);
        service.execute(new LoopNotify(modify));
    }
}
