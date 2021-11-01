class Solution {

    int answer = 0;
    int MAX = 15;
    public int countArrangement(int n) {
        int[] visited = new int[n + 1];//n+1比较直观，下标从1开始到n
        countArrangement(1, n, visited);
        return answer;
    }

    private void countArrangement(int step, int N,int[] visited){
        if(step == N+1) {
            answer++;
            return;
        }
        for(int i = 1; i <= N; i++) {
            //用过的不能再次使用，避免重复
            if(visited[i] == 0) {
                visited[i] = 1;
                //限制条件进行，剪枝
                if(i % step == 0 || step % i == 0) {
                    countArrangement(step+1,N,visited);
                }
                visited[i] = 0;
            }
        }
    }
}