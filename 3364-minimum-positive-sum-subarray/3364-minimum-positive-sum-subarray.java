class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();
        int ans = Integer.MAX_VALUE;

        // Calculate prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        // Check all possible subarrays
        for (int i = 0; i < n; i++) {

            for (int len = l; len <= r; len++) {

                if (i + len <= n) {

                    int sum = prefix[i + len] - prefix[i];

                    // Find minimum positive sum
                    if (sum > 0) {
                        ans = Math.min(ans, sum);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}