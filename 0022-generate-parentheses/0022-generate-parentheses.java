class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve("", 0, 0, n, ans);
        return ans;
    }

    void solve(String s, int open, int close, int n, List<String> ans) {

        // String is complete
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        // Add '(' if we still have opening brackets
        if (open < n) {
            solve(s + "(", open + 1, close, n, ans);
        }

        // Add ')' only if it won't become invalid
        if (close < open) {
            solve(s + ")", open, close + 1, n, ans);
        }
    }
}