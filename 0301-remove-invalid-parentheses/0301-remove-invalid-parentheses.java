import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> set = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum removals
        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            }

            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }

                else {
                    rightRemove++;
                }
            }
        }

        dfs(
            s,
            0,
            leftRemove,
            rightRemove,
            0,
            new StringBuilder(),
            set
        );

        return new ArrayList<>(set);
    }


    private void dfs(
        String s,
        int index,
        int leftRemove,
        int rightRemove,
        int balance,
        StringBuilder current,
        Set<String> set
    ) {

        // End of string
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                set.add(current.toString());
            }

            return;
        }

        char c = s.charAt(index);


        // =========================
        // CASE 1: '('
        // =========================

        if (c == '(') {

            // Option 1: Remove '('
            if (leftRemove > 0) {

                dfs(
                    s,
                    index + 1,
                    leftRemove - 1,
                    rightRemove,
                    balance,
                    current,
                    set
                );
            }


            // Option 2: Keep '('
            current.append(c);

            dfs(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                balance + 1,
                current,
                set
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }


        // =========================
        // CASE 2: ')'
        // =========================

        else if (c == ')') {

            // Option 1: Remove ')'
            if (rightRemove > 0) {

                dfs(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove - 1,
                    balance,
                    current,
                    set
                );
            }


            // Option 2: Keep ')'
            if (balance > 0) {

                current.append(c);

                dfs(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    balance - 1,
                    current,
                    set
                );

                // Backtrack
                current.deleteCharAt(current.length() - 1);
            }
        }


        // =========================
        // CASE 3: Letter
        // =========================

        else {

            current.append(c);

            dfs(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                balance,
                current,
                set
            );

            // Backtrack
            current.deleteCharAt(current.length() - 1);
        }
    }
}