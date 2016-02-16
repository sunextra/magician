package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by voidsun on 16/2/16.
 */
public class StaticSynchronized {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        int i = 10;
        while(i-->0){
            service.execute(new Task());
        }
        service.shutdown();
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        while(true){
            int result = StaticIncrease.increase();
            if(result %2 != 0){
                System.out.println("wrong result" + result);
                break;
            }
        }
    }
}

class StaticIncrease{
    private static int i = 0;

    public synchronized static int increase(){
        i++;
        i++;
        return i;
    }
}