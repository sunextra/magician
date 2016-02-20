package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/19.
 */
public class Test19 {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        int i = 10;
        List<Future<?>> futureList = new ArrayList<>();
        while(i --> 0){
            futureList.add(executorService.submit(new MultiCounter(i, counter)));
        }
        TimeUnit.SECONDS.sleep(2);
        for(Future future : futureList){
            future.cancel(true);
        }
        executorService.shutdown();
        if(!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)){
            System.out.println("some counter not stopped");
        }
        System.out.println("total:"+counter.get()+"  sum:"+counter.sum());
    }
}
