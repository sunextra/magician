package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/maximal-square/
 *
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
     For example, given the following matrix:
     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 4.
 * @Description
 * @Author voidsun
 * @Date 2015/8/26
 * @Email voidsun@126.com
 */
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int height = matrix.length;
        int weight = matrix[0].length;
        int[][] dp = new int[height + 1][weight + 1];
        int max = 0;
        for(int i=0; i<height; i++){
            for(int j=0; j<weight; j++){
                if(matrix[i][j] == '1'){
                    int left = dp[i+1][j];
                    int top = dp[i][j+1];
                    int leftTop = dp[i][j];
                    dp[i+1][j+1] = left == top && left == leftTop ? leftTop+1 : Math.min(Math.min(left, top), leftTop)+1;
                    if(dp[i+1][j+1] > max) max = dp[i+1][j+1];
                }
            }
        }
        return max * max;
    }
    public static void main(String[] args) {
        int rect = new MaximalSquare().maximalSquare(new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'},
        });
        System.out.print(rect);
    }
}
