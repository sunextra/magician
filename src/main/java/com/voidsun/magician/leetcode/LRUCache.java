package com.voidsun.magician.leetcode;

/**
 * https://leetcode.com/submissions/detail/45540975/
 * @Description
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

    get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
    set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * @Author voidsun
 * @Date 2015/11/10
 * @Email voidsun@126.com
 */
public class LRUCache {
    int capacity = 0;
    int size = 0;
    Node head = null;
    Node end = null;

    class Node{
        public int key;
        public int val;
        public Node prev;
        public Node next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    Node getNode(int key){
        Node temp = head;
        while(temp != null){
            if(temp.key == key) return temp;
            temp = temp.next;
        }
        return temp;
    }
    public int get(int key) {
        Node node = getNode(key);
        if(node != null){
            return node.val;
        }
        return -1;
    }

    public void set(int key, int value) {
        if(head == null) {
            append(new Node(key, value));
            return;
        }
        Node node = getNode(key);
        if(node != null){
            remove(node);
            append(new Node(key, value));
            return;
        }
        append(new Node(key, value));
        if(capacity < size){
            remove(head);
        }
    }

    void append(Node node){
        size++;
        if(end == null){
            head = node;
            end = node;
            return;
        }
        end.next = node;
        node.prev = end;
        end = node;
    }

    void remove(Node node){
        size--;
        Node prev = node.prev;
        Node next = node.next;
        if(prev != null){
            prev.next = next;
        }else{
            head = next;
        }
        if(next != null){
            next.prev = prev;
        }else{
            end = prev;
        }
    }
}
