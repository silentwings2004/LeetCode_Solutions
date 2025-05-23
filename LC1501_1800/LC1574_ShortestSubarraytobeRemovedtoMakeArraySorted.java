package LC1501_1800;

public class LC1574_ShortestSubarraytobeRemovedtoMakeArraySorted {
    /**
     * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr
     * are non-decreasing.
     *
     * A subarray is a contiguous subsequence of the array.
     *
     * Return the length of the shortest subarray to remove.
     *
     * Input: arr = [1,2,3,10,4,2,3,5]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^5
     * 0 <= arr[i] <= 10^9
     * @param arr
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int findLengthOfShortestSubarray(int[] arr) {
        // corner case
        if (arr == null || arr.length == 0) return 0;

        int n = arr.length, res = n - 1; // 可以最多删掉n-1个数，最后只保留一个数，肯定是一个解。
        // 从后往前找一个最长的递减区间
        int j = n - 1;
        while (j - 1 >= 0 && arr[j] >= arr[j - 1]) j--;
        res = Math.min(res, j); // 可以删掉[0,j)，只保留[j,n-1]是一个解
        if (res == 0) return 0; // [1,2,3] 直接返回0！

        // 下面一定是三段论的模型，i != j，否则 j - i - 1 = -1 < 0，前提是i与j必须错开，不能相等
        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0 && arr[i] < arr[i - 1]) break; // i 不存在
            while (j < n && arr[j] < arr[i]) j++;
            res = Math.min(res, j - i - 1);
        }
        return res;
    }

    // S1.2
    // time = O(n), space = O(1)
    public int findLengthOfShortestSubarray2(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        int l = 1, r = n - 2;
        while (l < n && arr[l] >= arr[l - 1]) l++;
        while (r >= 0 && arr[r] <= arr[r + 1]) r--;
        l--;
        r++;
        if (l > r) return 0;
        int res = Math.max(l + 1, n - r);
        for (int i = 0, j = r; i <= l; i++) {
            while (j < n && arr[j] < arr[i]) j++;
            res = Math.max(res, i + 1 + n - j);
        }
        return n - res;
    }
}
/**
 * 找最长没有意义 -> 砍到只剩1个
 * 更喜欢做subarray -> 只需要确定左右边界
 * 可大可小，可左可右
 * 整个数组分成三段
 * [x x x] [x x x {x] [x x x]}
 * 0    i            j   n-1
 * 如何高效的确定i和j
 * 1. [0:i] increase
 * 2. [j:n-1] increase -> 从后往前走，找一个最长的递减序列
 * 3. arr[i] <= arr[j]
 * 如果i向后移动一格，首先check是否递增；如果是递增，意味着j只能继续往后走
 * 快慢双指针，time = O(n)
 */
