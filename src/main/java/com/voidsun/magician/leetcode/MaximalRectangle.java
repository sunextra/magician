package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.Std;

import java.util.HashSet;
import java.util.Set;

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
    class Rect{
        int width;
        int height;
        public Rect(int width, int height){
            this.width = width;
            this.height = height;
        }
        public int area(){
            return width*height;
        }
        @Override
        public boolean equals(Object o) {
            Rect rect = (Rect) o;
            return height == rect.height && width == rect.width;
        }

        @Override
        public int hashCode() {
            return width*10000000+height;
        }

        @Override
        public String toString() {
            return "<"+width+","+height+">";
        }
    }
    Rect maxWidth;
    Rect maxHeight;
    Rect maxArea;

    void create(int width, int height){
        if(maxWidth == null || width > maxWidth.width){
            maxWidth = new Rect(width, height);
            return;
        }
        if(maxHeight == null || height > maxHeight.height){
            maxHeight = new Rect(width, height);
            return;
        }
        if(maxArea == null || width * height > maxArea.area()){
            maxArea = new Rect(width, height);
            return;
        }
    }

    int add(Set<Rect> rectSet, int max, Rect rect){
        if(rect != null){
            rectSet.add(rect);
            max = Math.max(rect.area(), max);
        }
        return max;
    }
    int add(Set<Rect> rectSet, int max){
        max = add(rectSet, max, maxWidth);
        max = add(rectSet, max, maxHeight);
        max = add(rectSet, max, maxArea);
        return max;
    }
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }
        int height = matrix.length;
        int weight = matrix[0].length;
        Set<Rect>[][] dp = new HashSet[height+1][weight+1];
        for(int i=0; i<=height; i++){
            for(int j=0; j<=weight; j++){
                dp[i][j] = new HashSet<>();
            }
        }
        int max = 0;
        for(int i=1; i<=height; i++){
            for(int j=1; j<=weight; j++){
                if(matrix[i-1][j-1] == '1') {
                    maxWidth = null;
                    maxHeight = null;
                    maxArea = null;
                    Set<Rect> left = dp[i][j-1];
                    Set<Rect> top = dp[i-1][j];
                    Set<Rect> leftTop = dp[i-1][j-1];
                    if(left.isEmpty() && top.isEmpty()){
                        create(1, 1);
                    }else if(left.isEmpty()){
                        for(Rect rect : top){
                            create(1, rect.height+1);
                        }
                    }else if(top.isEmpty()){
                        for(Rect rect : left){
                            create(rect.width+1, 1);
                        }
                    }else if(leftTop.isEmpty()){
                        for(Rect rect : left){
                            create(rect.width + 1, rect.height);
                        }
                        for(Rect rect : top){
                            create(rect.width, rect.height + 1);
                        }
                    }else{
                        for(Rect leftRect : left){
                            for(Rect topRect : top){
                                if(leftRect.width+1 != topRect.width){
                                    create(leftRect.width + 1, Math.min(topRect.height + 1, leftRect.height));
                                }
                                if(topRect.height+1 != leftRect.height){
                                    create(Math.min(leftRect.width + 1, topRect.width), topRect.height + 1);
                                }
                                create(Math.min(leftRect.width + 1, topRect.width),
                                        Math.min(topRect.height + 1, leftRect.height));
                            }
                        }
                    }
                    max = add(dp[i][j], max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int rect;
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '1', '0', '1'},
                {'1', '1', '0', '1'},
                {'1', '1', '1', '1'}
        });
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}
        });
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        });
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(new char[][]{
                {'1', '0', '1', '1', '1'},
                {'0', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '1'},
                {'0', '1', '1', '1', '1'}
        });
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(
                Std.buildMatrix("0001010",
                                "0100000",
                                "0101001",
                                "0011001",
                                "1111110",
                                "1001011",
                                "0100101",
                                "1101110",
                                "1010101",
                                "1110000")
        );
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(
                Std.buildMatrix("1")
        );
        System.out.println(rect);
        rect = new MaximalRectangle().maximalRectangle(
                Std.buildMatrix("01101",
                                "11010",
                                "01110",
                                "11110",
                                "11111",
                                "00000")
        );
        System.out.println(rect);
    }
}