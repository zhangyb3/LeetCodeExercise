class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        int length = nums.length;


        TreeNode root = recursiveBuildTree(nums,0,length-1);

        return root;

    }

    private TreeNode recursiveBuildTree(int[] nums, int start, int end) {
        if(start == end){
            TreeNode root = new TreeNode(nums[start]);
            return root;
        }
        int rootIndex = getTheMaxIndex(nums,start,end);
        TreeNode root = new TreeNode(nums[rootIndex]);
        TreeNode left = null;
        TreeNode right = null;
        if(rootIndex+1 <= end){
            right = recursiveBuildTree(nums,rootIndex+1,end);
        }
        if(start <= rootIndex -1){
            left = recursiveBuildTree(nums,start,rootIndex-1);
        }


        root.left = left;
        root.right = right;
        return root;
    }

    private int getTheMaxIndex(int[] nums, int start, int end) {
        int max = nums[start];
        int maxIndex = start;
        for(int count = start; count <= end; count++){
            if(nums[count] > max){
                max = nums[count];
                maxIndex = count;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,6,0,5};
        Solution solution = new Solution();
        TreeNode result = solution.recursiveBuildTree(nums,0,nums.length-1);
        System.out.println("finish");
    }
}