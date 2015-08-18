package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/rotate-image/
 *
 * @Description
 *  You are given an n x n 2D matrix representing an image.
    Rotate the image by 90 degrees (clockwise).
    Follow up:
    Could you do this in-place?
 * @Author voidsun
 * @Date 2015/8/18
 * @Email voidsun@126.com
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int xHalf = length%2 == 0?length/2:(length+1)/2;
        int yHalf = length/2;
        for(int x=0; x<xHalf; x++){
            for(int y=0; y<yHalf; y++){
                int r1 = matrix[x][y];
                int _x = length-1 - x;
                int r2 = matrix[y][_x];
                int _y = length-1 - y;
                int r3 = matrix[_x][_y];
                int r4 = matrix[_y][x];
                matrix[x][y] = r4;
                matrix[y][_x] = r1;
                matrix[_x][_y] = r2;
                matrix[_y][x] = r3;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        new RotateImage().rotate(matrix);
        for(int[] yline : matrix){
            for(int x : yline){
                System.out.print(x+" ");
            }
            System.out.println();
        }
    }




}
