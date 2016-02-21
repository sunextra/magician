package com.voidsun.magician.thinkinginjava.chapter21;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by voidsun on 16/2/20.
 */
public class Test27 {
    public static void main(String[] args) throws InterruptedException {
        int consumerNum = 4;
        int providerNum = 5;
        ExecutorService service = Executors.newFixedThreadPool(consumerNum + providerNum);
        Queue queue = new Queue();
        while(consumerNum --> 0){
            service.execute(new Consumer(consumerNum, queue));
        }
        while(providerNum --> 0){
            service.execute(new Provider(providerNum, queue));
        }
        while(true){
            TimeUnit.SECONDS.sleep(10);
            synchronized (queue){
                queue.report();
            }
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

    static class Queue{
        private int putTime;
        private int badPut;
        private int getTime;
        private int badGet;
        public Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();

        final private LinkedList<Data> queue = new LinkedList<>();

        public void report(){
            System.out.println("[report] ****** 共生产了"+putTime+" 饱和"+badPut+" 消费了"+getTime+" 饥饿"+badGet+" 队列里还剩"+queue.size());
        }
        public boolean put(Data data){

            if(queue.size() == 100){
                badPut++;
                return false;
            }
            queue.add(data);
            putTime++;
            return true;
        }
        public Data get(){
            Data data = queue.poll();
            if(data != null){
                getTime++;
                return data;
            }
            badGet++;
            return null;
        }
    }

    static class Consumer implements Runnable{
        final int id;
        final Random random = new Random();
        final Queue queue;
        private Data data;

        @Override
        public String toString() {
            return "消费者["+ id + "]";
        }

        public Consumer(int id, Queue queue){
            this.id = id;
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                if (data != null) {
                    System.out.println(this + " 消费了 " + data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(4000) + 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.lock.lock();
                try {
                    while (true) {
                        data = queue.get();
                        if (data != null) {
                            queue.condition.signalAll();
                            break;
                        }
                        try {
                            System.out.println(this + " 在等待消费");
                            queue.condition.await();
                        } catch (InterruptedException e) {
                            System.out.println("[error] " + this + "的等待被中断了");
                        } catch (IllegalMonitorStateException e) {
                            System.out.println("[error]" + this + "试图释放锁 但是失败了");
                        }
                    }
                }finally {
                    queue.lock.unlock();
                }
            }
        }
    }

    static class Provider implements Runnable {
        final int id;
        final Random random = new Random();
        final Queue queue;
        int dataId;

        @Override
        public String toString() {
            return "生产者[" + id + "]";
        }

        public Provider(int id, Queue queue) {
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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.lock.lock();
                try{
                    while (true) {
                        boolean result = queue.put(data);
                        if (result) {
                            System.out.println(this + " 将 " + data + "放入消费队列");
                            queue.condition.signalAll();
                            break;
                        } else {
                            System.out.println(this + " 试图将 " + data + "放入消费队列,但是失败了.进入了等待");
                            try {
                                queue.condition.await();
                            } catch (InterruptedException e) {
                                System.out.println("[error]" + this + "的等待被中断了");
                            } catch (IllegalMonitorStateException e) {
                                System.out.println("[error]" + this + "试图释放锁 但是失败了");
                            }
                        }
                    }
                }finally {
                    queue.lock.unlock();
                }
            }
        }
    }
}
