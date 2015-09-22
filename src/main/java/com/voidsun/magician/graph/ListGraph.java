package com.voidsun.magician.graph;

import com.voidsun.magician.std.exception.graph.VertexExsistException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Description
 *
 * 图邻接链表表示法
 *
 * @Author voidsun
 * @Date 2015/9/10
 * @Email voidsun@126.com
 */
public class ListGraph<T> {
    Vertex<T> head;
    Vertex<T> tail;
    Map<T, Vertex<T>> indexOnVertex = new HashMap<>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Vertex<T> vertex = head;
        while(vertex != null){
            builder.append(vertex.vertex).append(vertex.links).append("\n");
            vertex = vertex.next;
        }
        return builder.toString();
    }

    public Vertex<T> getHead(){
        return head;
    }

    class Vertex<T>{
        public Vertex(T t){
            this.vertex = t;
        }
        T vertex;
        Vertex<T> next;
        LinkedList<T> links = new LinkedList<>();
        boolean visit = false;
        public LinkedList<T>
        getLinks(){
            return links;
        }
        public Vertex<T> next(){
            return next;
        }
        public T get(){
            return vertex;
        }

        public boolean visit() {
            if(!visit){
                this.visit = true;
                return false;
            }
            return true;
        }
    }

    public ListGraph(T... ts){
        for(T t : ts){
            add(t);
        }
    }

    public Vertex<T> add(T t){
        if(indexOnVertex.get(t) != null){
            throw new VertexExsistException();
        }
        Vertex<T> vertex = new Vertex<>(t);
        if(head == null){
            head = vertex;
        }else{
            tail.next = vertex;
        }
        tail = vertex;
        indexOnVertex.put(t, vertex);
        return vertex;
    }

    public void link(T t1, T... links){
        Vertex<T> v1 = indexOnVertex.get(t1);
        if(v1 == null){
            v1 = add(t1);
        }
        for(T link : links){
            if(indexOnVertex.get(link) == null){
                add(link);
            }
            v1.links.add(link);
        }
    }

    public Vertex<T> get(T t){
        return indexOnVertex.get(t);
    }

    public void dulLink(T t1, T t2){
        link(t1, t2);
        link(t2, t1);
    }

    public static void main(String[] args) {
        ListGraph<Integer> graph = new ListGraph(1, 2, 3, 4, 5);
        graph.link(1, 2, 5);
        graph.link(2, 1, 5, 4, 3);
        graph.link(3, 2, 4);
        graph.link(4, 2, 3, 5);
        graph.link(5, 1, 2, 4);
    }

}
