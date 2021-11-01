class Solution {

    /**
     * 找连通域
     *
     * 典型并查集解法，全部遍历后，有多少根节点就有多少个连通域
     */



    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        UnionFind unionFind = new UnionFind(len+1);
        for(int i = 0; i < len; i++){
            if(unionFind.connected(edges[i][0],edges[i][1])){
                return new int[]{edges[i][0],edges[i][1]};
            }
            else{
                unionFind.union(edges[i][0],edges[i][1]);
            }
        }
        return new int[0];
    }
}

/**
 * 并查集类
 */
class UnionFind{
    private int count;
    private int[] parent;
    public UnionFind(int n){
        this.count = n;
        parent = new int[n];
        for(int i = 0;i<n;i++){
            parent[i] = i;
        }
    }

    //连接操作
    public void union(int a,int b){
        int parentA = findParent(a);
        int parentB = findParent(b);
        if(parentA == parentB) return;
        parent[parentA] = parentB;
        count--;
    }

    //判断是否连接的函数
    public boolean connected(int a,int b){
        return findParent(a) == findParent(b);
    }

    //递归式寻根
    public int findParent(int i){
        if(i != parent[i]){
            parent[i] = findParent(parent[i]); // 路径压缩
        }
        return parent[i];
    }


    public int getCount(){
        return this.count;
    }
}
