package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by voidsun on 16/2/17.
 */
public class Test11 implements Runnable{
    private EqualNum equalNum;

    public Test11(EqualNum equalNum){
        this.equalNum = equalNum;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        EqualNum equalNum = new EqualNum();
        executorService.execute(new Test11(equalNum));
        executorService.execute(new Test11(equalNum));
        executorService.shutdown();
    }

    @Override
    public void run() {
        while(true){
            equalNum.syncAddAll();
            if (!equalNum.check()){
                break;
            }
            Thread.yield();
        }
    }
}
class EqualNum{
    int num1 = 0;
    int num2 = 0;
    public void addAll(){
        num1++;
        num2++;
    }
    public synchronized void syncAddAll(){
        num1++;
        num2++;
    }
    public synchronized boolean check(){
        int num1 = this.num1;
        int num2 = this.num2;
        boolean isOk = num1 == num2;
        if(!isOk) {
            System.out.println("check error on " + num1 + " --> " + num2);
        }
        return isOk;
    }
}
