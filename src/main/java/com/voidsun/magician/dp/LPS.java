package com.voidsun.magician.dp;

/**
 * TODO
 * dp[m, n] = dp[m-1, n-1] + 2  when char[m] = char[n] && n>m
 * dp[m, n] = dp[m, n-1] + 1 when char[m] = char[n-1] && n==m
 *
 * @Description
 * @Author voidsun
 * @Date 2015/8/28
 * @Email voidsun@126.com
 */
public class LPS {

    void lps(char[] chars){
        int[][] dp = new int[chars.length+1][chars.length+1];

    }
}
