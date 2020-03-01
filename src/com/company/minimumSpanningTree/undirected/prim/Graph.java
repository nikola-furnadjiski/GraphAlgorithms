package com.company.minimumSpanningTree.undirected.prim;

import com.company.minimumSpanningTree.undirected.Edge;
import com.company.minimumSpanningTree.undirected.GraphNode;
import com.company.minimumSpanningTree.undirected.GraphNodeNeighbor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// ZABELESHKA: Algoritmite na kruskal i prim se isti i za nasochen i za nenasochen graf, so toa shto za prim e totalno
// ist a za kruskal treba samo totalEdges da ne go delime so 2 i da ne proveruvame dali index2>index 1 koga gi dodavame
// rebrata (so ovaa proverka go dodavame sekoe rebro samo po ednash a bez nea go dodavame 2 pati dokolku go ima
// a->b i b->a)
public class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y, float tezina) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        // i obratno
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina);
            adjList[y].updateNeighborWeight(adjList[x], tezina);
        } else {
            adjList[x].addNeighbor(adjList[y], tezina);
            adjList[y].addNeighbor(adjList[x], tezina);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
        adjList[y].removeNeighbor(adjList[x]);
    }

    /*******************************************************************************************************/
    /************************ PRIM **************************************************************************/

    //Metoda koja go naogja najmaloto rebro od poseteno do neposeteno teme
    private Edge getMinimalEdge(boolean[] included) {
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        float minweight = Float.MAX_VALUE;

        for (int i = 0; i < this.num_nodes; i++) {
            if (included[i]) {
                //ako e vkluceno temeto 'i'
                //izmini gi negovite nevkluceni sosedi
                Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
                while (it.hasNext()) {
                    GraphNodeNeighbor<E> pom = it.next();
                    //ako sosedot ne e poseten i ima do sega najmala tezina
                    if (!included[pom.node.getIndex()] && pom.weight < minweight) {
                        index1 = i;
                        index2 = pom.node.getIndex();
                        minweight = pom.weight;
                    }
                }
            }
        }

        if (minweight < Float.MAX_VALUE) {
            Edge ret = new Edge(index1, index2, minweight);
            return ret;
        }
        return null;
    }


    List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        boolean included[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++)
            included[i] = false;

        included[start_index] = true;

        for (int i = 0; i < this.num_nodes - 1; i++) {
            Edge e = this.getMinimalEdge(included);
            if (e == null) {
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFrom()] = included[e.getTo()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }

    /*******************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += adjList[i] + "\n";
        return ret;
    }

}
