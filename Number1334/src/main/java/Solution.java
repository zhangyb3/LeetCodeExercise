import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        //BFS解法
        //预处理
        int[][] arr = new int[n][n];
        for(int[] a : edges){
            arr[a[0]][a[1]] = a[2];
            arr[a[1]][a[0]] = a[2];
        }//邻接矩阵录入完毕
        int[] num = new int[n];
        Deque<int[]> q = new ArrayDeque<>();
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            q.addLast(new int[]{i,0});
            map.put(i,0);
            while(!q.isEmpty()){
                int[] temp = q.pollFirst();
                for(int j = 0; j < arr[temp[0]].length; j++){
                    if(arr[temp[0]][j] != 0 && (!map.containsKey(j) || map.get(j) > arr[temp[0]][j]+temp[1])
                            && arr[temp[0]][j]+temp[1] <= distanceThreshold){
                        q.addLast(new int[]{j,arr[temp[0]][j]+temp[1]});
                        map.put(j,arr[temp[0]][j]+temp[1]);
                    }
                }
            }
            num[i] = map.size()-1;
            map.clear();
        }
        int min = Integer.MAX_VALUE;
        int id = 0;
        for(int i=0; i<n; i++){
            if(num[i] <= min){
                id = i;
                min = num[i];
            }
        }
        return id;
    }
}