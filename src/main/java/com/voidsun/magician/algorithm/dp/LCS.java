package com.voidsun.magician.algorithm.dp;

/**
 * 最长公共子序列
 *
 * ABCDABCDBCDABCDA
 * ABCDBBDBADCBDABBD
 *
 * @Description
 * @Author voidsun
 * @Date 2015/8/13
 * @Email voidsun@126.com
 */
public class LCS {
    static char[] a = " ABCDABCDBCDABCDA".toCharArray();
    static char[] b = " ABCDBBDBADCBDABBD".toCharArray();

    static int dp[][] = new int[a.length][b.length];

    static void lcs(){
        for(int i=1; i<a.length; i++){
            for(int j=1; j<b.length; j++){
                if(a[i] == b[j]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else if(dp[i][j-1] >= dp[i-1][j]){
                    dp[i][j] = dp[i][j-1];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
    }


    public static void main(String[] args) {
        lcs();
        for(int j = 0; j<b.length; j++){
            System.out.printf("%3d", j);
        }
        System.out.println();
        for(int i=1; i<a.length; i++){
            System.out.printf("%3d", i);
            for(int j=1; j<b.length; j++){
                System.out.printf("%3d", dp[i][j]);
            }
            System.out.println();
        }
        System.out.println("=======================");
        String r = "";
        for(int i = a.length-1, j = b.length-1; i>0 && j>0 ;){
            if(dp[i][j] == dp[i][j-1]){
                j--;
                continue;
            }
            if(dp[i][j] == dp[i-1][j]){
                i--;
                continue;
            }
            j--;
            r = a[i--] + r;
        }
        System.out.print(r);

    }
}
