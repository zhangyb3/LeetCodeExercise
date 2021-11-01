import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        /**
         * 题目要求返回列表中剩余区间的数目
         *
         */
        int result = 0;

        //先排序，起点升序，终点降序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int len1 = o1[0];
                int len2 = o2[0];
                if (len1 == len2) {
                    return o2[1] - o1[1];
                }else {
                    return o1[0] - o2[0];
                }
            }
        });

        /**
         * 区间覆盖问题，前面步骤已排序
         * 两端节点遍历，如果下一区间两端被包含，则可以判断为覆盖区域
         */
        int leftStart = intervals[0][0];
        int rightEnd = intervals[0][1];
        int size = intervals.length;
        if(size == 0){
            return 0;
        }

        for (int count = 1; count < size; count++){
            if (leftStart <= intervals[count][0] && intervals[count][1] <= rightEnd) { // 覆盖
                result++;
            } else if (rightEnd < intervals[count][0]) { // 不相交
                leftStart = intervals[count][0];
                rightEnd = intervals[count][1];
            } else if (rightEnd >= intervals[count][0] && rightEnd <= intervals[count][1]) {//相交
                rightEnd = intervals[count][1];
            } else {
                continue;
            }
        }

        return size - result;
    }
}