import java.util.Arrays;

class Solution {

    /**
     * 开始以为复杂，其实不是，
     * 因为每次都是取走第二多的硬币，所以确保每次取出时，都把原来的第二多那堆拿到即可
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int length = piles.length;
        int rounds = length / 3;
        int coins = 0;
        int index = length - 2;
        for (int i = 0; i < rounds; i++) {
            coins += piles[index];
            index -= 2;
        }
        return coins;
    }


}