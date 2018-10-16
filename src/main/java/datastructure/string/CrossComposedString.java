package datastructure.string;

/**
 * 字符串的交错组成
 *
 * 给定三个字符串，如果str1 str2 aim 如果aim包含且仅包含来自str1 str2 的所有字符，
 * 而且在aim中属于str1的字符的顺序也保持原来在str1中的顺序，属于str2的字符之间也保持原来在str2中的顺序，
 * 则称aim为str1 str2的交错组成
 *
 */
public class CrossComposedString {
    public static void main(String[] args) {
        System.out.println(new CrossComposedString().isCrosString("ABCDEF", "12345", "1A2B34CD5EF"));
    }

    /**
     * 动态规划法
     * dp[i][j] 表示str1[0...i-1]、str2[0...j-1]、aim[0...i+j-1]三个字符串中，aim[0...i+j-1]是否是前两者的交错组成
     */
    public boolean isCrosString(String str1, String str2, String aim){
        if (aim.length() != str1.length() + str2.length()){
            return false;
        }
        boolean[][] dp = new boolean[str1.length() + 1][str2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < str1.length(); i++) {
            //dp[i][0] = aim.charAt(i - 1) == str1.charAt(i - 1);
            if (aim.charAt(i - 1) != str1.charAt(i - 1)){
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j < str2.length(); j++) {
            if (aim.charAt(j - 1) != str2.charAt(j - 1)){
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                char c = aim.charAt(i + j - 1);
                if ((dp[i - 1][j] && c == str1.charAt(i - 1)) ||
                        (dp[i][j - 1] && c == str2.charAt(j - 1))){
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
