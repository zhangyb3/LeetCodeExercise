class Solution {
    public int mincostTickets(int[] days, int[] costs) {

        /**
         * 类似走方格到某个点的模式
         *
         * 起点前进版本
         */
        int len = days.length;
        int lastDay = days[len-1];//最后一天
        int[] dp = new int[lastDay+1];//dp[i]表示第i天最便宜的花费
        int pos = 0;
        for (int count = 1; count <= lastDay; count ++){
            if (count == days[pos]){
                int day1 = count - 1;
                int day2 = count - 7 > 0? count-7:0;//当天数超过7天，看看买7天的票会否便宜
                int day3 = count - 30 > 0? count-30:0;//当天数超过30天，看看买30天的票会否便宜
                dp[count] = Math.min(dp[day1]+costs[0],Math.min(dp[day2]+costs[1],dp[day3]+costs[2]));
                pos++;
            }else{
                dp[count ] = dp[count -1];
            }
        }
        return dp[lastDay];


    }
}