package com.voidsun.magician.notes.单例反序列化;

import java.io.Serializable;

/**
 * @Description
 *
 * 天生规避反序列化问题
 * @Author voidsun
 * @Date 2015/9/15
 * @Email voidsun@126.com
 */
public enum SingleEnum implements Serializable{
    INSTANCE;

    public static void main(String[] args) {
        System.out.print(BeanCopy.copy(SingleEnum.INSTANCE) == SingleEnum.INSTANCE);
    }
}
