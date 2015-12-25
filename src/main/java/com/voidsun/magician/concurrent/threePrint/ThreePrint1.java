package com.voidsun.magician.concurrent.threePrint;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/12/25
 * @Email voidsun@126.com
 */
public class ThreePrint1 {

    public static void main(String[] args) {
        Mutex mutex = new Mutex(3);
        Print printA = new Print(mutex, 1, 'A');
        Print printB = new Print(mutex, 2, 'B');
        Print printC = new Print(mutex, 3, 'C');
        ExecutorService executable = Executors.newFixedThreadPool(3);
        executable.execute(printA);
        executable.execute(printB);
        executable.execute(printC);
    }

    public static class Mutex{
        private int current = 1;
        private int max;
        public Mutex(int max){
            this.max = max;
        }
        public int getCurrent(){
            return this.current;
        }
        public void release(){
            this.current = current == max?1:++current;
        }
    }
    public static class Print implements Runnable{
        int start = 10;
        int id;
        Mutex mutex;
        char printChar;

        public Print(Mutex mutex, int id, char printChar){
            this.id = id;
            this.mutex = mutex;
            this.printChar = printChar;
        }

        @Override
        public void run() {
            while(start --> 0){
                try {
                    synchronized (mutex) {
                        while (mutex.getCurrent() != this.id)
                            mutex.wait();
                        System.out.println(printChar);
                        mutex.release();
                        mutex.notifyAll();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
