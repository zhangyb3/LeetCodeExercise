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

        if(houseQuantity == 1){
            return nums[0];
        }
        if(houseQuantity == 2){
            return Math.max(nums[0],nums[1]);
        }
        if(houseQuantity == 3){

            return Math.max(Math.max(nums[0],nums[1]), nums[2]);
        }

        int maxProfit = 0;

        /**
         * 环状，意思就是第一和倒数第一只能有一个被偷
         *
         * 即在1至n-1最大得益和2至n最大得益之间选更大那个
         */
        maxProfit = Math.max(anotherRob(nums,0,houseQuantity-1),anotherRob(nums,1,houseQuantity));

        return maxProfit;
    }

    private int anotherRob(int[] nums, int start, int end){
        int houseQuantity = nums.length;


        /**
         * dpProfit[i] 表示到i点时的最大得益
         */
        dpProfit = new int[houseQuantity];
        dpProfit[start] = nums[start];
        dpProfit[start+1] = Math.max(nums[start],nums[start+1]);
        for(int count = start+2; count < end; count++){
            System.out.println("now: " + count);
            if(nums[count] + dpProfit[count-2] > dpProfit[count-1]){
                indexes.push(count);
            }
            dpProfit[count] = Math.max(nums[count] + dpProfit[count-2] , dpProfit[count-1]);
        }
        System.out.println(dpProfit[end-1]);
        return dpProfit[end-1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,1,1,2};
        int result = new Solution().rob(nums);
        System.out.println(result);
    }
}