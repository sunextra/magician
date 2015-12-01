package com.voidsun.magician.java.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/11/30
 * @Email voidsun@126.com
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;
    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before execute");
        method.invoke(target, args);
        System.out.println("after execute");
        return null;
    }
}
