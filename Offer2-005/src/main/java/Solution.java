class Solution {
    public int maxProduct1(String[] words) {
        int answer = 0;
        for (int i = 0; i < words.length; i++) {
            String word1 = words[i];
            for (int j = i + 1; j < words.length; j++) {
                String word2 = words[j];
                // 每个单词的 bitMask 会重复计算很多次
                if (!hasSameChar(word1, word2)) {
                    answer = Math.max(answer, word1.length() * word2.length());
                }
            }
        }
        return answer;
    }

    //完全不能有字符相同
    private boolean hasSameChar(String word1, String word2) {
        for (char c : word1.toCharArray()) {
            if (word2.indexOf(c) != -1) return true;
        }
        return false;
    }

}