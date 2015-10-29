package com.voidsun.magician.std.leetcode;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/10/29
 * @Email voidsun@126.com
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static TreeNode build(int value){
        return new TreeNode(value);
    }
}
