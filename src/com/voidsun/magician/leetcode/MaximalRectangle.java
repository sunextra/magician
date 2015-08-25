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
    public int maximalRectangle(char[][] matrix) {

    }

    public static void main(String[] args) {
        int rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'0'}
        });
        System.out.print(rect);
    }
}
class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;+-
    }
}