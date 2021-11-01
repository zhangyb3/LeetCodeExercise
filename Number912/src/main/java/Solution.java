
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int[] sortArray(int[] nums) {

        //quickSort(nums,0,nums.length-1);

        //HEAPSORT(nums);

        bubbleSort(nums);

        return nums;
    }

    private static void quickSort(int[] nums, int start, int end) {

        int pivot = nums[start];
        int i = start;
        int j = end;
        while (i != j){
            while(nums[j] >= pivot && i < j){
                j--;
            }
            nums[i] = nums[j];
            while(nums[i] <= pivot && i < j){
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;

        if(start < i-1) quickSort(nums,start,i-1);
        if(i+1 < end) quickSort(nums,i+1,end);

    }

    private static void heapSort(int[] nums) {
        //创建堆
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            sift(nums, i, nums.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = nums.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;

            //重新对堆进行调整
            sift(nums, 0, i);
        }
    }

    private static void sift(int[] nums, int parent, int length) {

        //将temp作为父节点
        int temp = nums[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = lChild + 1;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && nums[lChild] < nums[rChild]) {
                lChild++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= nums[lChild]) {
                break;
            }

            // 把孩子结点的值赋给父结点
            nums[parent] = nums[lChild];

            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * lChild + 1;
        }
        nums[parent] = temp;
    }

    private static void outputNums(int[] nums){
        System.out.println("-----------");
        for (int num:
                nums) {
            System.out.println(num);
        }
    }

    private static void SIFT(int[] nums, int parent, int length){


        for(int left = parent * 2 + 1; left < length;){

            int right = left + 1;

            //有右子
            if(right < length){
                //比较子树
                int max = Math.max(nums[parent],Math.max(nums[left],nums[right]));
                if(max == nums[parent]) {
                    //outputNums(nums);
                    break;
                }else if(max == nums[left]){
                    nums[left] = nums[parent];
                    nums[parent] = max;
                    parent = left;
                    left = parent * 2 + 1;
                    outputNums(nums);
                }else {
                    nums[right] = nums[parent];
                    nums[parent] = max;
                    parent = right;
                    left = parent * 2 + 1;
                    outputNums(nums);
                }

            }else {//没有右子
                //比较子树
                int max = Math.max(nums[left],nums[parent]);
                if(max == nums[parent]) {
                    //outputNums(nums);
                    break;
                }else if(max == nums[left]){
                    nums[left] = nums[parent];//交换父子
                    nums[parent] = max;
                    parent = left;
                    left = parent * 2 + 1;
                    outputNums(nums);
                }
            }

        }


    }

    private static void HEAPSORT(int[] nums){
        for(int count = (nums.length -1) / 2; count >= 0; count--){
            SIFT(nums,count,nums.length);
        }

        System.out.println("FIRST");
        outputNums(nums);
        System.out.println("SECOND");

        for (int count = nums.length - 1; count >= 0; count--) {
            //将堆顶元素与末尾元素进行交换
            int temp = nums[count];
            nums[count] = nums[0];
            nums[0] = temp;

            //重新对堆进行调整
            SIFT(nums, 0, count);
        }
    }

    private static void bubbleSort(int[] nums){
        int length = nums.length;
        for(int current = 0; current < length - 1; current++){
            for(int count = length - 1; count > current; count--){
                if(nums[count] < nums[count-1]){
                    int temp = nums[count];
                    nums[count] = nums[count-1];
                    nums[count-1] = temp;
                }
            }
        }
    }

    List<List<Integer>> results = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {

        if(root == null){
            return results;
        }

        Queue<TreeNode> queueToVisited = new LinkedList<>();
        queueToVisited.offer(root);
        while (!queueToVisited.isEmpty()){

            List<Integer> currentLayer = new ArrayList<>();
            int layerSize = queueToVisited.size();
            for(int count = 0; count < layerSize; count++){
                TreeNode temp = queueToVisited.poll();
                currentLayer.add(temp.val);
                if(temp.left != null){
                    queueToVisited.offer(temp.left);
                }
                if(temp.right != null){
                    queueToVisited.offer(temp.right);
                }
            }
            results.add(currentLayer);
        }

        return results;
    }

    private void BFS(TreeNode node, List<Integer> temp){
        if(node == null){
            return;
        }else {
            temp.add(node.val);

            List<Integer> nextLayerResult = new ArrayList<Integer>();
            BFS(node.left,nextLayerResult);
            BFS(node.right,nextLayerResult);
        }
        results.add(temp);
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{5,1,1,2,0,0};
//        new Solution().sortArray(nums);
//        outputNums(nums);

        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        TreeNode node20 = new TreeNode(20,node15,node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3,node9,node20);
        Solution solution = new Solution();
        solution.levelOrder(node3);
        for (List<Integer> result:
             solution.results) {
            System.out.println("--------");
            for (Integer integer:
                 result) {
                System.out.println(integer);
            }
        }

    }
}