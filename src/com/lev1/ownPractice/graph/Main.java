package com.lev1.ownPractice.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.lev1.ownPractice.graph.Graph.Edge;
import com.lev1.ownPractice.graph.Graph.Vertex;

/**
 * 测试用main方法
 * @author wuhui.wwh
 *
 */
public class Main {
    public static void main(String[] args) {
        Vertex A= new Vertex("A");
        Vertex B= new Vertex("B");
        Vertex C= new Vertex("C");
        Vertex D= new Vertex("D");
        Vertex E= new Vertex("E");

        List<Vertex> verList = new LinkedList<Graph.Vertex>();
        verList.add(A);
        verList.add(B);
        verList.add(C);
        verList.add(D);
        verList.add(E);

        Map<Vertex, List<Edge>> vertex_edgeList_map = new HashMap<Graph.Vertex, List<Edge>>();

        List<Edge> AList = new LinkedList<Graph.Edge>();
        AList.add(new Edge(A,B,5));
        AList.add(new Edge(A,D,5));
        AList.add(new Edge(A,E,7));

        List<Edge> BList = new LinkedList<Graph.Edge>();
        BList.add(new Edge(B,C,4));

        List<Edge> CList = new LinkedList<Graph.Edge>();
        CList.add(new Edge(C,D,8));
        CList.add(new Edge(C,E,2));

        List<Edge> DList = new LinkedList<Graph.Edge>();
        DList.add(new Edge(D,C,8));
        DList.add(new Edge(D,E,6));

        List<Edge> EList = new LinkedList<Graph.Edge>();
        EList.add(new Edge(E,B,3));

        vertex_edgeList_map.put(A, AList);
        vertex_edgeList_map.put(B, BList);
        vertex_edgeList_map.put(C, CList);
        vertex_edgeList_map.put(D, DList);
        vertex_edgeList_map.put(E, EList);


        Graph g = new Graph(verList, vertex_edgeList_map);
        g.dijkstraTravasal(0, 4);
    }
}
