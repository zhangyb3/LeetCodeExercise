class Solution {
    public int uniquePaths(int m, int n) {
        int sum = 0;
        sum = frontVersion(m,n);
        return sum;
    }

    private int frontVersion(int m, int n){

        //dp[i][j]表示到grid[i][j]的路径数
        int[][] dp = new int[m][n];

        dp[0][0] = 1;

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j != 0){//第一行
                    dp[i][j] = dp[i][j - 1];
                }else if(i != 0 && j == 0){//第一列
                    dp[i][j] = dp[i - 1][j];
                }else if(i != 0 && j != 0){
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }

        return dp[m-1][n-1];
    }
}