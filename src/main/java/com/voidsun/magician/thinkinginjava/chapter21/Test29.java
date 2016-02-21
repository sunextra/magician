package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by voidsun on 16/2/20.
 */
public class Test29 {
    public static void main(String[] args) throws InterruptedException {
        int consumerNum = 4;
        int providerNum = 5;
        ExecutorService service = Executors.newFixedThreadPool(consumerNum + providerNum);
        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();
        while(consumerNum --> 0){
            service.execute(new Consumer(consumerNum, queue));
        }
        while(providerNum --> 0){
            service.execute(new Provider(providerNum, queue));
        }
    }


    static class Data{
        final private int providerId;
        final private int dataId;
        public Data(int providerId, int dataId){
            this.providerId = providerId;
            this.dataId =dataId;
        }

        @Override
        public String toString() {
            return "Data{由"+providerId+"第"+dataId+"次创建}";
        }
    }


    static class Consumer implements Runnable{
        final int id;
        final Random random = new Random();
        final BlockingQueue<Data> queue;
        private Data data;

        @Override
        public String toString() {
            return "消费者["+ id + "]";
        }

        public Consumer(int id, BlockingQueue<Data> queue){
            this.id = id;
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                try {
                    data = queue.take();
                    System.out.println(this + " 消费了 " + data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(4000) + 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Provider implements Runnable {
        final int id;
        final Random random = new Random();
        final BlockingQueue<Data> queue;
        int dataId;

        @Override
        public String toString() {
            return "生产者[" + id + "]";
        }

        public Provider(int id, BlockingQueue<Data> queue) {
            this.id = id;
            this.queue = queue;
        }

        private Data data;

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(4000) + 1000);
                    data = new Data(this.id, dataId++);
                    System.out.println(this + " 生产了 " + data);
                    queue.add(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
