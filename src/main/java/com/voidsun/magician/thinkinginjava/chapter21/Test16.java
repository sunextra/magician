package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by voidsun on 16/2/18.
 */
public class Test16 {
    Integer i = new Integer(1);
    Lock lock2 = new ReentrantLock();

    public void test1(){
        Lock lock = new ReentrantLock();
        //lock.lock();
        lock2.lock();
        try{
            for(int j=0; j<10; j++){
                System.out.println("11");
                Thread.yield();
            }
        }finally {
            //lock.unlock();
            lock2.unlock();
        }
    }

    public void test2(){
        Lock lock = new ReentrantLock();
        //lock.lock();
        lock2.lock();
        try{
            for(int j=0; j<10; j++){
                System.out.println("22");
                Thread.yield();
            }
        }finally {
            //lock.unlock();
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
        final Test16 test16 = new Test16();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test16.test1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test16.test2();
            }
        }).start();
    }
}
