import java.util.*;

class Solution {

    int minDeletions(String s) {
        

        HashMap<Character,Integer> map = new HashMap<>();
        int [] count = new int [26];
        for(int i = 0;i < s.length();i++){
            count[s.charAt(i) - 'a']++;
        }
        Arrays.sort(count);
        
        int result = 0;
        for(int i = count.length - 1; i > 0;i--){
            while(count[i] == count[i - 1] && count[i - 1] != 0){
                count[i]--;
                result++;
                Arrays.sort(count);
            }
        }
        return result;

        
    }
};
