package com.lev1.ownPractice.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph{

    private List<Vertex> vertexList;   //图的顶点集
    private Map<Vertex, List<Edge>> ver_edgeList_map;  //图的每个顶点对应的有向边

    public Graph(List<Vertex> vertexList, Map<Vertex, List<Edge>> ver_edgeList_map) {
        super();
        this.vertexList = vertexList;
        this.ver_edgeList_map = ver_edgeList_map;
    }

    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }


    public Map<Vertex, List<Edge>> getVer_edgeList_map() {
        return ver_edgeList_map;
    }

    public void setVer_edgeList_map(Map<Vertex, List<Edge>> ver_edgeList_map) {
        this.ver_edgeList_map = ver_edgeList_map;
    }


    static class Edge{
        private Vertex startVertex;  //此有向边的起始点
        private Vertex endVertex;  //此有向边的终点
        private int weight;  //此有向边的权值

        public Edge(Vertex startVertex, Vertex endVertex, int weight) {
            super();
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public Edge()
        {}

        public Vertex getStartVertex() {
            return startVertex;
        }
        public void setStartVertex(Vertex startVertex) {
            this.startVertex = startVertex;
        }
        public Vertex getEndVertex() {
            return endVertex;
        }
        public void setEndVertex(Vertex endVertex) {
            this.endVertex = endVertex;
        }
        public int getWeight() {
            return weight;
        }
        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    static class Vertex {
        private final static int infinite_dis = Integer.MAX_VALUE;

        private String name;  //节点名字
        private boolean known; //此节点之前是否已知
        private int adjuDist; //此节点距离
        private Vertex parent; //当前从初始节点到此节点的最短路径下，的父节点。

        public Vertex()
        {
            this.known = false;
            this.adjuDist = infinite_dis;
            this.parent = null;
        }

        public Vertex(String name)
        {
            this.known = false;
            this.adjuDist = infinite_dis;
            this.parent = null;
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public boolean isKnown() {
            return known;
        }
        public void setKnown(boolean known) {
            this.known = known;
        }
        public int getAdjuDist() {
            return adjuDist;
        }
        public void setAdjuDist(int adjuDist) {
            this.adjuDist = adjuDist;
        }

        public Vertex getParent() {
            return parent;
        }

        public void setParent(Vertex parent) {
            this.parent = parent;
        }

        /**
         * 重新Object父类的equals方法
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vertex)) {
                throw new ClassCastException("an object to compare with a Vertext must be Vertex");
            }

            if (this.name==null) {
                throw new NullPointerException("name of Vertex to be compared cannot be null");
            }

            if (this == obj){
                return true;
            }

            return this.name.equals(obj);
        }
    }

    public void setRoot(Vertex v)
    {
        v.setParent(null);
        v.setAdjuDist(0);
//        v.setKnown(true);
    }


    /**
     *
     * @param startIndex dijkstra遍历的起点节点下标
     * @param destIndex dijkstra遍历的终点节点下标
     */
    public void dijkstraTravasal(int startIndex,int destIndex)
    {
        Vertex start = vertexList.get(startIndex);
        Vertex dest = vertexList.get(destIndex);
        String path = "["+dest.getName()+"]";

        setRoot(start);
        updateChildren(vertexList.get(startIndex));

        if (dest.parent != null){
            int shortest_length = dest.getAdjuDist();

            while((dest.getParent()!=null)&&(!dest.equals(start)))
            {
                path = "["+dest.getParent().getName()+"] --> "+path;
                dest = dest.getParent();
            }

            System.out.println("["+vertexList.get(startIndex).getName() +"] to ["+
                    vertexList.get(destIndex).getName()+"] dijkstra shortest path :: "+path);
            System.out.println("shortest length::"+shortest_length);
        } else {
            System.out.println("NO SUCH ROUTE");
        }

    }

    /**
     * 从初始节点开始递归更新邻接表
     * @param v
     */
    private void updateChildren(Vertex v)
    {
        if (v==null) {
            return;
        }

        if (ver_edgeList_map.get(v)==null||ver_edgeList_map.get(v).size()==0) {
            return;
        }
        //用来保存每个可达的节点
        List<Vertex> childrenList = new LinkedList<Vertex>();
        for(Edge e:ver_edgeList_map.get(v))
        {
            Vertex childVertex = e.getEndVertex();

            //如果子节点之前未知，则进行初始化，
            //把当前边的开始点默认为子节点的父节点，长度默认为边长加边的起始节点的长度，并修改该点为已经添加过，表示不用初始化
            if(!childVertex.isKnown())
            {
                childVertex.setKnown(true);
                childVertex.setAdjuDist(v.getAdjuDist()+e.getWeight());
                childVertex.setParent(v);
                childrenList.add(childVertex);
            }

            //此时该子节点的父节点和之前到该节点的最小长度已经知道了，则比较该边起始节点到该点的距离是否小于子节点的长度，
            //只有小于的情况下，才更新该点为该子节点父节点,并且更新长度。
            int nowDist = v.getAdjuDist()+e.getWeight();
            if(nowDist>=childVertex.getAdjuDist())
            {
                continue;
            }
            else {
                childVertex.setAdjuDist(nowDist);
                childVertex.setParent(v);
            }
        }

        //更新每一个子节点
        for(Vertex vc:childrenList)
        {
            updateChildren(vc);
        }
    }

}


