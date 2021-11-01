

class Solution {


    public int makeConnected(int n, int[][] connections) {
        //如果初始布线数小于 n - 1，那么一定不能使所有计算机连通
        if (connections.length < n - 1) {
            return -1;
        }

        //并查集初始化
        UnionFind unionFind = new UnionFind(n);

        //并查集检查有多少个连通分量
        for (int[] connection:
             connections) {
            unionFind.union(connection[0],connection[1]);
        }

        return unionFind.getCount();
    }
}