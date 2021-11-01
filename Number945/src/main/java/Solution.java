import java.util.Arrays;

class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);

        int result = 0;
        for (int count = 1; count < nums.length; count++) {
            if (nums[count] == nums[count - 1]) {
                nums[count] = nums[count-1] + 1;
                result++;
            }else if (nums[count] < nums[count - 1]) {
                int gap = nums[count-1] - nums[count];
                nums[count] = nums[count-1] + 1;
                result = result + (gap + 1);
            }

            System.out.println(count + "," +nums[count] + "," +result);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,2,1,7};
        new Solution().minIncrementForUnique(nums);
    }
}