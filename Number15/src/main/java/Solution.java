
import java.util.*;

class Solution {

    List<List<Integer>> solutionFromRight(int[] nums, List<List<Integer>> lists){

        int len = nums.length;

        for(int v = len-1; v > 0; v--){
            if(nums[v] < 0){
                return lists;
            }
            if(v < len-1 && nums[v] == nums[v+1]){
                continue;
            }
            int pivot = nums[v];
            int R = v - 1;
            int L = 0;
            while(L < R){
                int tempSum = nums[L] + nums[R] + pivot;
                if(tempSum == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(pivot);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);

                    while(L < R && nums[L+1] == nums[L]){
                        L++;
                    }
                    while (L < R && nums[R-1] == nums[R]){
                        R--;
                    }
                    L++;
                    R--;
                }else if(tempSum > 0) {
                    R--;
                } else {
                    L++;
                }

            }
        }

        return lists;
    }

    List<List<Integer>> solutionFromLeft(int[] nums, List<List<Integer>> lists){

        //双指针
        /**
         * 条件为 a+b+c=0
         * 排序之后，支点v从最小值开始，再设两个指针，左（v+1）右（LEN-1）相向靠近
         * 当三点之和大于零，右指针向左移动，小于零，则左指针向右移动，符合条件则纪录，直至两个指针相遇
         * 因此有另一个镜像解法，支点可以从最大值开始
         */
        int len = nums.length;
        for(int i = 0;i < len;++i) {
            if(nums[i] > 0) return lists;

            if(i > 0 && nums[i] == nums[i-1]) continue;

            int curr = nums[i];
            int L = i+1, R = len-1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if(tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L < R && nums[L+1] == nums[L]) ++L;
                    while (L < R && nums[R-1] == nums[R]) --R;
                    ++L;
                    --R;
                } else if(tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);

        lists = solutionFromLeft(nums,lists);
        lists = solutionFromRight(nums,lists);
        return  lists;
    }
}