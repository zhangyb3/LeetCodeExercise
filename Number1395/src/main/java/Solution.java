import java.util.*;

class Solution {


    List<List<Integer>> answers = new ArrayList<>();
    public int numTeams(int[] rating) {
        int sum = 0;
        sum = loopTeams(rating);
        sum = dpCount(rating);
        return sum;
    }

    private int loopTeams(int[] rating) {
        int sum = 0;
        int len = rating.length;
        for (int i = 0; i < len-2; i++ ){
            for(int j = i+1; j < len-1; j++){
                for(int k = j+1; k < len; k++){
                    if(rating[i] < rating[j] && rating[j] < rating[k]){
                        sum++;
                        List<Integer> answer = new ArrayList<>();
                        answer.add(rating[i]);
                        answer.add(rating[j]);
                        answer.add(rating[k]);
                        answers.add(answer);
                    }
                    if(rating[i] > rating[j] && rating[j] > rating[k]){
                        sum++;
                        List<Integer> answer = new ArrayList<>();
                        answer.add(rating[i]);
                        answer.add(rating[j]);
                        answer.add(rating[k]);
                        answers.add(answer);
                    }
                }
            }

        }
        return sum;
    }

    private int dpCount(int[] rating){
        if(rating.length < 3)
            return 0;
        int ans = 0;
        for(int i = 0; i < rating.length; i++){
            int leftLess = 0, leftMore = 0, rightLess = 0, rightMore = 0;
            for(int j = i - 1; j >= 0; j--){
                if(rating[j] < rating[i])
                    leftLess++;
                else
                    leftMore++;
            }//找出左小或左大的个数
            for(int j = i + 1;j < rating.length; j++){
                if(rating[j] > rating[i])
                    rightMore++;
                else
                    rightLess++;
            }//找出右小或左大的个数
            //以rating[i]为中间数的升序个数
            ans += leftLess * rightMore;
            //以rating[i]为中间数的降序个数
            ans += leftMore * rightLess;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] rating = new int[]{2,5,3,4,1};
//        int[] diff = new int[rating.length];
//        for (int count = 1; count < rating.length; count++){
//            diff[count] = rating[count] - rating[count - 1];
//        }
        Solution solution = new Solution();
        int sum = solution.numTeams(rating);
        System.out.println(sum);

    }
}