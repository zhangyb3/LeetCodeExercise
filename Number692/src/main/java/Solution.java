import java.util.*;

class Solution {

    List<String> results = new ArrayList<>();

    public List<String> topKFrequent(String[] words, int k) {

        //经典“前K问题”
        //直观解法是哈希表记录，然后排序
        //也可以用堆（优先队列），因为建堆过程就完成排序

        //results = hashMapSolution(words,k);
        results = priorityQueueSolution(words,k);

        return  results;
    }

    private List<String> priorityQueueSolution(String[] words, int k) {

        // 1.先用哈希表统计单词出现的频率
        HashMap<String,Integer> records = new HashMap<>();
        for (String word: words) {
            //当Map集合中有这个key时，就使用这个key值，如果没有就使用默认值defaultValue
            records.put(word, records.getOrDefault(word, 0) + 1);
        }//统计完成

        // 2.构建小根堆 这里需要自己构建比较规则
        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (records.get(s1).equals(records.get(s2))) {
                    return s2.compareTo(s1);
                } else {
                    return records.get(s1) - records.get(s2);
                }
            }
        });

        // 3.依次向堆加入元素。
        for (String s : records.keySet()) {
            minHeap.offer(s);
            // 当堆中元素个数大于 k 个的时候，需要弹出堆顶最小的元素。
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        // 4.依次弹出堆中的 K 个元素，放入结果集合中。
        List<String> res = new ArrayList<String>(k);
        while (minHeap.size() > 0) {
            res.add(minHeap.poll());
        }
        // 5.注意最后需要反转元素的顺序,因为是从小到大。
        Collections.reverse(res);
        return res;

    }

    private List<String> hashMapSolution(String[] words, int k) {

        HashMap<String,Integer> records = new HashMap<>();
        for (String word: words) {
            //当Map集合中有这个key时，就使用这个key值，如果没有就使用默认值defaultValue
            records.put(word, records.getOrDefault(word, 0) + 1);
        }//统计完成

        for (Map.Entry<String, Integer> entry : records.entrySet()) {//entry包含了key和value
            results.add(entry.getKey());
        }

        Collections.sort(results, new Comparator<String>() {
            public int compare(String word1, String word2) {
                if(records.get(word1) > records.get(word2)) {
                    return 1;
                }else if(records.get(word1) == records.get(word2)){
                    return word1.compareTo(word2);
                }else {
                    return -1;
                }
                //return records.get(word1) == records.get(word2) ? word1.compareTo(word2) : records.get(word2) - records.get(word1);
            }//根据key找出数量对字符串数组排序
        });

        return results.subList(0,k);

    }


}