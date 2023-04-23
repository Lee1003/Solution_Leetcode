package april2023;

import java.util.Arrays;

public class StoneMove {

    private int[][][] memo;
    private int[] s;
    private int k;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) // 无法合并成一堆
            return -1;

        s = new int[n + 1];
        for (int i = 0; i < n; i++)
            s[i + 1] = s[i] + stones[i]; // 前缀和
        this.k = k;
        memo = new int[n][n][k + 1];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                Arrays.fill(memo[i][j], -1); // -1 表示还没有计算过
        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int p) {
        if (memo[i][j][p] != -1) return memo[i][j][p];
        if (p == 1) // 合并成一堆
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + s[j + 1] - s[i];
        int res = Integer.MAX_VALUE;
        for (int m = i; m < j; m += k - 1) // 枚举哪些石头堆合并成第一堆
            res = Math.min(res, dfs(i, m, 1) + dfs(m + 1, j, p - 1));
        return memo[i][j][p] = res;
    }

}
