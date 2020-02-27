package com.company.adjacentListWeightedDirectedGraph;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementacija na nasochen tezhinski graf
 * so pomosh na lista na sosedstvo
 */
public class GraphNode<E> {
    private int index; //reden broj na temeto vo grafot
    private E info;
    private LinkedList<GraphNodeNeighbor<E>> neighbors;

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
            if(pom.getNode().equals(node)) {
                pom.setWeight(weight);
            }
        }
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+="("+neighbors.get(i).getNode().info+","+neighbors.get(i).getWeight()+") ";
        return ret;

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    // After this line come only setters and getters.

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
        this.neighbors = neighbors;
    }
}
