import java.util.*;

class Solution {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = queries.length;
        int[] ans = new int[m];

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store query time and original index
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));

        // Server ID -> Request count
        HashMap<Integer, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int i = 0; i < m; i++) {

            int start = q[i][0] - x;
            int end = q[i][0];

            // Add requests inside the window
            while (right < logs.length && logs[right][1] <= end) {

                int server = logs[right][0];

                map.put(server, map.getOrDefault(server, 0) + 1);

                if (map.get(server) == 1) {
                    activeServers++;
                }

                right++;
            }

            // Remove requests outside the window
            while (left < right && logs[left][1] < start) {

                int server = logs[left][0];

                map.put(server, map.get(server) - 1);

                if (map.get(server) == 0) {
                    activeServers--;
                }

                left++;
            }

            // Store answer at original query index
            ans[q[i][1]] = n - activeServers;
        }

        return ans;
    }
}