import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> answers = new ArrayList<String>();

        StringBuilder sb = new StringBuilder("");
        /**
         * 左右括号的配对形式上很像二叉树
         * 中序遍历DFS
         * 设置终止条件
         *
         */

        dfsGenerate(sb, n, n - 1, n, answers);

        return answers;
    }

    public static void dfsGenerate(StringBuilder sb, int n, int leftRemain, int rightRemain, List<String> answers) {
        if (sb.length() == n * 2) {
            answers.add(sb.toString());
            return ;
        }
        // leftRemain, rightRemain 分别表示可放置左右括号的剩余数量
        if (leftRemain > 0) { // 我还能不能放一个左括号？
            sb.append("(");
            dfsGenerate(sb, n, leftRemain - 1, rightRemain, answers);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rightRemain > 0 && leftRemain < rightRemain) {
            // 我还能不能放一个右括号？注意：放右括号时，在它前面左括号数量一定大于右括号
            // 反过来说，此时左括号剩余数量一定要小于右括号剩余数量
            sb.append(")");
            dfsGenerate(sb, n, leftRemain, rightRemain - 1, answers);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


}