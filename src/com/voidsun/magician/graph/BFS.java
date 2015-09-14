package com.voidsun.magician.graph;

import java.util.LinkedList;

/**
 * @Description
 * 广度优先搜索(无向强连通图)
 * @Author voidsun
 * @Date 2015/9/10
 * @Email voidsun@126.com
 */
public class BFS {
    public static void search(ListGraph graph){
        ListGraph.Vertex vertex =  graph.getHead();
        LinkedList<ListGraph.Vertex> workList = new LinkedList<>();
        if(vertex != null){
            workList.add(vertex);
        }
        while(workList.peekFirst() != null){
            ListGraph.Vertex v = workList.pop();
            v.visit();
            System.out.print("visit " + v.get() + " link:");
            for(Object obj : v.getLinks()){
                ListGraph.Vertex nextV = graph.get(obj);
                if(!nextV.isVisit()){
                    workList.add(nextV);
                    System.out.print("[" + nextV.get() + "] ");
                }
                nextV.visit();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ListGraph<Integer> graph = new ListGraph(1, 2, 3, 4, 5);
        graph.dulLink(1, 2);
        graph.dulLink(4, 2);
        graph.dulLink(3, 2);
        graph.dulLink(4, 5);
        graph.dulLink(3, 5);
        BFS.search(graph);
    }
}
