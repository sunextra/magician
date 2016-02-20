package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * Lock显示锁可以被interrupt中断
 * Created by voidsun on 16/2/19.
 */
public class Interrupting2 {

    static class Mutex implements Runnable{
        Lock lock = new ReentrantLock();
        public void lock(){
            lock.lock();
        }
        public void run(){
            try {
                System.out.println("trying lock");
                lock.lockInterruptibly();
                System.out.println("getting lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Mutex mutex = new Mutex();
        mutex.lock();
        Thread t = new Thread(mutex);
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("after 1 second");
        t.interrupt();
    }

}
