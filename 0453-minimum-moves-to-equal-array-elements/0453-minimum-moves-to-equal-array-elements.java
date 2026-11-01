class Solution {
    public int minMoves(int[] nums) {
        int mn = Integer.MAX_VALUE;

        for (int num : nums) {
            mn = Math.min(mn, num);
        }

        int sum = 0;

        for (int num : nums) {
            sum += num - mn;
        }

        return sum;
    }
}