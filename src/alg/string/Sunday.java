package alg.string;

/**
 * @author QFJiang on 2018/01/04 15:03
 */
public class Sunday {

    /**
     * Sunday的思想跟BM算法很相似，只不过Sunday算法是从前往后匹配，
     * 在匹配失败时关注的是文本串中参加匹配的最末位字符的下一位字符。
     * 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 匹配串长度 + 1；
     * 否则，其移动位数 = 模式串中最右端的该字符到末尾的距离 + 1。
     */
    public static int sundaySearch(String source, String pattern) {
        int i = 0;
        int j = 0;
        while (i < source.length() && j < pattern.length()) {
            if (source.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                int cur = i + pattern.length() - j;
                if (cur < source.length()) {
                    char c = source.charAt(cur);
                    int idx = pattern.indexOf(c);
                    i = i + pattern.length() - idx;
                    j = 0;
                } else {
                    return -1;
                }
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }
}
