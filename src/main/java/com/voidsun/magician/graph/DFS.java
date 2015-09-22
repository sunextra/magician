package com.voidsun.magician.graph;

/**
 * @Description
 * 深度优先检索
 * @Author voidsun
 * @Date 2015/9/14
 * @Email voidsun@126.com
 */
public class DFS {
    public static void search(ListGraph graph){
        ListGraph.Vertex vertex = graph.getHead();
        while(vertex != null){
            dfs(graph, vertex);
            System.out.println();
            vertex = vertex.next();
        }
    }

    static void dfs(ListGraph graph, ListGraph.Vertex vertex){
        if (vertex.visit()) return;
        System.out.print("[" + vertex.get() + "]");
        for(Object obj : vertex.getLinks()){
            dfs(graph, graph.get(obj));
        }
    }

    public static void main(String[] args) {
        ListGraph<Integer> graph = new ListGraph(1, 2, 3, 4, 5);
        graph.link(1, 2);
        graph.link(2, 3);
        graph.link(3, 1, 4);
        graph.link(4, 5);
        graph.link(6, 4, 7);
        graph.link(7, 5, 8);
        graph.link(4, 9);
        graph.link(9, 8);
        DFS.search(graph);
    }
}
