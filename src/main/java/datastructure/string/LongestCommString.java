package datastructure.string;

/**
 * 给定两个字符串str1 str2，返回两个字符串的最长公共子串
 * eg. str1 = "1AB2345CD", str2 = "2345EF"
 * 返回 "2345"
 */
public class LongestCommString {

    public static void main(String[] args) {
        LongestCommString lcs = new LongestCommString();
        String str1 = "1AB2345CD", str2 = "2345EF";
        //String str2 = "HIJKL1234567MNOP";
        System.out.println(lcs.getLCSUseDP(str1, str2));
        System.out.println(lcs.getLCSUseDP1(str1, str2));
    }

    /**
     * 使用Java String提供的方法
     * 对于长度较小的字符串，从长到短截取子串，判断是否此子串在另一个字符串中存在，存在则立即返回
     */
    public String getUseLib(String str1, String str2){
        if (str1 == null || str2 == null || str2.length() == 0){
            return "";
        }
        if (str1.length() < str2.length()){
            return getUseLib(str2, str1);
        }
        for (int k = str2.length(); k >= 0; k--) {
            for (int i = 0; i + k <= str2.length(); i++) {
                String expect = str2.substring(i, i + k);
                if (str1.contains(expect)){
                    return expect;
                }
            }
        }
        return "";
    }


    /**
     * 经典动态规划法
     * dp[i][j]的含义为，在必须把str1[i] str2[j]当作公共字串结尾的情况下最长公共字串长度
     * 时间复杂度O（M x N） 空间复杂度O（M x N）
     */
    public String getLCSUseDP(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return "";
        }
        int[][] dp = new int[str1.length()][str2.length()];
        int maxLen = 0, endIndex = 0;
        for (int i = 0; i <str1.length(); i++) {
            dp[i][0] = str1.charAt(i) == str2.charAt(0) ? 1 : 0;
            if (dp[i][0] > maxLen){
                maxLen = dp[i][0];
                endIndex = i;
            }
        }
        for (int i = 0; i <str2.length(); i++) {
            dp[0][i] = str1.charAt(0) == str2.charAt(i) ? 1 : 0;
            if (dp[0][i] > maxLen){
                maxLen = dp[0][i];
                endIndex = i;
            }
        }
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                dp[i][j] = str1.charAt(i) == str2.charAt(j) ? dp[i - 1][j - 1] + 1 : 0;
                if (dp[i][j] > maxLen){
                    maxLen = dp[i][j];
                    endIndex = i;
                }
            }
        }
        return str1.substring(endIndex - maxLen + 1, endIndex + 1);
    }

    /**
     * 动态规划法 空间压缩版
     * 由于本题dp[i][j]只依赖左上方dp[i - 1][j- 1]的值，因此按照斜线方向计算可以省去动态规划矩阵
     * 时间复杂度O（M x N） 空间复杂度O（1）
     */
    public String getLCSUseDP1(String str1, String str2){
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return "";
        }
        int maxLen = 0, endIndex = 0;
        int row = 0, col = str2.length() - 1;
        while (row < str1.length()){ //从i,j开始向右下方遍历
            int i = row, j = col;
            int preDP = 0;
            while (i < str1.length() && j < str2.length()){
                if (str1.charAt(i) == str2.charAt(j)){
                    preDP++;
                }
                else {
                    preDP = 0;
                }
                if (preDP > maxLen){
                    maxLen = preDP;
                    endIndex = i;
                }
                i++;
                j++;
            }
            if (col > 0){ //斜线到达边界后，先列左移
                col--;
            }
            else { //列到达最左后，行向下移
                row++;
            }
        }
        return str1.substring(endIndex - maxLen + 1, endIndex + 1);
    }
}
