package datastructure;

import java.lang.ref.WeakReference;

public class Temple {

    public static void main(String[] args) {
        int[] change = new int[]{7,  3};
        System.out.println(new Temple().minCoins1(change, 10));
    }

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


    private void qsortInDecreaseOrder(int[] arr, int start, int end){
        if (arr == null || start >= end){
            return;
        }
        int i = start, j = end, pivot = arr[(start + end) / 2];
        while (i < j){
            while (arr[i] > pivot){
                i++;
            }
            while (arr[j] < pivot){
                j--;
            }
            if (i < j){
                arr[i] ^= arr[j];
                arr[j] = arr[i] ^ arr[j];
                arr[i] ^= arr[j];
            }
            else if (i == j){
                i++;
            }
        }
        qsortInDecreaseOrder(arr, start, j);
        qsortInDecreaseOrder(arr, i, end);

    }


}
