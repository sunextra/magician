package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/16.
 */
public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        TimeUnit.SECONDS.sleep(1);
    }

}
class Daemon implements Runnable{
    private Thread[] t = new Thread[10];
    @Override
    public void run() {
        for(int i=0; i<t.length; i++){
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("spawn["+i+"] started. ");
        }
        for(int i=0; i<t.length; i++){
            System.out.println("spawn["+i+"] is daemon = " + t[i].isDaemon());
        }
        while(true){
            Thread.yield();
        }
    }
}
class DaemonSpawn implements Runnable{
    @Override
    public void run() {
        while(true){
            Thread.yield();
        }
    }
}