package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import static com.voidsun.magician.std.leetcode.TreeNode.build;

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * @Description
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

\\\\\\
    For example:
    Given binary tree {3,9,20,#,#,15,7},
    3
    / \
    9  20
    /  \
    15   7
    return its zigzag level order traversal as:
    [
    [3],
    [20,9],
    [15,7]
    ]
 * @Author voidsun
 * @Date 2015/10/29
 * @Email voidsun@126.com
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> leftInStack = new Stack<>();
        Stack<TreeNode> rightInStack = new Stack<>();
        if(root != null) rightInStack.add(root);
        List<Integer> pathList;
        while(!leftInStack.isEmpty() || !rightInStack.isEmpty()){
            pathList = new ArrayList<>();
            while(!leftInStack.isEmpty()){
                TreeNode node = leftInStack.pop();
                if(node.right != null)rightInStack.push(node.right);
                if(node.left != null)rightInStack.push(node.left);
                pathList.add(node.val);
            }
            if(!pathList.isEmpty())result.add(pathList);
            pathList = new ArrayList<>();
            while(!rightInStack.isEmpty()){
                TreeNode node = rightInStack.pop();
                if(node.left != null)leftInStack.push(node.left);
                if(node.right != null)leftInStack.push(node.right);
                pathList.add(node.val);
            }
            if(!pathList.isEmpty())result.add(pathList);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = build(1);
        root.left = build(2);
        root.right = build(3);
        root.right.left = build(4);
        root.right.right = build(5);
        new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root);
    }
}
