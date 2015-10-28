package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.leetcode.ListNode;

/**
 * https://leetcode.com/problems/rotate-list/
 * @Description
 * Given a list, rotate the list to the right by k places, where k is non-negative.

    For example:
    Given 1->2->3->4->5->NULL and k = 2,
    return 4->5->1->2->3->NULL.
 * @Author voidsun
 * @Date 2015/10/28
 * @Email voidsun@126.com
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k==0){
            return head;
        }
        ListNode first = head;
        ListNode last = null;
        ListNode newHead = head;
        ListNode newLast = null;
        int length = 0;
        while(head != null){
            length++;
            last = head;
            head = head.next;
        }
        k = k%length;
        if(k == 0){
            return first;
        }
        k = length - k;
        while(k-->0){
            newLast = newHead;
            newHead = newHead.next;
        }
        newLast.next = null;
        last.next = first;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node = new RotateList().rotateRight(ListNode.buildList(1, 2, 3), 2);
        System.out.print(node);
    }
}

