import java.util.ArrayList;
import java.util.List;

class Solution {

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        DFS(graph, 0);
        return result;
    }

    public void DFS(int[][] graph, int cur) {
        //如果找到了一条符合条件的path，添加它到res，并结束此递归分支
        if (cur == graph.length-1) {
            path.add(cur);
            result.add(new ArrayList<>(path)); //返回时必须重新 new 一个 list
            path.remove(path.size()-1);
            return;
        }
        //如果没有找到，将此节点做为未来可能的path上的节点
        path.add(cur);
        for (int i: graph[cur]) {
            DFS(graph, i);
        }
        //在从此节点出发的分支全部处理完成后，删除此节点，避免将其加到别的不包含此节点path上
        path.remove(path.size()-1);
    }
}