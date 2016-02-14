package com.voidsun.magician.thinkinginjava.chapter21;

/**
 * Created by voidsun on 16/2/14.
 */
public class Test1 implements Runnable{
    final private int id;
    private int times = 3;

    @Override
    public String toString() {
        return "["+id+"]";
    }

    public Test1(int id){
        this.id = id;
        System.out.println(this + " started");
    }

    @Override
    public void run() {
        while(times --> 0){
            System.out.println(this + " running " + (3-times));
            Thread.yield();
        }
        System.out.println(this + " stopped");
    }

    public static void main(String[] args) {
        int i = 10;
        while(i --> 0){
            new Thread(new Test1(i)).start();
        }
    }
}
