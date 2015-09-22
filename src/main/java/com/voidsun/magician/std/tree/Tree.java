package com.voidsun.magician.std.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/14
 * @Email voidsun@126.com
 */
public class Tree<T> {
    Map<T, Node<T>> indexOnNodes = new HashMap<>();
    Node<T> root;

    public Node<T> getRoot(){
        return root;
    }

    public Node<T> setRoot(T t){
        this.root = get(t);
        return root;
    }

    public Node<T> get(T t){
        Node<T> node = indexOnNodes.get(t);
        if(node != null){
            return node;
        }
        node = new Node<T>(t);
        indexOnNodes.put(t, node);
        return node;
    }

    class Node<T> {
        T t;
        Node<T> parent;
        LinkedList<Node<T>> children = new LinkedList<>();

        public Node(T t){
            this.t = t;
        }
        public T value(){
            return t;
        }
        void setParent(Node node){

        }
        public void addChild(T t){
            Node node = new Node(t);
            this.children.add(node);
            node.setParent(this);
        }
    }

}
