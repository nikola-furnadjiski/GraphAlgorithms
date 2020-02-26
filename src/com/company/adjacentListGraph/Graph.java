package com.company.adjacentListGraph;

public class Graph<E> {
    public int numNodes;
    public GraphNode<E>[] adjList;

    public Graph(int numNodes, E[] list) {
        this.numNodes = numNodes;
        adjList = (GraphNode<E>[]) new GraphNode[numNodes];
        for(int i=0; i<numNodes; i++) {
            adjList[i] = new GraphNode<E>(i,list[i]);
        }
    }

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjList = (GraphNode<E>[]) new GraphNode[numNodes];
    }

    public int adjacent(int x, int y) {
        //proveruva dali ima vrska od jazelot so indeks X do jazelot so indeks Y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    public void addEdge(int x, int y, float weight) {
        if(adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y],weight);
        } else {
            adjList[x].addNeighbor(adjList[y],weight);
        }
    }

    public void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    public void dfsSearch(int node) {
        boolean visited[] = new boolean[numNodes];
        for(int i=0; i<numNodes; i++) {
            visited[i] = false;
        }
        dfsRecursive(node, visited);
    }

    private void dfsRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].info);

        for(int i=0; i<adjList[node].neighbors.size(); i++) {
            GraphNode<E> pom = adjList[node].neighbors.get(i);
            if(!visited[pom.index]) {
                dfsRecursive(pom.index, visited);
            }
        }
    }
}
