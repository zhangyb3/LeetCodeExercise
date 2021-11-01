import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> answers = new ArrayList<List<Integer>>();
        List<Integer> answer = new ArrayList<Integer>();

        //回溯解法
        dfs(candidates, target, answers, answer, 0);
        return answers;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> answers, List<Integer> answer, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            answers.add(new ArrayList<Integer>(answer));
            return;
        }
        // 直接跳过，因为可以重复取
        dfs(candidates, target, answers, answer, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            answer.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], answers, answer, idx);
            answer.remove(answer.size() - 1);
        }
    }
}
