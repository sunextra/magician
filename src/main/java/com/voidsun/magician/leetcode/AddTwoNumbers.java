package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.leetcode.ListNode;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * @Description
 *
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8

 * @Author voidsun
 * @Date 2015/11/10
 * @Email voidsun@126.com
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int keeper = 0;

        while(l1.next != null || l2.next != null){
            l1 = l1.next;
        }

    }
}
