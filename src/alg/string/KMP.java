package alg.string;

/**
 * @author QFJiang on 2018/01/03 21:32
 */
public class KMP {

    private static int[] getNext(String pattern) {
        char[] p = pattern.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {
                ++j;
                ++k;

                // 原始next数组
                next[j] = k;

                // 优化next数组
                // 当p[j] != s[i]时，下次匹配必然是p[next[j]]跟s[i]匹配，如果p[j] = p[next[j]]，必然匹配失败，
                // 所以如果出现了p[j] = p[next[j]]，则需要再次递归，即令next[j] = next[next[j]] = next[k]。
                if (p[j] != p[k]) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * KMP搜索算法：检查目标字符串是否包含特定模式
     *
     * @param source  目标字符串
     * @param pattern 搜索模式
     * @return 第一次出现的索引或-1（不存在）
     */
    public static int kmpSearch(String source, String pattern) {
        int i = 0;
        int j = 0;
        int[] next = getNext(pattern);
        while (i < source.length() && j < pattern.length()) {
            if (j == -1 || source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];    // 只回溯pattern，不回溯source
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }
}
