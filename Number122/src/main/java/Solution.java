class Solution {
    public int maxProfit(int[] prices) {

        int maxProfit = 0;


        int min = prices[0];

        int max = prices[0];

        /**
         * 遍历时遇到比目前低的价格就买入，然后比较差价，如果比纪录差价大就纪录
         */
        for (int count = 0; count < prices.length; count++){
            if(prices[count] < min){
                min = prices[count];
            }
            if(prices[count] - min > 0){
                maxProfit = maxProfit + prices[count] - min;
                min = prices[count];
            }
        }

        return maxProfit;

    }

    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(new Solution().maxProfit(prices));
    }
}