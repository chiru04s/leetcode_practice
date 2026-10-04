class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                low++;
                high++;
            } 
            else if (ch == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;   // treat * as ')'
                high++;  // treat * as '('
            }

            // Even the maximum possible opens is negative
            if (high < 0) {
                return false;
            }

            // Minimum cannot be negative
            if (low < 0) {
                low = 0;
            }
        }

        return low == 0;
    }
}