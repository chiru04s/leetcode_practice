class Solution {
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] answer = new int[n];

        int prefix = 1;

        // First pass: calculate product of elements on LEFT
        for (int i = 0; i < n; i++) {

            answer[i] = prefix;

            prefix = prefix * nums[i];
        }

        int suffix = 1;

        // Second pass: calculate product of elements on RIGHT
        for (int i = n - 1; i >= 0; i--) {

            answer[i] = answer[i] * suffix;

            suffix = suffix * nums[i];
        }

        return answer;
    }
}