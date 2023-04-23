package april2023;

import java.util.Arrays;

public class Week1 {

    private static int[] v;
    private static int[][] memo;

    public static int minScoreTriangulation(int[] values) {
        v = values;
        int n = v.length;
        memo = new int[n][n];
        for (int i = 0; i < n; ++i)
            Arrays.fill(memo[i], -1); // -1 表示没有访问过
        return dfs(0, n - 1);
    }

    private static int dfs(int i, int j) {
        if (i + 1 == j) return 0; // 只有两个点，无法组成三角形
        if (memo[i][j] != -1){
            return memo[i][j];
        }
        int res = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; ++k) // 枚举顶点 k
            res = Math.min(res, dfs(i, k) + dfs(k, j) + v[i] * v[j] * v[k]);
        return memo[i][j] = res;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,3,1,4,1,5};
        System.out.println(minScoreTriangulation(arr));
    }
}
