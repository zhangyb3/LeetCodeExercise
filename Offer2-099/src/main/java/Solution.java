class Solution {

    /**
     *
     * DP 3大特征
     *
     * 重复子问题
     *
     * 无后向性
     *
     * 最优子结构
     *
     *
     * 移动智能向右或向下，不能向左或向上
     */
    public int minPathSum(int[][] grid) {
        int min = 0;
//        min = backVersion(grid);
        min = frontVersion(grid);
        return min;
    }

    //终点回推版
    private int backVersion(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        // 状态定义：dp[i][j] 表示从坐标 (i, j) 到右下角的最小路径和
        int[][] dp = new int[m][n];

        /**
         * 假设grid是3*4数组
         *
         * 最后一行倒数第二个空格到终点的最短路径就是grid[2][2]+dp[2][3]
         * dp[i][j] = grid[i][j] + dp[i][j + 1]
         *
         * 最后一列倒数第二个空格到终点的最短路径就是grid[1][2]+dp[2][3]
         * dp[i][j] = grid[i][j] + dp[i + 1][j]
         *
         * 非最后一行一列的时候有两种可能，从左或上过来
         * dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1])
         */

        // 状态初始化
        dp[m - 1][n - 1] = grid[m - 1][n - 1];

        // 状态转移
        for (int i = m - 1; i >= 0 ; i--) {
            for (int j = n - 1; j >= 0 ; j--) {
                if (i == m - 1 && j != n - 1) { // 最后一行
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (i != m - 1 && j == n - 1) {// 最后一列
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else if (i != m - 1 && j != n - 1) {// 非最后一行一列
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        // 返回结果
        return dp[0][0];
    }

    //起点前进版
    private int frontVersion(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        // 状态定义：dp[i][j] 表示到grid[i][j]的最小路径和
        int[][] dp = new int[m][n];

        /**
         * 假设grid是3*4数组
         *
         * 第一行第二个空格到终点的最短路径就是dp[0][0]+grid[0][1]
         * dp[i][j] = dp[i][j - 1] + grid[i][j]
         *
         * 第一列第二个空格到终点的最短路径就是dp[0][0]+grid[1][0]
         * dp[i][j] = dp[i - 1][j] + grid[i][j]
         *
         * 非最后一行一列的时候有两种可能，从左或上过来
         * dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j]
         */

        // 状态初始化
        dp[0][0] = grid[0][0];

        // 状态转移
        for (int i = 0; i < m ; i++) {
            for (int j = 0; j < n ; j++) {
                if (i == 0 && j != 0) { // 第一行
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (i != 0 && j == 0) {// 第一列
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else if (i != 0 && j != 0) {// 非第一行一列
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }

        // 返回结果
        return dp[m-1][n-1];
    }
}
