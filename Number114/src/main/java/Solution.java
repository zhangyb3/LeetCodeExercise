class Solution {
    public void flatten(TreeNode root) {

        convertToOnlyRight(root);
    }

    private void convertToOnlyRight(TreeNode root) {

       if(root == null){
           return;
       }
       TreeNode leftToCovert = root.left;
       TreeNode rightToCovert = root.right;

       convertToOnlyRight(leftToCovert);


       root.right = leftToCovert;
       root.left = null;

       //因为左子树已全部转化，右子树要跟在转化左子树的叶子节点后，所以要进行循环向右查询
        while (root.right != null){
            root = root.right;
        }

        convertToOnlyRight(rightToCovert);
        root.right = rightToCovert;

    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2,node3,node4);
        TreeNode node5 = new TreeNode(5,null,node6);
        TreeNode node1 = new TreeNode(1,node2,node5);
        Solution solution = new Solution();
        solution.convertToOnlyRight(node1);
        System.out.println("finish");
    }
}