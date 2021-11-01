import java.util.PriorityQueue;
import java.util.Queue;

class Solution {

    final int MAX = 1000000;

    public int furthestBuilding(int[] heights, int bricks, int ladders) {

        int ans = heights.length-1;//默认跑到最后
        int[] diff = new int[MAX];
        Queue<Integer> queue = new PriorityQueue<>();

        /**
         * 先建立差分数组
         * diff[i] <= 0,不处理
         * diff[i] > 0,则处理
         * 梯子用在最大正数
         * 砖头用在不太大的正数
         */

        for(int count = 1, bricksWanted = 0; count < heights.length; count++){
            diff[count] = heights[count] - heights[count - 1];
            if(diff[count] > 0) {
                queue.offer(diff[count]);
                if(queue.size() > ladders) {
                    bricksWanted += queue.poll();
                }
                if(bricksWanted > bricks){
                    return count - 1;//砖头用完，结束
                }

            }

        }


        return ans;

    }
}