package com.voidsun.magician.std.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/6
 * @Email voidsun@126.com
 */
public class Node {
    String id;
    Set<Node> linkedSet = new HashSet<>();

    public Node(String id) {
        this.id = id;
    }

    Node link(Node node){
        return link(node, true);
    }

    Node link(Node node, boolean callback){
        linkedSet.add(node);
        if(callback){
            node.link(this, false);
        }
        return this;
    }
}
