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
                    mat[i][j] = 0;
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
        boolean stop = false;
        while(!qi.isEmpty() && !stop) {

            int ii = qi.poll();
            int jj = qj.poll();

            for(int i=0; i<di.length; i++) {
                if(ii+di[i] >= 0 && ii+di[i] < N && jj+dj[i] >= 0 && jj+dj[i] < M && maze[ ii+di[i] ][ jj+dj[i] ] != '#') {
                    if(mat[ii][jj] + 1 < mat[ ii+di[i] ][ jj+dj[i] ] || mat[ ii+di[i] ][ jj+dj[i] ] == -1) {
                        mat[ii+di[i]][jj+dj[i]] = mat[ii][jj] + 1;
                        qi.add(ii+di[i]);
                        qj.add(jj+dj[i]);
                        if(ii+di[i] == ei && jj+dj[i] == ej) {
                            stop = true;
                        }
                    }
                }
            }
        }

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
