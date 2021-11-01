class Solution {
    public int[] spiralOrder(int[][] matrix) {

        if(matrix.length == 0) return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m*n];
        int count  = 0;

        int left = 0;
        int right = n-1;
        int top = 0;
        int bottom = m-1;
        while(true){
            for(int j = left; j <= right; j++){
                result[count] = matrix[top][j];
                count++;
            }
            //此时top++才避免重复读
            top++;
            if(top > bottom) break;
            for (int i = top; i <= bottom; i++){
                result[count] = matrix[i][right];
                count++;
            }
            //此时right--才避免重复读
            right--;
            if (right < left) break;
            for (int j = right; j >= left; j--){
                result[count] = matrix[bottom][j];
                count++;
            }
            //此时bottom--才避免重复读
            bottom--;
            if(bottom < top) break;
            for (int i = bottom; i >= top; i--){
                result[count] = matrix[i][left];
                count++;
            }
            //此时left++才避免重复读
            left++;
            if(left > right) break;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        Solution solution = new Solution();
        solution.spiralOrder(matrix);
    }
}