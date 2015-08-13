package com.voidsun.magician.dp;

import static com.voidsun.magician.std.Std.*;

/**
 * 钢材切割问题
 * 切割价值
 * 1:3
 * 2:5
 * 3:9
 * 4:10
 *
 * 每次成本1 求最优解
 *
 *
 * @Description
 * @Author voidsun
 * @Date 2015/8/12
 * @Email voidsun@126.com
 */
public class Test1 {
    int[] value = {0, 3, 5, 9, 10};
    int cost = 1;

    void start(int n){
        int[] dp = new int[n + 1];
        cut(n, dp);
        System.out.println(dp[n]);
    }

    void cut(int n, int[] dp){
        for(int s = 1; s <= n; s++){
            for(int i = 0; i <= 4 && i <= s; i++){
                dp[s] = max(dp[s], value[i] + dp[s-i] - cost);
            }
        }
    }

    public static void main(String[] args) {
        new Test1().start(8);
    }
    
}
