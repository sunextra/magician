package com.voidsun.magician.leetcode;

import com.voidsun.magician.std.leetcode.TreeNode;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * @Description
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

    For example, you may serialize the following tree

    1
    / \
    2   3
    / \
    4   5
    as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * @Author voidsun
 * @Date 2015/10/30
 * @Email voidsun@126.com
 */
public class SerializeAndDeserializeBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return null;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);
        if(data.isEmpty()) return null;
        String[] strArray = data.split(",");
        TreeNode[] nodeArray = new TreeNode[strArray.length];
        nodeArray[0] = new TreeNode(Integer.parseInt(strArray[1]));
        int nextStart = 1;
        int nextEnd = 2;
        int pointer = 1;
        int nextLevel = 2;
        int nextNull = 0;
        while(pointer < strArray.length){
            int nullNode = nextNull;
            nextNull = 0;
            for(;pointer < nextEnd && pointer < strArray.length; pointer++){
                if(strArray[pointer].equals("null")){
                    nullNode++;
                    continue;
                }

            }
        }
        return nodeArray[0];
    }

    public static void main(String[] args) {
        new SerializeAndDeserializeBinaryTree().deserialize("[1,2,3,null,5,6,7,null,8,9]");
    }
}
