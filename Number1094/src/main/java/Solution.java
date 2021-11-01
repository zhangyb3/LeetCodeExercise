class Solution {

    /**
     * 思路：先确定不超载的情况
     *
     * 1、任何一程只要超过座位数就false
     * 2、任何两程有重叠就相加两程的客人，超过就false
     *
     * 此处建立差分数组diff,遍历到trips[i]时,
     * diff[trips[i][1]]加上该地上车的乘客数目trips[i][0]表示比上一位置增加了这么多乘客,
     * 在diff[trip[i][2]]处减去trips[i][0]表示乘客已下车，
     * 那么数组diff的前缀和prefixSum[i]就表示i处的当前乘客数目，如果超过容量capacity就不能完成任务了
     *
     *
     */
    public boolean carPooling(int[][] trips, int capacity) {
        boolean can = true;

        final int max = 1000;    // 最远距离处,max是常亮

        /**
         * 举例：trips = [[2,1,5],[3,3,7]],
         * 差分数组diff先全部初始化为0
         * 每个最小数组第一个数c为人数
         * 第二个数i是上车站标为 diff[i] = diff[i] + c
         * 第二个数j是上车站标为 diff[j] = diff[j] - c
         *
         * 全部扫描后可以确定所有站的人数变化行为
         * 当差分数组从头向尾不停累加，有任何一次超过人数上限，就可以判断超载
         */
        int[] diff = new int[max + 1];//全部初始化为零
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }

        int prefixSum = 0;
        for (int i = 0; i <= max; i++) {
            prefixSum += diff[i];
            if (prefixSum > capacity) {
                can = false;
                break;
            }
        }


        return can;
    }

    public static void main(String[] args) {
        int[][] trips = new int[][]{};
    }
}