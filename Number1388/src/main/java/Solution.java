import java.util.*;

class Solution {

    //暴力解法
    public int minSetSize(int[] arr) {

        final int allQuantity = arr.length;

        int deleteQuantity = 0;

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int a:
             arr) {
            map.put(a,map.getOrDefault(a,0)+1);

        }

        //從HashMap中恢復entry集合，得到全部的鍵值對集合
        Set<Map.Entry<Integer,Integer>> entries = map.entrySet();
        //將Set集合轉為List集合，為了實用工具類的排序方法
        List<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(entries);
        //使用Collections工具類對list進行排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                //从大到小排列
                return o2.getValue() - o1.getValue();
            }
        });

        int count = 0,border;
        if(allQuantity % 2 == 0){
            border = allQuantity / 2;
        }else {
            border = allQuantity / 2 + 1;
        }
        System.out.println(border);

        for (Map.Entry<Integer, Integer> o:
             list) {
            count += o.getValue();
            deleteQuantity++;
            if(count >= border){
                break;
            }
        }

        return deleteQuantity;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,3,3,3,5,5,5,2,2};
        System.out.println(new Solution().minSetSize(arr));
    }
}