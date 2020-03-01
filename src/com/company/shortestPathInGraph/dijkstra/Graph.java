package com.company.shortestPathInGraph.dijkstra;


/**
 * Ovoj dijkstra algoritam e za nasochen tezhinski graf (ako e ne nasochen samo koga testirash dodaj gi site rebra
 * po dva pati i gotovo ke raboti, pr. ako ima rebro 3 -> 4 dodaj i teme 4 -> 3 so ista tezhina i taka za site rebra
 */

import java.util.Iterator;

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
    /***************** DIJKSTRA ******************************************************************************/

    public float[] dijkstra(int from) {

        /* Minimalna cena do sekoe od teminjata */
        float minDistances[] = new float[this.num_nodes];

        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean isFinal[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            minDistances[i] = -1;
            isFinal[i] = false;
        }

        isFinal[from] = true;
        minDistances[from] = 0;

        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.num_nodes; i++) {
            /* za site sledbenici na from presmetaj ja cenata */
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors().iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                /* ako grankata kon sosedot nema konecna cena */
                if (!isFinal[pom.node.getIndex()]) {
                    float newDistance = minDistances[from] + pom.weight;

                    /* ako ne e presmetana cena za temeto ili ako e pronajdena poniska cena */
                    if (minDistances[pom.node.getIndex()] <= 0 || newDistance <  minDistances[pom.node.getIndex()]) {
                        minDistances[pom.node.getIndex()] = newDistance;
                    }
                }
            }

            /* najdi teme so min. cena koja ne e konecna */
            int indexOfNodeToBeMadeFinal = -1;
            float minimumDistance = -1;

            /* proveri gi site teminja */
            for (int j = 0; j < this.num_nodes; j++) {
                if (minDistances[j] != -1 && !isFinal[j]) { /*ako ima cena i taa ne e konecna*/
                    /* proveri dali e pronajdeno teme so pomala cena */
                    if (minimumDistance == -1 || minimumDistance > minDistances[j]) {
                        minimumDistance = minDistances[j]; /* proglasi go ova e minimum */
                        indexOfNodeToBeMadeFinal = j;
                    }
                }
            }
            isFinal[indexOfNodeToBeMadeFinal] = true;
            from = indexOfNodeToBeMadeFinal; /* from represents next index to be considered */
        }

        return minDistances;

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