import java.util.*;

class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {

        /**
         * 数据结构是关键，哈希表应该可行
         *
         * 先统计
         */
        Set<String> foodNameSet = new HashSet<String>();
        Map<Integer,Map<String,Integer>> foodsCount = new HashMap<>();
        for (List<String> order:
             orders) {
            String foodName = order.get(2);
            foodNameSet.add(foodName);
            Integer tableId = Integer.parseInt(order.get(1));
            Map<String, Integer> map = foodsCount.getOrDefault(tableId, new HashMap<String, Integer>());
            map.put(order.get(2), map.getOrDefault(foodName, 0) + 1);
            foodsCount.put(tableId,map);
        }

        //排序
        List<String> foodNames = new ArrayList<String>();
        for (String name : foodNameSet) {
            foodNames.add(name);
        }
        Collections.sort(foodNames);

        List<Integer> tableIds = new ArrayList<Integer>();
        for (int id : foodsCount.keySet()) {
            tableIds.add(id);
        }
        Collections.sort(tableIds);

        //展示
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : foodNames) {
            header.add(name);
        }//此为第一行
        result.add(header);
        int tableQuantity = foodsCount.size();
        int foodTypeQuantity = foodNames.size();
        for (int i = 0; i < tableQuantity; ++i) {
            int id = tableIds.get(i);
            Map<String, Integer> cnt = foodsCount.get(id);
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(id));
            for (int j = 0; j < foodTypeQuantity; ++j) {
                row.add(Integer.toString(cnt.getOrDefault(foodNames.get(j), 0)));
            }
            result.add(row);
        }
        return result;


    }
}