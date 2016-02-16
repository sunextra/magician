package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/15.
 */
public class Test6 implements Runnable {
    private int sortNum;
    private Test6(int sortNum){
        this.sortNum = sortNum;
    }
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sortNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sortNum);
    }

    public static void main(String[] args) {
        //sleep sort 233
        ExecutorService service = Executors.newCachedThreadPool();
        Random random = new Random();
        int i = 10;
        while(i --> 0){
            service.execute(new Test6(random.nextInt(20)));
        }
        service.shutdown();
    }
}
