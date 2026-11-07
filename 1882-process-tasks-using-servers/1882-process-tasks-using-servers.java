import java.util.*;

class Solution {

    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Available servers: [weight, index]
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);

                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [freeTime, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);

                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);

                return Long.compare(a[2], b[2]);
            }
        );

        // Initially all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        // Global time
        long currentTime = 0;

        for (int j = 0; j < m; j++) {

            // Never move time backwards
            currentTime = Math.max(currentTime, j);

            // Release all completed servers
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= currentTime) {

                long[] s = busy.poll();

                available.offer(new int[]{
                    (int) s[1],
                    (int) s[2]
                });
            }

            // If no server is available, wait
            if (available.isEmpty()) {

                currentTime = busy.peek()[0];

                // Release all servers available at this time
                while (!busy.isEmpty() &&
                       busy.peek()[0] <= currentTime) {

                    long[] s = busy.poll();

                    available.offer(new int[]{
                        (int) s[1],
                        (int) s[2]
                    });
                }
            }

            // Select server with minimum weight and index
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Calculate completion time
            long freeTime = currentTime + tasks[j];

            busy.offer(new long[]{
                freeTime,
                weight,
                index
            });
        }

        return ans;
    }
}