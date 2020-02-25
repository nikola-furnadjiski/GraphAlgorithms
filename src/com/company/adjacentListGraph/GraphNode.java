package com.company.adjacentListGraph;

import com.company.adjacentMatrixGraph.Graph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementacija na graf
 * so pomosh na lista na sosedstvo
 */
public class GraphNode<E> {
    public int index; //reden broj na temeto vo grafot
    public E info;
    LinkedList<GraphNodeNeighbor<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNodeNeighbor<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> node) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<>(node, 0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> node, float weight) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<>(node, weight);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> node) {
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<>(node, 0);
        if(neighbors.contains(pom))
            neighbors.remove(pom);
    }

    public void updateNeighborWeight(GraphNode<E> node, float weight) {
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while(i.hasNext()) {
            GraphNodeNeighbor<E> pom = i.next();
            if(pom.node.equals(node)) {
                pom.weight = weight;
            }
        }
    }
}
