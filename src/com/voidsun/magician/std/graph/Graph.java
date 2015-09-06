package com.voidsun.magician.std.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author voidsun
 * @Date 2015/9/6
 * @Email voidsun@126.com
 */
public class Graph {
    Map<Integer, Node> nodeMap = new HashMap<>();
    Set<Edge> edgeSet = new HashSet<>();

    Node addNode(int id){
        Node node = new Node(id);
        nodeMap.put(id, node);
        return node;
    }

    Edge addEdge(int from, int to){
        Node fromNode = nodeMap.get(from);
        if(fromNode == null)fromNode = addNode(from);
        Node toNode = nodeMap.get(to);
        if(toNode == null)toNode = addNode(to);
        Edge edge = new Edge(fromNode, toNode);
        return edge;
    }
}
