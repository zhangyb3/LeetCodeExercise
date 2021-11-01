import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    /**
     * 判断连通图有没有环
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //入度数组，入度为0的是根节点
        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // 统计各个课程的入度和邻接表
        for(int[] cp : prerequisites) {
            inDegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // 处理根节点
        for(int i = 0; i < numCourses; i++){
            if(inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        // BFS遍历，
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre)){
                inDegrees[cur]--;//从a到b后，削减入度，当入度为0，加入遍历的行列
                if(inDegrees[cur] == 0) {
                    queue.add(cur);
                }
            }

        }
        return numCourses == 0;
    }
}