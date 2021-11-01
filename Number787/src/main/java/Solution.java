class Solution {

    int INF = 1000007;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // k表示经过的节点，我们转成边数（步数），这样好计算一些
        int[][] memo = new int[n][k+2];
        int ans = dfs(flights, src, dst, k + 1, memo);
        return ans >= INF ? -1 : ans;
    }

    // 表示从 src 到 dst 的走 k 步的最小价格
    private int dfs(int[][] flights, int src, int dst, int k, int[][] memo) {
        if (k < 0) {
            return INF;
        }

        if (src == dst) {
            return 0;
        }

        if (memo[src][k] != 0) {
            return memo[src][k];
        }

        int min = INF;
        for (int[] flight : flights) {
            // 遍历 src 的下一个节点，不停选择临近节点最近的节点加入，每前进一步，k的步数减一
            if (flight[0] == src) {
                min = Math.min(min, dfs(flights, flight[1], dst, k - 1, memo) + flight[2]);
            }
        }

        memo[src][k] = min;

        return min;
    }

    public static void main(String[] args) {
        int[][] flights = new int[][]{{0,1,100},{1,2,100},{0,2,500}};
        int src = 0, dst = 2, k = 1;

        int ans = new Solution().findCheapestPrice(3,flights,src,dst,1);



        int[][] test = new int[3][];
        for(int count = 0; count < test.length; count ++){
            test[count] = new int[1];
            test[count][0] = 1;
        }

        System.out.println(ans);
    }
}