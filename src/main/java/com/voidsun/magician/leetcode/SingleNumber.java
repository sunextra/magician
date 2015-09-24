package com.voidsun.magician.leetcode;

/**
 *
 * https://leetcode.com/problems/single-number/
 *
 * @Description
 *  Single Number
 *
 *Given an array of integers, every element appears twice except for one. Find that single one.
    Note:
    Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * @Author voidsun
 * @Date 2015/9/24
 * @Email voidsun@126.com
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int i=0; i<nums.length; i++){
            result ^= nums[i];
        }
        String s = "";
        return result;
    }
}
