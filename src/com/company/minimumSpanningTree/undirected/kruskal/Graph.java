package com.company.minimumSpanningTree.undirected.kruskal;

import com.company.minimumSpanningTree.undirected.Edge;
import com.company.minimumSpanningTree.undirected.GraphNode;

import java.util.ArrayList;
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
        } else{
            adjList[x].addNeighbor(adjList[y], tezina);
            adjList[y].addNeighbor(adjList[x], tezina);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
        adjList[y].removeNeighbor(adjList[x]);
    }

    /*******************************************************************************************************/
    /*************************** KRUSKAL ***********************************************************************/

    /**
     * Metoda koja generira niza shto gi sodrzhi site mozhni rebra vo grafot
     * @return Edge[] site rebra vo grafot
     */
    public Edge[] getAllEdges() {
        int totalEdges = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            // za sekoe teme go dodavame brojot na sosedi koi gi ima
            totalEdges += this.adjList[i].getNeighbors().size();
        }

        totalEdges /= 2; // bidejki e nenasocen graf, sekoe rebro se javuva kaj dve teminja
        Edge[] edges = new Edge[totalEdges];

        int index = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            for (int j = 0; j < this.adjList[i].getNeighbors().size(); j++) {
                // se zemaat indeksot 'i', indeksot 'j' i  tezinata na rebroto megu 'i' i 'j'
                int index1 = this.adjList[i].getIndex();
                int index2 = this.adjList[i].getNeighbors().get(j).node.getIndex();
                float weight = this.adjList[i].getNeighbors().get(j).weight;

                // bidejki grafot e nenasocen, da ne go zemame sekoe rebro dva pati
                // ne mozhe index2=index1 bidejki ne smee da ima jamki (vaka i da ima ne gi razgleduvame i raboti alg)
                if(index2>index1)
                    edges[index++] = new Edge(index1, index2, weight);
            }
        }

        return edges;
    }

    // Metoda koja gi sortira vo rastechki redosled (najmaliot e prv) site rebra
    private void sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }

    }

    //Metoda koja pravi unija na dve drva
    private void union(int u, int v, int[] vrski) {
        int findWhat, replaceWith;

        findWhat = vrski[Math.max(u,v)];
        replaceWith = vrski[Math.min(u,v)];


        //za dvete poddrva stava ist index
        //vo vrski t.e. gi spojuva
        for(int i=0; i<vrski.length; i++){
            if(vrski[i] == findWhat){
                vrski[i] = replaceWith;
            }
        }
    }

    List<Edge> kruskal() {
        /*
         * Pomoshna niza za sledenje na kreiranite drva
         * Ako dve teminja se del od isto poddrvo
         * togash imaat ista vrednost vo nizata
         */
        int vrski[] = new int[this.num_nodes];

        // niza koja gi sodrzi site rebra
        Edge[] edges = this.getAllEdges();
        // se sortiraat rebrata spored tezinite vo neopagjacki redosled
        this.sortEdges(edges);

        // inicijalizacija na pomosnata niza za evidencija,
        // sekoj jazel si e posebno drvo
        for (int i = 0; i < this.num_nodes; i++)
            vrski[i] = i;

        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        for(int i=0; i<edges.length; i++){
            //za sekoe rebro vo sortiran redosled
            Edge e = edges[i];

            if(vrski[e.getFrom()] != vrski[e.getTo()]){
                //ako teminjata na ova rebro ne se vo isto poddrvo
                //ova rebro e MST rebro
                mstEdges.add(e);
                //sega dvete teminja treba da se vo isto poddrvo
                //t.e se pravi merge (unija) t.s. vo nizata vrski
                //za site elementi od dvete poddrva
                //go setira istiot (najmal) element od dvete poddrva
                //vrski = this.union(e.getFrom(), e.getTo(), vrski);
                this.union(e.getFrom(), e.getTo(), vrski);
            }

            //ako sme dodale num_nodes-1 rebra moze da prestaneme
            if(mstEdges.size()==(this.num_nodes-1))
                break;
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
