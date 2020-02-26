package com.company.listWeightedDirectedGraph;

/**
 *  Sekoj sosed na jazol se sostoi od jazol i tezhina na rebroto
 */
public class GraphNodeNeighbor<E> {
    private GraphNode<E> node;
    private  float weight;

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight = 0;
    }

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }

    // After this line come only setters and getters.

    public GraphNode<E> getNode() {
        return node;
    }

    public float getWeight() {
        return weight;
    }

    public void setNode(GraphNode<E> node) {
        this.node = node;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
