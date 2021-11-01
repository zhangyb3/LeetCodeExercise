import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();//结果
        List<Integer> combination = new ArrayList<>();//每个排列组合的结果
        boolean[] used = new boolean[nums.length];//不能重复，所以需要记录

        dfs(nums, combination,results,used);

        return results;

    }

    private void dfs(int[] nums, List<Integer> combination, List<List<Integer>> results, boolean[] used) {
        if (combination.size() == nums.length) {
            results.add(new ArrayList<>(combination));//必须复制，combination只有一个引用，不复制的话，回溯时会把内容删光
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝，判断重复使用的数字
            if (used[i]) continue;
            combination.add(nums[i]);
            used[i] = true;
            dfs(nums, combination, results, used);
            // 回溯的过程中，将当前的节点从 combination 中删除
            combination.remove(combination.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Solution solution = new Solution();
        solution.permute(nums);
    }
}