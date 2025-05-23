package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CountingBits
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 338. Counting Bits
 */
public class LC338_CountingBits {
    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
     * in their binary representation and return them as an array.
     *
     * Example 1:
     *
     * Input: 2
     * Output: [0,1,1]
     * Example 2:
     *
     * Input: 5
     * Output: [0,1,1,2,1,2]
     * Follow up:
     *
     * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time
     * O(n) /possibly in a single pass?
     * Space complexity should be O(n).
     * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
     * other language.
     * @param num
     * @return
     */
    // S1: DP
    // time = O(n), space = O(n)
    public int[] countBits(int num) {
        // corner case
        if (num == 0) return new int[]{0};

        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp;
    }
}
