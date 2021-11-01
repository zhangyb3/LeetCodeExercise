class Solution {

    /**
     * 找连通域
     *
     * 典型并查集解法，全部遍历后，有多少根节点就有多少个连通域
     */
    int[] father;

    public int findFather(int i){
        if(i != father[i]){
            father[i] = findFather(father[i]); // 路径压缩
        }
        return father[i];
    }

    public void union(int i, int j){
        int fi = findFather(i);
        int fj = findFather(j);
        if(fi != fj){
            father[fj] = fi;
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        father = new int[n];
        for(int i=0; i<n; i++){
            father[i] = i;
        }
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isConnected[i][j] == 1){
                    union(i, j);
                }
            }
        }
        int cnt = 0;
        for(int i=0; i<n; i++){
            if(father[i] == i){
                cnt++;
            }
        }
        return cnt;
    }
}
