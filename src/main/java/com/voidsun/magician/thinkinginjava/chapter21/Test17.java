package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/18.
 */
public class Test17 {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newCachedThreadPool();
        int i = 10;
        while(i --> 0){
            executorService.submit(new MultiCounter(i, counter));
        }
        TimeUnit.SECONDS.sleep(3);
        counter.isRunning = false;
        executorService.shutdown();
        if(!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)){
            System.out.println("some counter not stopped");
        }
        System.out.println("total:"+counter.get()+"  sum:"+counter.sum());
    }
}


class MultiCounter implements Runnable{
    final int id;
    int countVal = 0;
    final Counter counter;

    public String toString(){
        return "counter["+id+"]: "+getVal();
    }

    public MultiCounter(int id, Counter counter){
        this.id = id;
        this.counter = counter;
        counter.add(this);
    }

    public synchronized int getVal(){
        return countVal;
    }

    @Override
    public void run() {
        while(counter.isRunning){
            synchronized (this) {
                countVal++;
            }
            System.out.println(this + " total:"+counter.increase());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println(this + " stopped");
    }
}

class Counter{
    private List<MultiCounter> counterList = new ArrayList<>();
    public volatile boolean isRunning = true;
    int countVal = 0;

    public synchronized int increase(){
        return ++countVal;
    }

    public synchronized int get(){
        return countVal;
    }
    public void add(MultiCounter counter){
        this.counterList.add(counter);
    }
    public List<MultiCounter> getList(){
        return counterList;
    }

    public int sum(){
        int i = 0;
        for(MultiCounter multiCounter : counterList){
            i += multiCounter.getVal();
        }
        return i;
    }
}
