import java.util.Arrays;

class Solution {
    public int singleNumber(int[] nums) {


        int[] differences = new int[nums.length];
        Arrays.fill(differences,0);
        Arrays.sort(nums);
        int answer = nums[nums.length-1];
        differences[0] = nums[0];
        for(int count = 1; count <= nums.length-1; count++){
            differences[count] = nums[count] - nums[count-1];
            if(differences[count] != 0 && differences[count-1] != 0){
                answer = differences[count -1];
            }
        }
        return answer;
    }
}