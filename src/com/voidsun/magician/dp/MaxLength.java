package com.voidsun.magician.dp;

import java.util.*;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/8/20
 * @Email voidsun@126.com
 */
public class MaxLength {
    static Map<String, List<E>> fromEdgeMap = new HashMap<>();
    static Map<String, List<E>> toEdgeMap = new HashMap<>();

    Map<String, Integer> toMaxLength = new HashMap<>();

    void max(String V, int length, Integer preLength){
        if(preLength == null){
            preLength = 0;
        }
        if(toMaxLength.get(V) == null) {
            toMaxLength.put(V, 0);
        }
        if(length + preLength > toMaxLength.get(V)){
            toMaxLength.put(V, length + preLength);
        }
    }

    void maxLength(String start, String end){
        max(start, 0, 0);
        LinkedList<E> toList = new LinkedList<>();
        toList.addAll(fromEdgeMap.get(start));
        while(!toList.isEmpty()){
            E next = toList.pollFirst();
            String to = next.to;
            if(!to.equals(end)){
                if(fromEdgeMap.get(to) != null){
                    toList.addAll(fromEdgeMap.get(to));
                }
            }
            for(E fromE : toEdgeMap.get(to)){
                max(to, fromE.length, toMaxLength.get(fromE.from));
            }
        }
    }

    public static void main(String[] args) {
        new E("0", "1", 5);
        new E("0", "2", 5);
        new E("1", "3", 2);
        new E("1", "4", 4);
        new E("2", "5", 1);
        new E("3", "6", 2);
        new E("4", "7", 8);
        new E("5", "7", 3);
        new E("5", "8", 16);
        new E("7", "9", 4);
        new E("8", "9", 1);
        MaxLength maxLength = new MaxLength();
        maxLength.maxLength("0", "9");
        System.out.println(maxLength.toMaxLength.get("9"));
    }


}
class E{
    String from;
    String to;
    int length;

    E(String from, String to, int length){
        this.from = from;
        this.to = to;
        this.length = length;
        List<E> fromSet = MaxLength.fromEdgeMap.get(from);
        if(fromSet == null){
            fromSet = new ArrayList<>();
            MaxLength.fromEdgeMap.put(from, fromSet);
        }
        fromSet.add(this);

        List<E> toSet = MaxLength.toEdgeMap.get(to);
        if(toSet == null){
            toSet = new ArrayList<>();
            MaxLength.toEdgeMap.put(to, toSet);
        }
        toSet.add(this);
    }
}