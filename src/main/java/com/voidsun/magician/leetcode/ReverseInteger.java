package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/reverse-integer/
 *
 * @Description
 *
 * Reverse digits of an integer.

    Example1: x = 123, return 321
    Example2: x = -123, return -321
 * @Author voidsun
 * @Date 2015/11/20
 * @Email voidsun@126.com
 */
public class ReverseInteger {
    public int reverse(int x) {
        boolean ltZero = x < 0;
        if(ltZero) x = -x;
        long y = 0;
        while(x > 0){
            y = y*10 + x%10;
            x = x/10;
        }
        if(y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) y = 0;
        if(ltZero) y = -y;
        return (int)y;
    }
}
