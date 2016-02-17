package com.voidsun.magician.thinkinginjava.chapter21;

/**
 * Created by voidsun on 16/2/17.
 */
public class Test15 {
    Integer i = new Integer(1);

    public void test1(){
        synchronized (i){
            for(int j=0; j<10; j++){
                System.out.println("11");
                Thread.yield();
            }
        }
    }

    public void test2(){
        //synchronized (this){
        synchronized (i){
            for(int j=0; j<10; j++){
                System.out.println("22");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        final Test15 test15 = new Test15();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test15.test1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test15.test2();
            }
        }).start();
    }
}
