package com.voidsun.magician.algorithm.set;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * 不相交集合，主要应用场景用来解决无向图联通问题
 * @Author voidsun
 * @Date 2015/9/9
 * @Email voidsun@126.com
 */
public class DisjointSet<T extends Comparable> {
    Member<T> head;
    Member<T> tail;
    int size = 0;
    Map<T, Member<T>> indexOnMember = new HashMap<>();
    class Member<T>{
        Member<T> next;
        T t;
        public Member(T t){
            this.t = t;
        }
    }

    public DisjointSet(T t){
        Member<T> member = new Member(t);
        this.head = member;
        this.tail = member;
        indexOnMember.put(t, member);
        size = 1;
    }

    /**
     *  并集
     */
    public DisjointSet union(DisjointSet<T> anotherSet){
        DisjointSet<T> _this = this;
        if(_this.size < anotherSet.size){
            _this = anotherSet;
            anotherSet = this;
        }
        _this.indexOnMember.putAll(anotherSet.indexOnMember);
        _this.tail.next = anotherSet.head;
        _this.tail = anotherSet.tail;
        _this.size += anotherSet.size;
        return _this;
    }

    public boolean exists(T t){
        return indexOnMember.containsKey(t);
    }


    public static void main(String[] args) {
        DisjointSet<Integer> s1 = new DisjointSet("a");
        DisjointSet<Integer> s2 = new DisjointSet("b");
        DisjointSet<Integer> s3 = new DisjointSet("c");
        DisjointSet<Integer> s4 = new DisjointSet("d");
        DisjointSet<Integer> s5 = new DisjointSet("e");
        DisjointSet<Integer> s12 = s1.union(s2);
        DisjointSet<Integer> s123 = s12.union(s3);
        DisjointSet<Integer> s45 = s4.union(s5);
        DisjointSet<Integer> s12345 = s123.union(s45);

    }
}
