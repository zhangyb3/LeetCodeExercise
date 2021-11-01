import java.util.*;

class Solution {
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){

            //层级遍历,注意加入子节点前就要纪录该层的节点数
            int layerSize = queue.size();
            //各层统计前先清零；
            sum = 0;

            for (int count = 0; count < layerSize; count++){

                TreeNode tempNode = queue.poll();
                sum = sum + tempNode.val;

                if(tempNode.left != null){
                    queue.offer(tempNode.left);
                }
                if(tempNode.right != null){
                    queue.offer(tempNode.right);
                }
            }

            System.out.println("sum : " + sum);
        }


        return sum;
    }


    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7,null,null);
        TreeNode node8 = new TreeNode(8,null,null);
        TreeNode node4 = new TreeNode(4,node7,null);
        TreeNode node5 = new TreeNode(5,null,null);
        TreeNode node6 = new TreeNode(6,null,node8);
        TreeNode node2 = new TreeNode(2,node4,node5);
        TreeNode node3 = new TreeNode(3,null,node6);
        TreeNode node1 = new TreeNode(1,node2,node3);

        new Solution().deepestLeavesSum(node1);
    }
}