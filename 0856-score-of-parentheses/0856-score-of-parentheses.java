
class Solution {

    public int scoreOfParentheses(String s) {

        Stack<Integer> st = new Stack<>();

        st.push(0);

        for (char c : s.toCharArray()) {

            if (c == '(') {
                st.push(0);
            } 
            else {

                int x = st.pop();

                int score;

                if (x == 0)
                    score = 1;       // ()

                else
                    score = 2 * x;   // (A)

                st.push(st.pop() + score);  // AB
            }
        }

        return st.peek();
    }
}