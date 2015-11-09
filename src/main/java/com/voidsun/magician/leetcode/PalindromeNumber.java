package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/problems/palindrome-number/
 *
 * @Description
 * Determine whether an integer is a palindrome. Do this without extra space.
 * @Author voidsun
 * @Date 2015/11/9
 * @Email voidsun@126.com
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        int y = 0;
        int k = x;
        while(k > 0){
            y = y*10 + k - k/10*10;
            k/=10;
        }
        return y==x;
    }

    public static void main(String[] args) {
        System.out.print(new PalindromeNumber().isPalindrome(-1));
        System.out.print(new PalindromeNumber().isPalindrome(121211));
        System.out.print(new PalindromeNumber().isPalindrome(121121));

    }
}
