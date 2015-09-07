package com.voidsun.magician.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 *
 * @Description
 * Given an array of integers, find two numbers such that they add up to a specific target number.
    The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
    You may assume that each input would have exactly one solution.
    Input: numbers={2, 7, 11, 15}, target=9
    Output: index1=1, index2=2
 *
 * @Author voidsun
 * @Date 2015/9/7
 * @Email voidsun@126.com
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer, Integer> storedIndexMap = new HashMap<>(numbers.length * 2);
        for(int i=0; i<numbers.length ;i++){
            Integer storedIndex = storedIndexMap.get(numbers[i]);
            if(storedIndex == null){
                storedIndexMap.put(target - numbers[i], i);
                continue;
            }
            return new int[]{i+1, storedIndex+1};
        }
        return null;
    }

    public static void main(String[] args){
        System.out.println(new TwoSum().twoSum(new int[]{3,2,4}, 6));
    }
}
