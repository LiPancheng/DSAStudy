package datastructure.arrayandmatrix;

/**
 * 给定数组arr，返回arr的最长递增子序列.
 * （单调递增,子序列不要求连续，顺序不乱即可）
 * 如 arr=[10,22,9,33,21,50,41,60,80],返回的最长递增子序列为[10,22,33,41,60,80]
 */
public class LongestIncreasingSubsequence {
    /**
     * 计算最长递增子序列的长度
     * dp[i]表示到arr[0...i]中最长递增子序列的长度
     */
    public int lenOfLIS(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1; // 初始化
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = 0;
        for (int v : dp) {
            max = v > max ? v : max;
        }
        return max;
    }

    /**
     * 获取最长递增子序列
     * 先得到长递增子序列的长度和子序列最后元素的位置
     */
    public int[] getLIS(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            dp[i] = 1; // 初始化
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int count = 0, index = -1;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > count){
                count = dp[i];
                index = i;
            }
        }
        //找到最大dp[i]，从后往前，如果dp[i]==dp[j]+1,且arr[i]>arr[j]，则可知arr[j]是子序列中arr[i]前面的数
        int[] res = new int[count];
        res[--count] = arr[index]; //由于数组下标问题，需先自减，
        for (int i = index - 1; i >= 0; i--) {
            if (dp[i] == count && arr[i] < arr[index]){
                res[--count] = arr[i];
                index = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] arr1 = new int[]{10,22,9,33,21,50,41,60,80};
        int[] arr2 = new int[]{2,1,5,3,6,4,8,9,7};
        int[] arr3 = new int[]{5, 6, 7, 1, 2, 8};

        lis.printArray(lis.getLIS(arr1));
        lis.printArray(lis.lis2(arr1));

        lis.printArray(lis.getLIS(arr2));
        lis.printArray(lis.lis2(arr2));

        lis.printArray(lis.getLIS(arr3));
        lis.printArray(lis.lis2(arr3));

        int[] arr4 = lis.generatePositiveArray(20);
        lis.printArray(lis.getLIS(arr4));
        lis.printArray(lis.lis2(arr4));
    }



    private int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 100) + 1;
        }
        return result;
    }

    public static int[] generateLIS(int[] arr, int[] dp) {
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        int[] lis = new int[len];
        lis[--len] = arr[index];
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                lis[--len] = arr[i];
                index = i;
            }
        }
        return lis;
    }

    public int[] lis2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[] dp = getdp2(arr);
        return generateLIS(arr, dp);
    }

    public int[] getdp2(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);
            ends[l] = arr[i];
            dp[i] = l + 1;
        }
        return dp;
    }

    // for test
    public void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
