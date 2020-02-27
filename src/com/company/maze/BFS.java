package com.company.maze;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void findPath(char[][] maze) {
        int[] di = {0, 0, -1, 1};
        int[] dj = {-1, 1, 0, 0};

        int N=maze.length;
        int M=maze[0].length;

        int si=-1,sj=-1,ei=-1,ej=-1;
        int[][] mat = new int[N][M];

        for(int i=0; i<N; i++) {
            for (int j = 0; j <M; j++) {
                mat[i][j] = -1;
            }
        }

        for(int i=0; i<maze.length; i++) {
            for(int j=0; j<maze[i].length; j++) {
                if(maze[i][j] == 'S') {
                    si=i;
                    sj=j;
                } else if (maze[i][j] == 'E') {
                    ei=i;
                    ej=j;
                }
            }
        }

        Queue<Integer> qi = new LinkedList<>();
        Queue<Integer> qj = new LinkedList<>();
        qi.add(si);
        qj.add(sj);
        mat[si][sj] = 0;
        while(!qi.isEmpty()) {

            int ii = qi.poll();
            int jj = qj.poll();

            for(int i=0; i<di.length; i++) {
                int newi = ii + di[i];
                int newj = jj + dj[i];
                if(newi >= 0 && newi < N && newj >= 0 && newj < M && maze[newi][newj] != '#' && mat[newi][newj] == -1) {
                        mat[newi][newj] = mat[ii][jj] + 1;
                        qi.add(newi);
                        qj.add(newj);
                }
            }
        }

        //just for printing the maze, not part of the algorithm
        for(int i=0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(mat[i][j] < 9 && mat[i][j] != -1) {
                    System.out.print(" ");
                }
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println(mat[ei][ej]);
    }

    public static void main(String[] args) {
        char[][] in = new char[][]{ "######".toCharArray(),
                                    "# # ##".toCharArray(),
                                    "# # S#".toCharArray(),
                                    "# # ##".toCharArray(),
                                    "# E  #".toCharArray(),
                                    "######".toCharArray()};

        findPath(in);
    }
}
