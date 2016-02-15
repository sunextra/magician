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
        int add = 0;
        ListNode node = new ListNode(0);
        ListNode result = node;
        boolean next = false;
        while(add == 1 || l1 != null || l2 != null){
            if(next){
                node.next = new ListNode(0);
                node = node.next;
            }
            int l1num = 0;
            if(l1 != null){
                l1num = l1.val;
                l1 = l1.next;
            }
            int l2num = 0;
            if(l2 != null){
                l2num = l2.val;
                l2 = l2.next;
            }
            int sum = l1num + l2num + add;
            if(sum >= 10){
                sum = sum - 10;
                add = 1;
            }else{
                add = 0;
            }
            node.val = sum;
            next = true;
        }
        return result;
    }
}
