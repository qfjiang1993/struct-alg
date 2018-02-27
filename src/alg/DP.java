package alg;

/**
 * 动态规划算法：递归 + 备忘录
 * 递归用于求解子问题，备忘录用于记录以求解的子问题
 *
 * @author QFJiang on 2018/01/08 10:30
 */
public class DP {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("ABCBDAB", "BDCABA"));
        System.out.println(maxContinuousSubSum(new int[]{20, -11, -4, -13, -5, -2}));
        System.out.println(maxContinuousSubSum(new int[]{1, -3, 4, -2, -1, 6}));
        System.out.println(longestIncreasingSubsequence(new int[]{5, 6, 2, 3, 4, 1, 9, 9, 8, 9, 5}));

        int[] w = new int[]{3, 4, 7, 8, 9};
        int[] v = new int[]{4, 5, 10, 11, 13};
        System.out.println(zeroOneKnapsack(17, w, v));
        System.out.println(zeroOneKnapsackImprovement(17, w, v));
    }

    /**
     * 最长公共子序列长度（不要求连续） Longest Common Subsequence
     * <p>
     * 字符串X、Y长度分别为m、n，lcs为(m+1)*(n+1)的二维数组，
     * lcs(i, j)定义为子串X[i..m-1]与Y[j..n-1]的公共子串长度，有：
     * <pre>
     *     if i==m or j==n  then lcs(i, j) = 0;
     *     if X[i] != Y[j]  then lcs(i, j) = max(lcs(i, j+1), lcs(i+1, j))
     *     if X[i] == Y[j]  then lcs(i, j) = 1 + lcs(i+1, j+1)
     * </pre>
     */
    public static int longestCommonSubsequence(String X, String Y) {
        // 初始化数组及边界
        int[][] lcs = new int[X.length() + 1][Y.length() + 1];
        for (int i = 0; i <= X.length(); i++) {
            lcs[i][Y.length()] = 0;
        }
        for (int j = 0; j <= Y.length(); j++) {
            lcs[X.length()][j] = 0;
        }
        // 动态规划求解：递归+备忘录（存储表）
        for (int i = X.length() - 1; i >= 0; i--) {
            for (int j = Y.length() - 1; j >= 0; j--) {
                if (X.charAt(i) == Y.charAt(j)) {
                    lcs[i][j] = lcs[i + 1][j + 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i][j + 1], lcs[i + 1][j]);
                }
                lcs[i][j] = Math.max(lcs[i][j + 1], lcs[i][j]);
                lcs[i][j] = Math.max(lcs[i + 1][j], lcs[i][j]);
            }
        }

