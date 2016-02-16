package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.concurrent.*;

/**
 * Created by voidsun on 16/2/16.
 */
public class Test10 {
    ExecutorService service = Executors.newCachedThreadPool();

    Future<String> runTask(int i){
        Future<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "task run";
            }
        });
        service.shutdown();
        return future;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(new Test10().runTask(10).get());
    }
}
