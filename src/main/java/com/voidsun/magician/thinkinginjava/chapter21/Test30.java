package com.voidsun.magician.thinkinginjava.chapter21;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by voidsun on 16/2/21.
 */
public class Test30 {
    public static PipedWriter writer= new PipedWriter();
    public static PipedReader reader;
    static {
        try {
            reader = new PipedReader(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static class Sender implements Runnable{
        final private int id;
        Random random = new Random();
        public Sender(int id){
            this.id = id;
        }

        @Override
        public void run() {
            while(true) {
                int genInt = random.nextInt(1000) + 10000;
                System.out.println("["+id+"]gen int " + genInt);
                try {
                    writer.write(genInt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(100)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Receiver implements Runnable{

        @Override
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while(true) {
                try {
                    System.out.println(reader.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Sender(1));
        service.execute(new Sender(2));
        service.execute(new Receiver());
    }
}
