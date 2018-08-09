package datastructure.string;

/**
 * 给定两个字符串str1 str2，返回两个字符串的最长公共子序列
 * 注：子序列不要求连续
 */
public class LongestCommSequence {
    /**
     * 动态规划法
     * dp[i][j]表示str1[0...i]和str2[0...j]的最长公共子序列
     *
     * dp[i][j]可能来自以下情况：
     *    如果 str1[i] != str2[j]，则 dp[i][j] = dp[i - 1][j] 或 dp[i][j - 1]
     *    如果 str1[i] == str2[j]，则 dp[i][j] 除前两种值外还可以为 dp[i - 1][j - 1] + 1
     * dp[i][j]取上述三种情况中的最大值
     *
     * 计算完动态矩阵后，dp[M-1][N-1]就是最大长度。然后通过整个dp矩阵的状态，计算最长公共子序列
     * 从dp[M-1][N-1]推导回去：
     *    如果 dp[i][j] = dp[i - 1][j - 1] + 1，说明dp[i - 1][j - 1] + 1是三种情况的最大值，且一定str1[i] == str2[j]，这个字符在LCS中。接下来看dp[i - 1][j - 1]
     *    如果 dp[i][j] = dp[i - 1][j]，说明是源于dp[i - 1][j]。而dp[i - 1][j]可能也源于它的前一个，此时无法确定，继续上移
     *    如果 dp[i][j] = dp[i][j - 1]，同前一条继续左移
     */
    public String getLCS(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        //通过动态规划得到最长公共子序列长度
        int[][] dp = getdp(chs1, chs2);
        //通过整个dp矩阵的状态，计算最长公共子序列
        int m = chs1.length - 1;
        int n = chs2.length - 1;

        char[] res = new char[dp[m][n]];
        int maxCommSeqLen = res.length - 1;
        while (maxCommSeqLen >= 0){
            if (m > 0 && dp[m][n] == dp[m - 1][n]){
                m--;
            }
            else if (n > 0 && dp[m][n] == dp[m][n - 1]){
                n--;
            }
            else {
                res[maxCommSeqLen--] = chs1[m];
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }

    public int[][] getdp(char[] str1, char[] str2) {
        int[][] dp = new int[str1.length][str2.length];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < str1.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], str1[i] == str2[0] ? 1 : 0);
        }
        for (int j = 1; j < str2.length; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], str1[0] == str2[j] ? 1 : 0);
        }
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        String str1 = "A1BC2D3EFGH45I6JK7LMN";
        String str2 = "12OPQ3RST4U5V6W7XYZ";
        LongestCommSequence lcs = new LongestCommSequence();
        System.out.println(lcs.getLCS(str1, str2));

        String a = "blog.csdn.net";
        String b = "csdn.blogt";
        System.out.println(lcs.getLCS(a, b));
    }
}
