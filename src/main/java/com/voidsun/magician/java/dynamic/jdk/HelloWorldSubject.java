package com.voidsun.magician.java.dynamic.jdk;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/11/30
 * @Email voidsun@126.com
 */
public class HelloWorldSubject implements Subject {
    @Override
    public void execute() {
        System.out.println("hello world");
    }
}
