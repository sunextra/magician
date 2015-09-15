package com.voidsun.magician.notes.单例反序列化;

import java.io.IOException;
import java.io.Serializable;

/**
 * @Description
 *
 * 如果不加上readResolve，则反序列化会破坏单例
 *
 * @Author voidsun
 * @Date 2015/9/15
 * @Email voidsun@126.com
 */
public class Singleton implements Serializable{

    private static Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton singleton = Singleton.getInstance();
        System.out.println(BeanCopy.copy(singleton) == singleton);
    }
}
