import java.util.Arrays;

class Solution {
    public int countPaths(int n, int[][] roads) {
        if (n <= 2) return 1;
        // dist[i][j]表示 i, j 两点最短距离
        // ways[i][j]表示 i, j 两点最短距离的路径数量
        long[][] dist = new long[n][n], ways = new long[n][n];
        // 初始化
        for (int i = 0; i < n; ++i) Arrays.fill(dist[i], Long.MAX_VALUE);
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            dist[u][v] = dist[v][u] = time;
            ways[u][v] = ways[v][u] = 1;
        }
        // Floyd求最短路
        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                if (dist[i][k] == Long.MAX_VALUE) continue;
                // 只考虑i < j, 可利用对称性 dist[j][i] = dist[i][j] 减小一下常数
                for (int j = i + 1; j < n; ++j) {
                    if (dist[k][j] == Long.MAX_VALUE) continue;
                    if (dist[i][j] < dist[i][k] + dist[k][j]) continue;
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        // 碰到更短的路径，直接更新路径数
                        // 全局数量 = 左边数量 * 右边数量
                        ways[i][j] = ways[i][k] * ways[k][j];
                    } else {
                        // 碰到相等的路径，需要累加路径数
                        ways[i][j] += ways[i][k] * ways[k][j];
                    }
                    ways[j][i] = ways[i][j] %= 1000000007;
                    dist[j][i] = dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return (int) ways[0][n - 1];
    }
}
