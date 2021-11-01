class Solution {
    public void rotate(int[][] matrix) {

        /**
         * 思路，向右旋转90度，即先对角翻转，再左右旋转
         */
        int m = matrix.length;
        int n = matrix[0].length;//其实正方形矩阵，m=n


        /**
         * 对角翻转
         */
        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < m; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        /**
         * 左右翻转
         */
        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < n/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }
}