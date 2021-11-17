package Tree;

/**
 * 202111152250
 * 红黑树
 */
public class RedBlackTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RBNode root;

    private static class RBNode<K extends Comparable<K>,V>{
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode(){}

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K key,V value){
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = key;
            this.value = value;
        }

    }

    private RBNode parentOf(RBNode node){
        if(node != null){
            return node.parent;
        }
        return null;
    }
    private boolean isRed(RBNode node){
        if(node != null){
            return node.color == RED;
        }
        return false;
    }
    private boolean isBlack(RBNode node){
        if(node != null){
            return node.color == BLACK;
        }
        return false;
    }
    private void setRed(RBNode node){
        if(node != null){
            node.color = RED;
        }
    }
    private void setBlack(RBNode node){
        if(node != null){
            node.color = BLACK;
        }
    }
    private void inorderPrint(RBNode root){
        if(root != null){
            inorderPrint(root.left);
            System.out.println("key: " + root.key + " , value: " + root.value);
            inorderPrint(root.right);
        }
    }


    /**
     * 左旋方法
     */
    private void leftRotate(RBNode x){
        RBNode y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        x.parent = y.parent;
        //x不是根
        if(x.parent != null){
            //x是左子
            if(x == x.parent.left){
                x.parent.left = y;
            }else {
                //x是右子
                x.parent.right = y;
            }
        }else{
            //x是根
            this.root = y;
            this.root.parent = null;
        }
        x.parent = y;
        y.left = x;
    }
    /**
     * 右旋方法
     */
    private void rightRotate(RBNode x){
        RBNode y = x.left;
        x.left = y.right;
        if(y.right != null){
            y.right.parent = x;
        }
        x.parent = y.parent;
        if(x.parent != null){
            //x是左子
            if(x == x.parent.left){
                x.parent.left = y;
            }else {
                //x是右子
                x.parent.right = y;
            }
        }else{
            //x是根
            this.root = y;
            this.root.parent = null;
        }
        x.parent = y;
        y.right = x;
    }


    /**
     * 新节点插入
     */
    private void insertNode(K key,V value){
        RBNode node = new RBNode();
        node.key = key;
        node.value = value;
        node.color = RED;
        insert(node);

    }
    private void insert(RBNode node) {
        RBNode parent = null;
        RBNode visited = this.root;
        while(visited != null){
            parent = visited;
            int cmp = node.key.compareTo(parent.key);
            if(cmp > 0){
                visited = visited.right;
            }else if(cmp < 0){
                visited = visited.left;
            }else {
                visited.value = node.value;
                return;
            }
        }
        node.parent = parent;
        //再次比较，看放在左还是右
        int cmp = node.key.compareTo(parent.key);
        //node非根
        if(parent != null){
            if(cmp > 0){
                parent.right = node;
            }else if(cmp < 0){
                parent.left = node;
            }
        }else{
            this.root = node;
            node.color = BLACK;
        }
        reBalanceTree(node);
    }
    private void reBalanceTree(RBNode node){
        this.root.color = BLACK;

        RBNode parent = parentOf(node);
        RBNode gParent = parentOf(parent);
        RBNode uncle = null;
        /**
         * 父节点红色才需要处理，黑色直接跳过
         */
        if(parent != null && isRed(parent)){

            if(parent == gParent.left){
                //父节点为爷节点左子
                uncle = gParent.right;
                if(uncle != null && isRed(uncle)){
                    /**
                     * 条件1.1，叔父双红
                     * 叔父变黑，爷变红，再递归处理爷节点
                     */
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    reBalanceTree(gParent);
                    return;
                }else {
                    /**
                     * 条件1.2，无叔或叔黑
                     *
                     */
                    if(node == parent.left){
                        /**
                         * 条件1.2.1，LL情况
                         * 叔父变黑，爷变红右旋
                         */
                        setBlack(parent);
                        setRed(gParent);
                        rightRotate(gParent);
                        return;
                    }else {
                        /**
                         * 条件1.2.2，LR情况
                         * 父左旋，再递归处理
                         */
                        leftRotate(parent);
                        reBalanceTree(parent);
                        return;
                    }
                }

            }else{
                //父节点为爷节点右子
                uncle = gParent.left;
                if(uncle != null && isRed(uncle)){
                    /**
                     * 条件2.1，叔父双红
                     * 叔父变黑，爷变红，再递归处理爷节点
                     */
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gParent);
                    reBalanceTree(gParent);
                    return;
                }else {
                    /**
                     * 条件2.2，无叔或叔黑
                     *
                     */
                    if (node == parent.right) {
                        /**
                         * 条件2.2.1，RR情况
                         * 叔父变黑，爷变红左旋
                         */
                        setBlack(parent);
                        setRed(gParent);
                        leftRotate(gParent);
                        return;
                    } else {
                        /**
                         * 条件2.2.2，RL情况
                         * 父右旋，再递归处理
                         */
                        rightRotate(parent);
                        reBalanceTree(parent);
                        return;
                    }
                }
            }

        }
    }

}
