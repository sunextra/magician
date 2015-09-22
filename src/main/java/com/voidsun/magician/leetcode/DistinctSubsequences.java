package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 *
 * @Description
 *      Given a string S and a string T, count the number of distinct subsequences of T in S.
 *      A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *      Here is an example:
 *      S = "rabbbit", T = "rabbit"
 *      Return 3.
 * @Author voidsun
 * @Date 2015/8/18
 * @Email voidsun@126.com
 */
public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        if(t.isEmpty() || s.isEmpty()) return 0;
        int[][] dp =  new int[s.length()+1][t.length()+1];
        if(s.charAt(0) == t.charAt(0)) {
            dp[0][0] = 1;
        }
        for(int i=1; i<s.length(); i++){
            if(t.charAt(0) == s.charAt(i)){
                dp[i][0] = dp[i-1][0] + 1;
            }else{
                dp[i][0] = dp[i-1][0];
            }
        }
        for(int i=1; i<s.length(); i++){
            for(int j=1; j<=i && j<t.length(); j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[s.length()-1][t.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubsequences().numDistinct("rabbbit", "rabbit"));
    }
}
