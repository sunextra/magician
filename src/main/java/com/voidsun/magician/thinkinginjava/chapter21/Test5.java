package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by voidsun on 16/2/15.
 */
public class Test5 implements Callable<String> {
    final private int id;
    final private int n;

    @Override
    public String toString() {
        return "["+id+"]";
    }

    public Test5(int id, int n){
        this.id = id;
        this.n = n;
    }

    @Override
    public String call() throws Exception {
        int result = 0;
        int prev = 1;
        int curr = 1;
        for(int idx = 0; idx <= n; idx++){
            if(idx == 0 || idx == 1){
                result += 1;
            }else{
                int temp = curr + prev;
                prev = curr;
                curr = temp;
                result += curr;
            }
        }
        return this + " ["+n+"]=" + result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<String>> results = new ArrayList<>();
        int i = 10;
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(3);
        while(i --> 0){
            results.add(service.submit(new Test5(i, random.nextInt(10))));
        }
        for(Future<String> result : results){
            System.out.println(result.get());
        }
    }
}
