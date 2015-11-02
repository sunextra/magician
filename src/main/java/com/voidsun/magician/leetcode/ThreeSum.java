package com.voidsun.magician.leetcode;

import java.util.*;

/**
 *
 * TODO: TLE
 * https://leetcode.com/problems/3sum/
 *
 * @Description
 *
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

    Note:

    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
    The solution set must not contain duplicate triplets.

    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)


 * @Author voidsun
 * @Date 2015/11/2
 * @Email voidsun@126.com
 */
public class ThreeSum {

    int[] sortArray = new int[3];
    private void sortThree(int i, int j, int k){
        if(i>=j&&i>=k){
            sortArray[0] = i;
            if(j>=k){
                sortArray[1] = j;
                sortArray[2] = k;
            }else{
                sortArray[1] = k;
                sortArray[2] = j;
            }
        }else if(j>=k){
            sortArray[0] = j;
            if(k>=i){
                sortArray[1] = k;
                sortArray[2] = i;
            }else{
                sortArray[1] = i;
                sortArray[2] = k;
            }
        }else{
            sortArray[0] = k;
            if(j>=i){
                sortArray[1] = j;
                sortArray[2] = i;
            }else{
                sortArray[1] = i;
                sortArray[2] = j;
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Map<Integer, List<Integer>> oppositeMap = new HashMap<>(length/3*4);
        for(int i=0; i<length; i++){
            List<Integer> indexList = oppositeMap.get(0-nums[i]);
            if(indexList == null){
                indexList = new ArrayList<>();
                oppositeMap.put(0-nums[i], indexList);
            }
            indexList.add(i);
        }
        List<List<Integer>> result = new ArrayList<>();
        Set<String> resultKeySet = new HashSet<>();
        for(int i=0; i<length-1; i++){
            for(int j=i+1; j<length; j++){
                List<Integer> indexList = oppositeMap.get(nums[i] + nums[j]);
                if(indexList != null){
                    for(Integer index : indexList){
                        if(index != i && index != j){
                            sortThree(i, j, index);
                            String key = sortArray[0] + "_" + sortArray[1] + "_" + sortArray[2];
                            if(!resultKeySet.contains(key)){
                                resultKeySet.add(key);
                                result.add(Arrays.asList(nums[i], nums[j], nums[index]));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        new ThreeSum().threeSum(new int[]{-1, -1, 2, 2, 3});
    }
}
