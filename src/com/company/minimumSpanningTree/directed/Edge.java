package com.company.minimumSpanningTree.directed;

public class Edge{
    private int fromVertex, toVertex;
    private float weight;
    public Edge(int from, int to, float weight){
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom(){
        return this.fromVertex;
    }
    public int getTo(){
        return this.toVertex;
    }
    public float getWeight(){
        return this.weight;
    }
}