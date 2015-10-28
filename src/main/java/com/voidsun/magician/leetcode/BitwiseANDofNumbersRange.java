package com.voidsun.magician.leetcode;

/**
 * TODO: TLE
 * https://leetcode.com/problems/bitwise-and-of-numbers-range/
 * @Description
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

    For example, given the range [5, 7], you should return 4.
 * @Author voidsun
 * @Date 2015/10/28
 * @Email voidsun@126.com
 */
public class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while(m!=n && m>0){
            i++;
            m>>=1;
            n>>=1;
        }
        return m<<i;
    }



    public static void main(String[] args) {
        System.out.print(new BitwiseANDofNumbersRange().rangeBitwiseAnd(2147483647, 2147483647));
    }
}
