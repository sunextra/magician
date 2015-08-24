package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/ugly-number/
 *
 *  Write a program to check whether a given number is an ugly number.
    Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
    Note that 1 is typically treated as an ugly number.
 * @Description
 * @Author voidsun
 * @Date 2015/8/24
 * @Email voidsun@126.com
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        while(num != 1){
            if(num == 0) return false;
            if(num/2*2 == num) return isUgly(num/2);
            if(num/3*3 == num) return isUgly(num/3);
            if(num/5*5 == num) return isUgly(num/5);
            return false;
        }
        return true;
    }
}
