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
    Node[] hTable = null;
    int hash = 0;
    Node head = new Node();
    Node tail = new Node();

    class Node{
        public int k;
        public int v;
        public Node(){}
        public Node(int k, int v){
            this.k = k;
            this.v = v;
        }
        Node prev = null;
        Node next = null;
        Node hPrev = null;
        Node hNext = null;
    }

    int hash(int k){
        return k % hash;
    }

    void append(Node node){
        size++;
        Node prev = tail.prev;
        tail.prev = node;
        prev.next = node;
        node.prev = prev;
        node.next = tail;
        hSet(node);
    }

    void remove(Node findNode){
        size--;
        findNode.prev.next = findNode.next;
        findNode.next.prev = findNode.prev;
        hRemove(findNode);
    }

    void hSet(Node node){
        int hKey = hash(node.k);
        Node findNode = hTable[hKey];
        if(findNode == null){
            hTable[hKey] = node;
        }else{
            hTable[hKey] = node;
            node.hNext = findNode;
            findNode.hPrev = node;
        }
    }

    void hRemove(Node node){
        if(node.hPrev != null){
            node.hPrev.hNext = node.hNext;
            if(node.hNext != null) node.hNext.hPrev = node.hPrev;
        }else{
            hTable[hash(node.k)] = node.hNext;
            if(node.hNext != null){
                node.hNext.hPrev = null;
            }
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        int i = 16;
        while(i < capacity){
            i = i<<1;
        }
        hash = i<<1 - 1;
        hTable = new Node[hash];
        head.next = tail;
        tail.prev = head;
    }

    Node getNode(int key){
        Node node = hTable[hash(key)];
        while(node != null){
            if(node.k == key){
                return node;
            }
            node = node.hNext;
        }
        return null;
    }

    public int get(int key) {
        Node node = getNode(key);
        if(node != null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            Node prev = tail.prev;
            prev.next = node;
            tail.prev = node;
            node.prev = prev;
            node.next = tail;
            return node.v;
        }
        return -1;
    }


    public void set(int key, int value) {
        Node node = new Node(key, value);
        Node findNode = getNode(key);
        if(findNode != null){
            remove(findNode);
        }
        append(node);
        if(size > capacity){
            remove(head.next);
        }
    }
}