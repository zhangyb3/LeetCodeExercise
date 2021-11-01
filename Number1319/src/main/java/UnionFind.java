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