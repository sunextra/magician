package com.voidsun.magician.notes.单例反序列化;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/15
 * @Email voidsun@126.com
 */
public class BeanCopy {
    public static Object copy(Object source){
        try {
            ByteOutputStream byteArrayOutputStream = new ByteOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(source);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.getBytes());
            Object object = new ObjectInputStream(byteArrayInputStream).readObject();
            return object;
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
