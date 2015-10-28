package com.voidsun.magician.std.leetcode;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/10/28
 * @Email voidsun@126.com
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode buildList(int... val){
        int size = val.length;
        ListNode next = null;
        while(size --> 0){
            ListNode node = new ListNode(val[size]);
            node.next = next;
            next = node;
        }
        return next;
    }

    public static void main(String[] args) {
        ListNode node = buildList(1, 2, 3, 4, 5);
        System.out.println(node);
    }
}
