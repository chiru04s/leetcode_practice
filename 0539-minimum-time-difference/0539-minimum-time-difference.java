class Solution {
    public int findMinDifference(List<String> timePoints) {

        List<Integer> list = new ArrayList<>();

        // Time ko minutes me convert
        for (String time : timePoints) {

            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));

            list.add(hour * 60 + minute);
        }

        // Sort
        Collections.sort(list);

        int ans = Integer.MAX_VALUE;

        // Normal differences
        for (int i = 1; i < list.size(); i++) {
            ans = Math.min(ans, list.get(i) - list.get(i - 1));
        }

        // Last aur first ka difference
        int last = list.get(list.size() - 1);
        int first = list.get(0);

        ans = Math.min(ans, 1440 - last + first);

        return ans;
    }
}