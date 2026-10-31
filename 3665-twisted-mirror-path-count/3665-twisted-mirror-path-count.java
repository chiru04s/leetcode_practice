class Solution {
    public int uniquePaths(int[][] grid) {

        final int MOD = 1_000_000_007;

        int n = grid[0].length;

        int[] down = new int[n];

        down[0] = 1;

        for (int[] row : grid) {

            int right = 0;

            for (int j = 0; j < n; j++) {

                if (row[j] == 1) {

                    // Mirror: swap directions
                    int temp = right;
                    right = down[j];
                    down[j] = temp;

                } else {

                    // Empty cell: both directions are possible
                    right = (right + down[j]) % MOD;
                    down[j] = right;
                }
            }
        }

        return down[n - 1];
    }
}