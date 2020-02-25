package com.company.adjacentMatrixGraph;

/**
 * Implementacija na netezhinski nenasochen graf
 * so pomosh na matrica na sosedstvo
 */
public class Graph<E> {
    public int numNodes; //broj na jazli (teminja,tochki)
    public E nodes[]; //informacija vo jazlite
    public int adjMat[][]; //matrica na sosednost

    public Graph(int numNodes) {
        this.numNodes = numNodes;
        nodes = (E[]) new Object[numNodes];
        adjMat = new int [numNodes][numNodes];

        for(int i = 0; i<this.numNodes; i++) {
            for(int j = 0; j<this.numNodes; j++) {
                adjMat[i][j] = 0;
            }
        }
    }

    public int adjacent(int x, int y) {
        //proverka dali ima vrska od jazelot so indeks X do jazelot so indeks Y
        return (adjMat[x][y] != 0) ? 1 : 0;
    }

    public void addEdge(int x, int y) {
        //dodava vrska megu jazlite so indeks X i Y
        adjMat[x][y] = 1;
        adjMat[y][x] = 1;
    }

    public void deleteEdge(int x, int y) {
        //ja brishe vrskata megu jazlite so indeksi X i Y
        adjMat[x][y] = 0;
        adjMat[y][x] = 0;
    }

    public E getNodeValue(int x) {
        //ja vraka informacijata od jazelot vo indeks X
        return nodes[x];
    }

    public void setNodeValue(int x, E value) {
        //ja postavuva informacijata vo jazelot so indeks 'X' na 'value'
        nodes[x] = value;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public void setNumNodes(int numNodes) {
        this.numNodes = numNodes;
    }
}
