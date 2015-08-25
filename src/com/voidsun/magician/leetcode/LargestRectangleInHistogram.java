package com.voidsun.magician.leetcode;

/**
 *
 * TODO: LTE
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 *   Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
     Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
     The largest rectangle is shown in the shaded area, which has area = 10 unit.
     For example,
     Given height = [2,1,5,6,2,3],
     return 10.
 * @Description
 * @Author voidsun
 * @Date 2015/8/25
 * @Email voidsun@126.com
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] height) {
        int max = 0;
        int current = 0;
        while(current < height.length){
            int count = 1;
            int index = current;
            while(--index > 0 && height[index] >= height[current]){
                count++;
            }
            index = current;
            while(++index < height.length && height[index] >= height[current]){
                count++;
            }
            int area = count * height[current];
            max = max > area ? max : area;
            current++;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.print(new LargestRectangleInHistogram().largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}
