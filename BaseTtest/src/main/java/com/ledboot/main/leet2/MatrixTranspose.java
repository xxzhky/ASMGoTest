package com.ledboot.main.leet2;

/**
 * @author: Fred
 * @date: 2024/2/21 18:32
 * @description: (类的描述)
 */
public class MatrixTranspose {
    public static int[][] transpose(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }

        }
        return transposed;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}};
        int[][] transposed = transpose(matrix);
        for (int i = 0; i < transposed.length; i++) {
            for (int j = 0; j < transposed[0].length; j++) {
                System.out.print(transposed[i][j] + " ");
            }
            System.out.println();
        }
    }
}
