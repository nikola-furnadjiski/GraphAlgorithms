package com.company.adjacentListGraph;

/**
 *  Sekoj sosed na jazol se sostoi od jazol i tezhina na rebroto
 */
public class GraphNodeNeighbor<E> {
    public GraphNode<E> node;
    public float weight;

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }
}
