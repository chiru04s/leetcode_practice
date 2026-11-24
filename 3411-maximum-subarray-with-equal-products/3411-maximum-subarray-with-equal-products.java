class Solution {
    public int maxLength(int[] nums) {
       int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {

            long product = 1;
            long gcd = 0;
            long lcm = 1;

            for (int j = i; j < n; j++) {

                int num = nums[j];

                product *= num;

                gcd = findGCD(gcd, num);

                lcm = findLCM(lcm, num);

                if (product == gcd * lcm) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

    // Find GCD using Euclidean Algorithm
    private long findGCD(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Find LCM
    private long findLCM(long a, long b) {
        return (a / findGCD(a, b)) * b; 
    }
}