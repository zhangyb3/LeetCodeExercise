public class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        boolean isCoverd = true;

        final int MAX = 50;
        int[] records = new int[MAX+1];

        for (int[] range:ranges) {

            for(int start = range[0]; start <= range[1]; start++){
                records[start] = 1;
            }

        }

        for(int count = left; count <= right; count++){
            if(records[count] < 1){
                isCoverd = false;
                break;
            }
        }


        return isCoverd;

    }
}
