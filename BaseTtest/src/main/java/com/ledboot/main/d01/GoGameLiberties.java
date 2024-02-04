package com.ledboot.main.d01;

import java.util.Scanner;

/**
 * @author: Fred
 * 华为OD 第一题
 */
public class GoGameLiberties {
    private static final int SIZE = 19;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] board = new int[SIZE][SIZE];

        String[] blackCoords = in.nextLine().split(" ");
        for (int i = 0; i < blackCoords.length; i += 2) {
            int x = Integer.parseInt(blackCoords[i]);
            int y = Integer.parseInt(blackCoords[i + 1]);
            board[x][y] = 1;//black
        }

        String[] whiteCoords = in.nextLine().split(" ");
        for (int i = 0; i < whiteCoords.length; i += 2) {
            int x = Integer.parseInt(whiteCoords[i]);
            int y = Integer.parseInt(whiteCoords[i + 1]);
            board[x][y] = 2;//white
        }

        int blackLiberties = calculateLiberties(board, 1);
        int whiteLiberties = calculateLiberties(board, 2);

        System.out.println(blackLiberties + " " + whiteLiberties);


    }

    private static int calculateLiberties(int[][] board, int color) {
        boolean[][] visited = new boolean[SIZE][SIZE];
        int liberties = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == color && !visited[i][j]) {
                    liberties += countLiberties(board, i, j, visited, color);
                }
            }
        }
        return liberties;
    }

    public static int countLiberties(int[][] board, int x, int y, boolean[][] visited, int color) {
        visited[x][y] = true;
        int liberties = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < SIZE && ny >= 0 && ny < SIZE) {
                if (board[nx][ny] == 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    liberties++;
                } else if (board[nx][ny] == color && !visited[nx][ny]) {
                    liberties += countLiberties(board, nx, ny, visited, color);
                }
            }
        }
        return liberties;
    }


}
