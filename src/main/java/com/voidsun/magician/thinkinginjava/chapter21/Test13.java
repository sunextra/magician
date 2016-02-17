package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by voidsun on 16/2/17.
 */
public class Test13 implements Runnable{
    private IntGenerator intGenerator;
    private IntSet intSet;
    public Test13(IntGenerator intGenerator, IntSet intSet){
        this.intGenerator = intGenerator;
        this.intSet = intSet;
    }

    public static void main(String[] args) {
        IntSet set = new IntSet(10000);
        IntGenerator intGenerator = new IntGenerator();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0; i<10; i++){
            executorService.execute(new Test13(intGenerator, set));
        }
        executorService.shutdown();
    }

    @Override
    public void run() {
        while(true) {
            int i = intGenerator.nextInt();
            intSet.checkInsert(i);
        }
    }
}
class IntSet{
    int size;
    int[] resultSet;

    public IntSet(int size){
        this.size = size;
        resultSet = new int[size];
        for(int i=0; i<size; i++){
            resultSet[i] = -1;
        }
    }

    public synchronized void checkInsert(int i){
        int t = i / size;
        int idx = i % size;
        if(resultSet[idx] != t){
            resultSet[idx] = t;
        }else{
            System.out.println("error at " + i);
            throw new RuntimeException();
        }
    }
}
class IntGenerator{
    volatile int i = 0;

    public synchronized int nextInt(){
        return i++;
    }
}