package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/14.
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        List<ExecutorService> serviceList = new ArrayList<>();
        serviceList.add(Executors.newCachedThreadPool());
        serviceList.add(Executors.newFixedThreadPool(3));
        serviceList.add(Executors.newSingleThreadExecutor());
        for(ExecutorService executorService : serviceList){
            System.out.println("=======================");
            for(int i = 0; i <= 10; i++){
                executorService.execute(new Test1(i));
            }
            TimeUnit.SECONDS.sleep(3);
        }

    }
}
