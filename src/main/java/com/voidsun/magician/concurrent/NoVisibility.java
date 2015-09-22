package com.voidsun.magician.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 *
 * 本意是ready的改变对于子线程不可见，然而在jdk8下没有达到预期的效果
 *
 * @Author voidsun
 * @Date 2015/9/14
 * @Email voidsun@126.com
 */
public class NoVisibility {

    static boolean ready = false;
    static int num;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(!ready)
                Thread.yield();
            System.out.print(num);
        }).start();
        TimeUnit.SECONDS.sleep(1);
        num = 42;
        ready = true;
    }

}
