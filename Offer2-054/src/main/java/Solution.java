import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    int rootAndRight = 0;

    public TreeNode convertBST(TreeNode root) {


        //DFS深度优先，计算右子数和根节点的总和
       if(root !=  null) {
           convertBST(root.right);
           root.val = root.val + rootAndRight;
           rootAndRight = root.val;
           convertBST(root.left);
       }



        return root;
    }


    Stack<TreeNode> stack = new Stack<>();

    public TreeNode stackConvertBstToGst(TreeNode root){

        TreeNode current = root;
        while (!stack.isEmpty() || current != null){

            while (current != null){
                stack.push(current);
                current = current.right;
            }


            current = stack.peek();
            current.val = current.val + rootAndRight;
            rootAndRight = current.val;
            stack.pop();
            current = current.left;
        }



        return root;
    }
}
