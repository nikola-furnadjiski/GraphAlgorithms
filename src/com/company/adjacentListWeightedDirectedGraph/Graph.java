package com.company.adjacentListWeightedDirectedGraph;

import java.util.Stack;

public class Graph<E> {
    private int numNodes;
    private GraphNode<E>[] adjList;

    @SuppressWarnings("unchecked")
    public Graph(int numNodes, E[] list) {
        this.numNodes = numNodes;
        adjList = (GraphNode<E>[]) new GraphNode[numNodes];
        for(int i=0; i<numNodes; i++) {
            adjList[i] = new GraphNode<>(i,list[i]);
        }
    }

    @SuppressWarnings("unchecked")
    public Graph(int numNodes) {
        this.numNodes = numNodes;
        adjList = (GraphNode<E>[]) new GraphNode[numNodes];
    }

    public int adjacent(int x, int y) {
        //proveruva dali ima vrska od jazelot so indeks X do jazelot so indeks Y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    public void addEdge(int x, int y, float weight) {
        // dodava vrska od jazelot so indeks X do jazelot so indeks Y so tezina
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
        boolean[] visited = new boolean[numNodes];
        for(int i=0; i<numNodes; i++) {
            visited[i] = false;
        }
        dfsRecursive(node, visited);
    }

    private void dfsRecursive(int node, boolean[] visited) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for(int i=0; i<adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i).getNode();
            if(!visited[pom.getIndex()]) {
                dfsRecursive(pom.getIndex(), visited);
            }
        }
    }

    public void dfsNonRecursive(int node) {
        boolean[] visited = new boolean[numNodes];
        for(int i=0; i<numNodes; i++)
            visited[i] = false;

        System.out.println(node+": "+adjList[node].getInfo());
        visited[node] = true;

        Stack<GraphNode<E>> s = new Stack<>();
        s.push(adjList[node]);

        GraphNode<E> pom = null;

        while(!s.isEmpty()) {
            pom = s.peek();
            GraphNode<E> tmp = null;

            for(int i=0; i<pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i).getNode();
                if(!visited[tmp.getIndex()]) {
                    break;
                }
            }
            if(tmp != null && !visited[tmp.getIndex()]) {
                System.out.println(tmp.getIndex()+": "+tmp.getInfo());
                visited[tmp.getIndex()] = true;
                s.push(tmp);
            } else {
                s.pop();
            }
        }
    }

    public void bfs(int node) {
        boolean[] visited = new boolean[numNodes];
        for(int i=0; i<numNodes; i++)
            visited[i] = false;

        System.out.println(node+": "+adjList[node].getInfo());
        visited[node] = true;
        Queue<GraphNode<E>> q = new LinkedQueue<>();
        q.enqueue(adjList[node]);

        GraphNode<E> pom = null;
        while(!q.isEmpty()) {
            pom = q.dequeue();
            for(int i=0; i<pom.getNeighbors().size(); i++) {
                GraphNode<E> tmp = pom.getNeighbors().get(i).getNode();
                if(!visited[tmp.getIndex()]) {
                    System.out.println(tmp.getIndex()+": "+tmp.getInfo());
                    visited[tmp.getIndex()] = true;
                    q.enqueue(tmp);
                }
            }
        }
    }

    @Override
    public String toString() {
        String ret = new String();
        for(int i=0;i<this.numNodes;i++)
            ret+=adjList[i]+"\n";
        return ret;
    }

    // After this line come only setters and getters.

    public int getNumNodes() {
        return numNodes;
    }

    public GraphNode<E>[] getAdjList() {
        return adjList;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }

    public void setAdjList(GraphNode<E>[] adjList) {
        this.adjList = adjList;
    }
}
