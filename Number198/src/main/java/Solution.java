import java.util.Stack;

class Solution {

    /**
     * DP解法
     *
     * 写状态转移方程
     *
     * @param nums
     * @return
     */

    static int[] dpProfit = new int[]{};
    static Stack<Integer> indexes = new Stack<>();
    public int rob(int[] nums) {

        int houseQuantity = nums.length;

        if(houseQuantity < 2){
            return nums[0];
        }
        /**
         * dpProfit[i] 表示到i点时的最大得益
         */
        dpProfit = new int[houseQuantity];
        dpProfit[0] = nums[0];
        dpProfit[1] = Math.max(nums[0],nums[1]);
        for(int count = 2; count < houseQuantity; count++){
            System.out.println("now: " + count);
            if(nums[count] + dpProfit[count-2] > dpProfit[count-1]){
                indexes.push(count);
            }
            dpProfit[count] = Math.max(nums[count] + dpProfit[count-2] , dpProfit[count-1]);
        }



        return dpProfit[houseQuantity-1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,9,3,1};
        int result = new Solution().rob(nums);
        System.out.println(result);
    }
}