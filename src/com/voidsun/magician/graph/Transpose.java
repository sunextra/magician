package com.voidsun.magician.graph;

import java.util.LinkedList;

/**
 * @Description
 *
 * 矩阵转置
 * G = (V, E)  Gt = (V, Et)
 *
 * @Author voidsun
 * @Date 2015/9/10
 * @Email voidsun@126.com
 */
public class Transpose<T> {
    ListGraph<T> transpose(ListGraph<T> graph){
        ListGraph.Vertex vertex = graph.getHead();
        ListGraph<T> transGraph = new ListGraph<>();
        while(vertex != null){
            LinkedList<T> links = vertex.getLinks();
            for(T t : links){
                transGraph.link(t, (T)vertex.get());
            }
            vertex = vertex.next();
        }
        return transGraph;
    }

    public static void main(String[] args) {
        ListGraph<Integer> graph = new ListGraph(1, 2, 3, 4, 5);
        graph.link(1, 2, 5);
        graph.link(2, 4, 3);
        graph.link(3, 2);
        graph.link(4, 3);
        graph.link(5, 4);
        System.out.print(graph);
        ListGraph<Integer> that = new Transpose<Integer>().transpose(graph);
        System.out.print(that);
    }
}
