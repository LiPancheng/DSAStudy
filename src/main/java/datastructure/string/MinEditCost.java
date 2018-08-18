package datastructure.string;

/**
 * 最小编辑代价
 */
public class MinEditCost {
    public static int minCost1(String str1, String str2, int ic, int dc, int rc){
        if (str1.length() == 0){
            return str2.length() * ic;
        }
        if (str2.length() == 0){
            return str1.length() * dc;
        }
        if (str1.equals(str2)){
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int[][] dp = new int[chs1.length][chs2.length];

        for (int i = 0; i < chs1.length; i++) {
            dp[i][0] = i * dc;
        }
        for (int i = 0; i < chs2.length; i++) {
            dp[0][i] = i * ic;
        }
        for (int i = 1; i < chs1.length; i++) {
            for (int j = 1; j < chs2.length; j++) {
                if (chs1[i] == chs2[j]){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = dp[i - 1][j  -1] + Math.min(rc, dc + ic);
                }
                dp[i][j] = Math.min(dp[i][j], dc + dp[i - 1][j]);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
            }
        }
        return dp[chs1.length - 1][chs2.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCost1("abc", "adc", 5, 3, 2));
        System.out.println(minCost2("abc", "adc", 5, 3, 2));

        System.out.println(minCost1("ab12cd3", "abcdf", 5, 3, 2));
        System.out.println(minCost2("ab12cd3", "abcdf", 5, 3, 2));

        System.out.println(minCost1("abcdf", "ab12cd3", 3, 2, 4));
        System.out.println(minCost2("abcdf", "ab12cd3", 3, 2, 4));

        System.out.println(minCost1("", "ab12cd3", 1, 7, 5));
        System.out.println(minCost2("", "ab12cd3", 1, 7, 5));

        System.out.println(minCost1("abcdf", "", 2, 9, 8));
        System.out.println(minCost2("abcdf", "", 2, 9, 8));
    }

    public static int minCost2(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) { // str2较长就交换ic和dc的值
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i++) {
            int pre = dp[0]; // pre表示左上角的值
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j++) {
                int tmp = dp[j]; // dp[j]没更新前先保存下来
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp; // pre变成dp[j]没更新前的值
            }
        }
        return dp[shorts.length];
    }
}
