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
    Map<String, Node> nodeMap = new HashMap<>();
    Set<Edge> edgeSet = new HashSet<>();

    public Node addNode(String id){
        Node node = new Node(id);
        nodeMap.put(id, node);
        return node;
    }

    public Edge addEdge(String from, String to){
        Node fromNode = nodeMap.get(from);
        if(fromNode == null)fromNode = addNode(from);
        Node toNode = nodeMap.get(to);
        if(toNode == null)toNode = addNode(to);
        Edge edge = new Edge(fromNode, toNode);
        edgeSet.add(edge);
        fromNode.link(toNode);
        toNode.link(fromNode);
        return edge;
    }
}
