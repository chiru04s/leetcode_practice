class Solution {
    int[] answer;
    int[] newParent;
    List<Integer>[] children;
    int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        answer = new int[n];
        newParent = parent.clone();
        last = new int[26];

        Arrays.fill(last, -1);

        // Build original tree
        children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // Find new parents using DFS
        dfsUpdate(0, s);

        // Build modified tree
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        dfsSize(0, newChildren);

        return answer;
    }

    void dfsUpdate(int node, String s) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        if (old != -1) {
            newParent[node] = old;
        }

        last[ch] = node;

        for (int child : children[node]) {
            dfsUpdate(child, s);
        }

        last[ch] = old;
    }

    int dfsSize(int node, List<Integer>[] tree) {
        int size = 1;

        for (int child : tree[node]) {
            size += dfsSize(child, tree);
        }

        answer[node] = size;

        return size;
    }
}