import java.util.Arrays;

class Solution {
    public static int uniquePaths(int m, int n) {
        //dp[i][j]表示该点到达终点的走法
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = 1;
        for(int i = m-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                //最后一行
                if(i == m-1 && j != n-1){
                    dp[i][j] = dp[i][j+1];
                }
                //最后一列
                else if(i != m-1 && j == n-1){
                    dp[i][j] = dp[i+1][j];
                }
                else if(i != m-1 && j != n-1){
                    dp[i][j] = dp[i+1][j] + dp[i][j+1];
                }

            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3,7));
    }
}