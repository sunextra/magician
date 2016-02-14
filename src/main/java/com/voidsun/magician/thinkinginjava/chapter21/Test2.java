package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.Random;

/**
 * Created by voidsun on 16/2/14.
 */
public class Test2 implements Runnable {
    final private int id;
    final private int n;

    @Override
    public String toString() {
        return "["+id+"]";
    }

    public Test2(int id, int n){
        this.id = id;
        this.n = n;
    }

    @Override
    public void run() {
        int prev = 1;
        int curr = 1;
        for(int idx = 0; idx <= n; idx++){
            if(idx == 0 || idx == 1){
                System.out.println(this+" ["+idx+"] = "+ 1);
            }else{
                int temp = curr + prev;
                prev = curr;
                curr = temp;
                System.out.println(this+" ["+idx+"] = "+ curr);
            }
        }
    }

    public static void main(String[] args) {
        int i = 10;
        Random random = new Random();
        while(i --> 0){
            new Thread(new Test2(i, random.nextInt(10))).start();
        }
    }
}
