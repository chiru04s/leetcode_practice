import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Release completed servers
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                int server = busy.poll()[1];
                available.add(server);
            }

            // All servers are busy
            if (available.isEmpty()) {
                continue;
            }

            // Find preferred server
            int preferred = i % k;

            // Find next available server
            Integer server = available.ceiling(preferred);

            // Wrap around if necessary
            if (server == null) {
                server = available.first();
            }

            // Assign request
            available.remove(server);
            count[server]++;

            // Calculate completion time
            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

        // Find maximum requests
        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        // Collect busiest servers
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                result.add(i);
            }
        }

        return result;
    }
}