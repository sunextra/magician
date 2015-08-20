package com.voidsun.magician.dp;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/8/20
 * @Email voidsun@126.com
 */
public class MaxLength {
    Map<Integer, List<E>> fromEdgeMap;
    Map<Integer, List<E>> toEdgeMap;

    Map<Integer, Integer> toMaxLength;

    void max(int V, int length){
        if(toMaxLength.get(V) == null) {
            toMaxLength.put(V, 0);
        }
        if(length > toMaxLength.get(V)){
            toMaxLength.put(V, length);
        }
    }
    void add(int V, int length){
        if(toMaxLength.get(V) == null) {
            toMaxLength.put(V, 0);
        }
        if(length > toMaxLength.get(V)){
            toMaxLength.put(V, toMaxLength.get(V) + length);
        }
    }

    void maxLength(int start, int end){
        max(start, 0);
        LinkedList<E> toList = new LinkedList<>();
        toList.addAll(fromEdgeMap.get(start));
        while(!toList.isEmpty()){
            E next = toList.pop();
            int from = next.from;
            int to = next.to;
            if(to != end){
                toList.addAll(fromEdgeMap.get(to));
            }
            for(E fromE : toEdgeMap.get(to)){
                max(to, fromE.length);
            }
            add(to, toMaxLength.get(from));
        }
    }


}
class E{
    int from;
    int to;
    int length;
}