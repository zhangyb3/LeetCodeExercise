import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    /**
     * 树就是无环的连通图！怎么检测是否存在环和计算连通分支数？用并查集啦
     * 除了检测环和连通分支数，我们还需要进行一些特殊的判断，例如一个孩子存在多个父亲，一个父亲的存在两个相同的孩子。
     *
     * @param n
     * @param leftChild
     * @param rightChild
     * @return
     */

    // 并查集用的集合列表
    List<Integer> p = new ArrayList<>();
    // 用于统计不相交的连通分支个数
    int cnt;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        boolean isBT = true;

        // 用于标记各个孩子的父节点
        int[] fathers = new int[n];
        // 初始化，并不知道指向
        Arrays.fill(fathers, -1);
        // 初始化并查集集合状态
        for(int i = 0; i < n; i++) p.add(i);

        //连通分量初始化
        cnt = n;

        // 遍历所有节点
        for(int i = 0; i < n; i++) {
            // 如果节点存在两个孩子，而且两个孩子相同，那么显然是错误的二叉树
            if(leftChild[i] == rightChild[i] && leftChild[i] != -1) return false;
            // 合并两个孩子
            if(!merge(fathers, i, leftChild[i]) || !merge(fathers, i, rightChild[i])) return false;
        }

        // 如果最后所有的节点组成一个连通分支，才是一棵树
        if(cnt > 1) isBT = false;
        return isBT;
    }

    // 合并父亲和孩子节点，并判断逻辑
    private boolean merge(int[] fathers, int father, int child) {
        // 孩子是空的，直接返回
        if(child == -1) return true;
        // 孩子之前有爸爸了，就是错的
        if(fathers[child] != -1) return false;
        // 并查集查找两个集合的根
        int a = find(father), b = find(child);
        // 如果孩子和父亲已经存在于一个集合中，那么说明会产生环，返回错误
        if(a == b) return false;
        // 合并两个集合
        p.set(b, a);
        // 标记孩子的父亲是谁
        fathers[child] = father;
        // 连通分支数减一
        cnt --;
        return true;
    }

    // 并查集通用方法，找集合的根元素
    private int find(int x) {
        if(p.get(x) != x) {
            p.set(x, find(p.get(x)));
        }
        return p.get(x);
    }

    public static void main(String[] args) {

    }
}