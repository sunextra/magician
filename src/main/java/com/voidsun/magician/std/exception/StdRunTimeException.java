package com.voidsun.magician.std.exception;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/8
 * @Email voidsun@126.com
 */
public class StdRunTimeException extends RuntimeException {
    public StdRunTimeException(){}
    public StdRunTimeException(String msg){
        super(msg);
    }
}
