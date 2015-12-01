package com.voidsun.magician.java.dynamic.jdk;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/11/30
 * @Email voidsun@126.com
 */
public class TestTable {
    public static void main(String[] args) {
        ProxyHandler handler = new ProxyHandler();
        Subject subject = (Subject)handler.bind(new HelloWorldSubject());
        subject.execute();
    }
}
