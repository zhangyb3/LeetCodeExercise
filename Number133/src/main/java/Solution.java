import java.util.ArrayList;
import java.util.HashMap;

class Solution {

    //复制登记纪录
    HashMap <Node, Node> records = new HashMap<>();

    public Node cloneGraph(Node node) {

        /**
         * 普通暴力克隆
         * 此次为DFS遍历
         * 遍历连通图，然后不停复制
         */

        if (node == null) {
            return node;
        }

        if(records.containsKey(node)){
            return records.get(node);
        }

        //复制某节点
        Node cloneNode = new Node(node.val, new ArrayList<>());
        records.put(node,cloneNode);
        //复制该节点的邻接表
        for (Node neighbor:
                node.neighbors) {
            //递归克隆
            cloneGraph(neighbor);
            //深度复制时，克隆对象的邻接表也是克隆的产品
            cloneNode.neighbors.add(records.get(neighbor));

        }


        return cloneNode;
    }
}