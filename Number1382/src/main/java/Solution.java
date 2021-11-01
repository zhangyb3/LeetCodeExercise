import java.util.ArrayList;
import java.util.List;

class Solution {

    //二叉搜索树平衡化
    //暴力方法是先把树写到有序数组，然后重建一棵树
    List<Integer> buffer = new ArrayList<>();

    private void record(TreeNode root) {
        if(root.left != null){
            record(root.left);
        }
        buffer.add(root.val);
        if(root.right != null){
            record(root.right);
        }
    }

    private TreeNode rebuildBalanceBST(int left, int right){
        TreeNode treeNode = null;

        if(left > right){
            return null;
        }
        int middle = left + (right - left) / 2;//计算中点
        //int middle = (left + right) >> 1;//移位操作，又移一位相当于除以2，同理，左移一位相当于乘以2
        treeNode = new TreeNode(buffer.get(middle));
        if(left < middle){
            treeNode.left = rebuildBalanceBST(left,middle-1);
        }
        if(right > middle){
            treeNode.right = rebuildBalanceBST(middle+1,right);
        }


        return treeNode;
    }

    public TreeNode balanceBST(TreeNode root) {


        record(root);

        root = rebuildBalanceBST(0, buffer.size()-1);

        return root;
    }
}