public class Solution {
    int count = 0;

    TreeNode kTH = null;

    public int kthSmallest(TreeNode root, int k) {
        //二叉搜索树，严格遵循左<根<右，
        //所以一直中序遍历DFS，每访问一个节点就累加
        dfs(root,k);
        return  kTH.val;
    }

    public void dfs(TreeNode root,int k){
        if(root == null){
            return;
        }
        dfs(root.left,k);
        count++;
        if(count == k){
            kTH = root;
        }
        dfs(root.right,k);

    }
}
