package com.company.adjacentListWeightedDirectedGraph;

public class GraphTester {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Character niza[] = new Character[8];
        niza[0]='A';
        niza[1]='B';
        niza[2]='C';
        niza[3]='D';
        niza[4]='E';
        niza[5]='F';
        niza[6]='G';
        niza[7]='H';
        Graph<Character> g = new Graph<Character>(8,niza);
        g.addEdge(0, 3, 5); //dodavame rebro od jazol 0(A) do jazol 3(D) so tezhina 5
        g.addEdge(0, 5, 5);
        g.addEdge(0, 7, 5);
        g.addEdge(1, 0, 5);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 4, 5);
        g.addEdge(3, 2, 5);
        g.addEdge(4, 3, 5);
        g.addEdge(5, 1, 5);
        g.addEdge(5, 2, 5);
        g.addEdge(7, 1, 5);
        g.addEdge(7, 6, 5);

        System.out.print(g);
        System.out.println("Dali se sosedi 0 i 1: "+g.adjacent(0, 1));
        System.out.println("Dali se sosedi 0 i 2: "+g.adjacent(0, 2));

        g.addEdge(0, 1, 6); //dodavam rebro od A do B tezina 6
        g.deleteEdge(1, 2); //brisham rebro od B do C

        System.out.println("Depth First Search Recursive:");
        g.dfsSearch(5);
        System.out.println("Depth First Search Nonrecursive:");
        g.dfsNonRecursive(5);
        System.out.println("Breath First Search:");
        g.bfs(5);
    }
}

