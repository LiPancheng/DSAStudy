package algorithms;

public class ExchangeCoins {
    /**
     * 给定数组，其中的值都为正数且不重复，每个代表可用零钱的面值。每种面值的货币可以使用任意张
     * 再给定一个非负数aim，代表要换的钱值。求组成aim的最小货币数
     * 如[5, 2, 3], aim = 20, 返回4
     *   [5, 2, 3], aim = 0, 返回0
     *   [5, 3], aim = 2, 返回-1
     *
     * 题目及解答见书的第4章 递归和动态规划 问题3
     * */
    public int minCoins0(int[] change, int aim){
        int[] dp = new int[aim + 1];
        int max = Integer.MAX_VALUE;
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
            if (j - change[0] >= 0 && dp[j - change[0]] != max){
                dp[j] = dp[j - change[0]] + 1;
            }
        }
        for (int i = 1; i < change.length; i++) {
            for (int j = 1; j <= aim ; j++) {
                int left = max;
                if (j - change[i] >= 0 && dp[j - change[i]] != max){
                    left = dp[j - change[i]] + 1;
                }
                dp[j] = Math.min(dp[j], left);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }

    /**
     * 给定数组，其中的值都为正数，每个代表可用零钱的面值。其中每种面值的货币只能使用一张
     * 再给定一个非负数aim，代表要换的钱值。求组成aim的最小货币数
     * */
    public int minCoins1(int[] change, int aim){
        int[] dp = new int[aim + 1];
        int max = Integer.MAX_VALUE;
        for (int j = 1; j <= aim; j++) {
            dp[j] = max;
            if (j == change[0]){
                dp[j] = 1;
            }
        }
        for (int i = 1; i < change.length; i++) {
            for (int j = aim; j > 0 ; j--) { //与前一题不同，如果还是递增，则计算dp[j]时，dp[j]前面的值已经被更新了，与求解式不符
                int left = max;
                if (j - change[i] >= 0 && dp[j - change[i]] != max){
                    left = dp[j - change[i]] + 1;
                }
                dp[j] = Math.min(dp[j], left);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;
    }


    /*
     * 给定数组，其中的值都为正数且不重复，每个代表可用零钱的面值。每种面值的货币可以使用任意张
     * 再给定一个非负数aim，代表要换的钱值。求换取的方法共有多少种
     */

    /*方法一 递归的初级优化：记忆搜索*/
    public int coinsWay(int[] change, int aim){
        if (change == null || change.length == 0 || aim < 0){
            return 0;
        }
        int[][] map = new int[change.length+1][aim+1];
        return coinsWaySpe(change, 0, aim, map);
    }

    private int coinsWaySpe(int[] change, int index, int aim, int[][] map){
        int count = 0;
        if (index == change.length){
            count = aim == 0 ? 1 : 0;
        }
        else {
            for (int i = 0; change[index] * i <= aim; i++) {
                int remainCompose = map[index + 1][aim - change[index] * i];
                if (remainCompose != 0){
                    count += remainCompose == -1 ? 0 : remainCompose;
                }
                else {
                    count += coinsWaySpe(change, index+1, aim - change[index] * i, map);
                }
            }
        }
        map[index][aim] = count == 0 ? -1 : count;
        return count;
    }

    /*方法二 动态规划法*/
    public int coinsWay1(int[] change, int aim){
        if (change == null || change.length == 0 || aim < 0){
            return 0;
        }
        int[][] dp = new int[change.length][aim+1];
        for (int i = 0; i < dp.length; i++) { // 初始化第一列
            dp[i][0] = 1;
        }
        for (int i = 0; i * change[0] <= aim; i++) { // 初始化第一行
            dp[0][i * change[0]] = 1;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j <= aim; j++) {
                int num = 0;
                for (int k = j / change[i]; k >= 0; k--) {
                    num += dp[i-1][j - k * change[i]];
                }
                dp[i][j] = num;
            }
        }
        return dp[dp.length - 1][aim];
    }

    /*方法三 动态规划法的空间压缩版*/
    public int coinsWay2(int[] change, int aim){
        if (change == null || change.length == 0 || aim < 0){
            return 0;
        }
        int[] dp = new int[aim+1];
        for (int i = 0; i * change[0] <= aim; i++) { // 初始化第一行
            dp[i * change[0]] = 1;
        }
        for (int i = 1; i < change.length; i++) {
            for (int j = 0; j <= aim; j++) {
                int num = 0;
                for (int k = j / change[i]; k >= 0; k--) {
                    num += dp[j - k * change[i]];
                }
                dp[j] = num;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] change = new int[]{5, 10, 25, 1};
        System.out.println(new ExchangeCoins().coinsWay2(change, 15));
    }
}
