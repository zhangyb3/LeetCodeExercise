import java.util.ArrayList;
import java.util.List;

class Solution {

    List<Integer> path = new ArrayList<Integer>();
    int[] two = new int[2];

    private void dfs (int[] numbers, int target, int idx, List<Integer> path, int[] result) {
        if (idx <= numbers.length) {
            if (path.size() == 2 && numbers[path.get(0)] + numbers[path.get(1)] == target) {
                result[0] = path.get(0);
                result[1] = path.get(1);
                return;
            } else if (path.size() > 2) {
                return;
            }
        }

        for (int i = idx; i < numbers.length; i++) {
            path.add(i);
            dfs(numbers, target, i + 1, path, two);
            int size = path.size();
            path.remove(size-1);
        }
    }

    public int[] twoSum(int[] numbers, int target) {



        dfs(numbers,target,0,path,two);

        return two;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2,7,11,15};
        Solution solution = new Solution();
        solution.twoSum(numbers,18);
    }
}