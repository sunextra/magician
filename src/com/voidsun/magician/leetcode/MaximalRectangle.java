package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/maximal-rectangle/
 *
 *  Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 *
 * @Description
 * @Author voidsun
 * @Date 2015/8/25
 * @Email voidsun@126.com
 */
public class MaximalRectangle {

    class Area{
        int x, y;
        public Area(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int height = matrix.length;
        int weight = matrix[0].length;
        Area[][] dp = new Area[height+1][weight+1];
        for(int i=0; i<dp.length; i++){
            for(int j=0; j<dp[i].length; j++){
                dp[i][j] = new Area(0, 0);
            }
        }
        int max = 0;
        for(int i=1; i<=height; i++){
            for(int j=1; j<=weight; j++){
                if(matrix[i-1][j-1] == '1'){
                    int x = dp[i][j-1].x;
                    int y = dp[i-1][j].y;


                    if(x * y > max){
                        max = x * y;
                    }
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int rect;
        /**
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '1', '0', '1'},
                {'1', '1', '0', '1'},
                {'1', '1', '1', '1'}
        });
        System.out.print(rect);**/
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}
        });
        System.out.print(rect);
    }
}