//        // 打印公共子序列，从lcs右上角遍历到左下角，
//        // 比较lcs(i, j)与lcs(i, j+1)、lcs(i+1, j)、lcs(i+1, j+1)
//        int i = 0;
//        int j = 0;
//        // 防止在X中出现ABA子序列，而在Y中只有A的情况下打印两次A
//        int last = -1;
//        StringBuilder sb = new StringBuilder();
//        while (i < X.length() && j < Y.length()) {
//            if (X.charAt(i) == Y.charAt(j) && last != lcs[i][j]) {
//                sb.append(X.charAt(i));
//                last = lcs[i][j];
//            }
//            // 从lcs(i, j+1)、lcs(i+1, j)、lcs(i+1, j+1)中选择最大值
//            if (lcs[i + 1][j + 1] >= lcs[i + 1][j]
//                    && lcs[i + 1][j + 1] >= lcs[i][j + 1]) {
//                i++;
//                j++;
//            } else if (lcs[i][j + 1] >= lcs[i + 1][j]) {
//                j++;
//            } else {
//                i++;
//            }
//        }
//        System.out.println(sb.toString());

        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < X.length() && j < Y.length()) {
            if (X.charAt(i) == Y.charAt(j)) {
                sb.append(X.charAt(i));
                i++;
                j++;
            } else if (lcs[i][j + 1] > lcs[i + 1][j]) {
                j++;
            } else if (lcs[i][j + 1] < lcs[i + 1][j]) {
                i++;
            } else {
                j++;//i++;
            }
        }
        System.out.println(sb.toString());

        return lcs[0][0];
    }

    /**
     * 最大连续子序列和 Maximum Continuous Subsequence Sum
     * <p>
     * 定义M[i]为i为末端的所有窗口的最大和，有：
     * <pre>
     *     M[i] = max(M[i - 1] + A[i], 0)
     * 其中：M[i - 1] + A[i]表示通过增加A[i]扩充原来的和，0表示始于A[i]的新窗口
     * </pre>
     */
    public static int maxContinuousSubSum(int[] A) {
        int[] M = new int[A.length];
        int maxSum = 0;
        if (A[0] > 0) {
            M[0] = A[0];
            maxSum = M[0];
        } else {
            M[0] = 0;
        }
        for (int i = 1; i < A.length; i++) {
            if (M[i - 1] + A[i] > 0) {
                M[i] = M[i - 1] + A[i];
            } else {
                M[i] = 0;
            }
            if (M[i] > maxSum) {
                maxSum = M[i];
            }
        }
        return maxSum;
    }

    /**
     * 卡塔兰数 Catalan Number n个节点的二叉搜索树总共有多少颗
     * 递归：Cn = C1*Cn-1 + ... + Cn-1*C1，数学求解：(2n)!/(n!(n+1)!)
     */
    public static int catalanNumber(int n) {

//        // Recursive Method
//        if (n == 0) {
//            return 1;
//        }
//        int count = 0;
//        for (int i = 1; i <= n; i++) {
//            count += catalanNumber(i - 1) * catalanNumber(n - i);
//        }
//        return count;

        int[] catalanTable = new int[1024];
        catalanTable[0] = 1;
        catalanTable[1] = 1;

        if (catalanTable[n] != 0) {
            return catalanTable[n];
        }
        for (int i = 1; i <= n; i++) {
            catalanTable[n] += catalanNumber(i - 1) * catalanNumber(n - i);
        }
        return catalanTable[n];
    }

    /**
     * 最长严格递增子序列 Longest Increasing Subsequence （不要求连续）
     * <p>
     * 定义：L[i]表示从A[0]开始且以A[i]结束的最优子序列
     * 递归：L[i] = max { L[j] + 1 }, j<i && A[j]<A[i]
     * <p>
     * 另解：将A[]递增排序存为B[]，求A与B的LCS
     * 要求严格递增，则须将B[]中重复元素删除之后再求LCS
     */
    public static int longestIncreasingSubsequence(int[] A) {
        int[] lisTable = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            lisTable[i] = 1;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && lisTable[i] < lisTable[j] + 1) {
                    lisTable[i] = lisTable[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = max > lisTable[i] ? max : lisTable[i];
        }
        return max;
    }

//    /**
//     * 若在0~i之间存在元素和为j的子集则M[i][j]=1否则M[i][j]=0
//     * 递归：M[i][j] = max(M[i-1][j], M[i-1][j-A[i]])
//     */
//    public static int subsetSum(int[] A, int sum) {
//        int[][] M = new int[A.length + 1][sum + 1];
//        for (int i = 0; i < sum + 1; i++) {
//            M[0][i] = 0;
//        }
//
//    }

    /**
     * 0-1背包问题：每种物品仅有一件，可以选择放或不放
     * <p>
     * 定义：f[i][j]表示将第1~i件物品装入一个容量为j的背包可以获得的最大价值
     * 递归：f[i][j] = max{ f[i-1][j], f[i-1][j-w[i]] + v[i] }
     * 初始：i=0时没有商品，最大价值为零；j=0时没有容量，最大价值也为零
     *
     * @param V       背包的容量
     * @param weights 物品的重量数组
     * @param values  物品的价值数组
     * @return 背包能装的最大价值
     */
    public static int zeroOneKnapsack(int V, int[] weights, int[] values) {
        int N = weights.length;
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                // 剩余容量可以放下第i件物品
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
//        int[][] dp = new int[N][V + 1];
//        for (int i = 1; i < N; i++) {
//            for (int j = 1; j <= V; j++) {
//                if (j >= weights[i]) {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }
//        }

//      // Print trace
//        for (int i = 0; i <= V; i++) {
//            System.out.format("%5d", i);
//        }
//        System.out.println();
//        for (int[] row : dp) {
//            for (int i : row) {
//                System.out.format("%5d", i);
//            }
//            System.out.println();
//        }
        return dp[N][V];
    }

    /**
     * 0-1背包问题改进版，优化空间复杂度
     *
     * @param V       背包的容量
     * @param weights 物品的重量数组
     * @param values  物品的价值数组
     * @return 背包能装的最大价值
     */
    public static int zeroOneKnapsackImprovement(int V, int[] weights, int[] values) {
        int N = weights.length;
        int[] dp = new int[V + 1];
        for (int i = 0; i < N; i++) {
            for (int j = V; j >= 0; j--) {
                // 剩余容量可以放下第i件物品
                if (j >= weights[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }
        return dp[V];
    }

}
