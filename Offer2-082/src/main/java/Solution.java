import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        int candidatesNum = candidates.length;
        List<List<Integer>> answers = new ArrayList<>();//记录合标准的答案
        Arrays.sort(candidates);
        backTraceCount(candidates, candidatesNum, 0, target, new ArrayList<>(), answers);
        return  answers;

    }

    private void backTraceCount(int[] candidates, int candidatesNum, int index, int target, List<Integer> list, List<List<Integer>> answers) {
        if (target == 0) {
            answers.add(new ArrayList<>(list));
            return ;
        }
        for (int i = index; i < candidatesNum; i++) {
            if (candidates[i] > target) { // 剪枝
                break;
            }
            if (i > index && candidates[i] == candidates[i - 1]) { // 剪枝、避免重复
                // 因为前面那个同样的数已经经历过dfs，再拿同样的数dfs就会得到重复的答案
                continue;
            }
            list.add(candidates[i]);
            backTraceCount(candidates, candidatesNum, i + 1, target - candidates[i], list, answers);
            list.remove(list.size() - 1);
        }

    }
}