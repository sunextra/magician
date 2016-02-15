package com.voidsun.magician.leetcode;

import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 * <p>
 * Created by voidsun on 16/2/15.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int start = 0;
        int end = 0;
        int curr = 0;
        byte[] charSet = new byte[256];
        char[] chars = s.toCharArray();
        while(end < s.length()){
            if(charSet[chars[end]] == 0){
                charSet[chars[end]] = 1;
                curr ++;
                end ++;
            }else{
                while(start < end){
                    start ++;
                    if(chars[start-1] == chars[end]){
                        break;
                    }else{
                        charSet[chars[start-1]] = 0;
                    }
                    curr--;
                }
                end ++;
            }
            if(curr > max){
                max = curr;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(
            new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("tmmzuxt")
        );
    }

}
