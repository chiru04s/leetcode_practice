class Solution {
    public String removeOuterParentheses(String s) {

        StringBuilder res = new StringBuilder();
        int open = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                if (open > 0) {
                    res.append(c);
                }
                open++;
            }

            else {
                open--;

                if (open > 0) {
                    res.append(c);
                }
            }
        }

        return res.toString();
    }
}