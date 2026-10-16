class Solution {
static final int MOD = 1_000_000_007;


long[] sum, sq, lazy;

public int sumCounts(int[] nums) {
    int n = nums.length;

    sum = new long[4 * n];
    sq = new long[4 * n];
    lazy = new long[4 * n];

    java.util.Map<Integer, Integer> last = new java.util.HashMap<>();

    long ans = 0;

    for (int i = 0; i < n; i++) {
        int prev = last.getOrDefault(nums[i], -1);

        update(1, 0, n - 1, prev + 1, i);

        ans = (ans + sq[1]) % MOD;

        last.put(nums[i], i);
    }

    return (int) ans;
}

private void apply(int node, int l, int r, long x) {
    long len = r - l + 1;

    sq[node] = (sq[node] + 2 * x % MOD * sum[node]
                + len * x % MOD * x) % MOD;

    sum[node] = (sum[node] + len * x) % MOD;

    lazy[node] = (lazy[node] + x) % MOD;
}

private void push(int node, int l, int r) {
    if (lazy[node] == 0 || l == r) {
        return;
    }

    int mid = (l + r) / 2;
    long x = lazy[node];

    apply(node * 2, l, mid, x);
    apply(node * 2 + 1, mid + 1, r, x);

    lazy[node] = 0;
}

private void update(int node, int l, int r, int ql, int qr) {
    if (qr < l || r < ql) {
        return;
    }

    if (ql <= l && r <= qr) {
        apply(node, l, r, 1);
        return;
    }

    push(node, l, r);

    int mid = (l + r) / 2;

    update(node * 2, l, mid, ql, qr);
    update(node * 2 + 1, mid + 1, r, ql, qr);

    sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

    sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
}


}
