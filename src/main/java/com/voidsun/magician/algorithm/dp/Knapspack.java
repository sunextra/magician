package com.voidsun.magician.algorithm.dp;

/**
 * @Description
 * 0-1背包问题,有一些商品,重w[i],价值v[i],计算在只有一个可以承重W的背包的情况下获得最多的商品的策略
 * dp[i][j] = max(dp[i-1][j], dp[i-1][j-wj] + vj)
 * @Author voidsun
 * @Date 2015/9/7
 * @Email voidsun@126.com
 */
public class Knapspack {

    void pick(Bag[] bag, int wmax){
        int vmax = 0;
        int[][] dp = new int[bag.length+1][wmax+1];
        for(int i=1; i<=bag.length; i++){
            for(int j=bag[i-1].w; j<=wmax; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-bag[i-1].w] + bag[i-1].v);
                vmax = Math.max(vmax, dp[i][j]);
            }
        }
        System.out.println(vmax);
    }

    public static void main(String[] args) {
        Bag[] bag = {bag(1, 2), bag(2, 3), bag(3, 5), bag(5, 8), bag(8, 15), bag(4, 6)};
        new Knapspack().pick(bag, 15);
    }

    static Bag bag(int w, int v){
        return new Bag(w, v);
    }
}
class Bag{
    public int w, v;
    public Bag(int w, int v){
        this.w = w;
        this.v = v;
    }
}
