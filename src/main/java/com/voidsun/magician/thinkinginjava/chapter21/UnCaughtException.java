package com.voidsun.magician.thinkinginjava.chapter21;

/**
 * Created by voidsun on 16/2/16.
 */
public class UnCaughtException {

    static class ExceptionTask implements Runnable{
        String name;
        public ExceptionTask(String name){
            this.name = name;
        }
        @Override
        public void run() {
            throw new RuntimeException("task " + name + " throw exception");
        }
    }

    public static void main(String[] args) {
        Thread.UncaughtExceptionHandler handler = new UnCaughtExceptionHandler();
        Thread t = new Thread(new ExceptionTask("task_single"));
        t.setUncaughtExceptionHandler(handler);
        t.start();

        Thread t1 = new Thread(new ExceptionTask("task_single2"));
        t1.start();
    }

}

class UnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("catch e " + e.getMessage());
    }
}
