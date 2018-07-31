package datastructure.arrayandmatrix;

import java.util.HashMap;

/**
 * 给定一个无序数组arr，以及一个整型值sum，
 * 求数组中所有 子数组元素相加和等于sum 的最长子数组长度
 */
public class MaxSubarrayLenInArray {
    /**
     * @param positiveArr 给定数组元素全为正数
     */
    public int maxSubarraySumLen(int[] positiveArr, int sum){
        int res = 0;
        for (int i = 0; i < positiveArr.length; i++) {
            int sumTmp = positiveArr[i];
            for (int j = i+1; j < positiveArr.length; j++) {
                if (sumTmp + positiveArr[j] < sum){
                    sumTmp += positiveArr[j];
                }
                else if (sumTmp + positiveArr[j] > sum){
                    break;
                }
                else {
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度为O（N）,空间复杂度为O（1）的解法
     *
     * 用两个位置left和right来标记子数组的两头，初始都为0
     * sum表示子数组元素和，len始终记录满足累加和为 k 的子数组最大长度
     * 根据sum和len的比较，决定是left移动还是right移动
     *
     * @param positiveArr 给定数组元素全为正数
     * @param k 给定累加和值
     */
    public int maxSubarraySumLen1(int[] positiveArr, int k) {
        if (positiveArr == null || positiveArr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = positiveArr[0];
        int len = 0;
        while (right < positiveArr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= positiveArr[left++];
            } else if (sum < k) {
                right++;
                if (right == positiveArr.length) {
                    break;
                }
                sum += positiveArr[right];
            } else {
                sum -= positiveArr[left++];
            }
        }
        return len;
    }

    /**
     * 当数组元素可正可负可为0时
     * 普通解法
     *
     * @param arr 数组元素可正可负可为0
     */
    public int maxLength(int[] arr, int k) {
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k){
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    /**
     * 当数组元素可正可负可为0时
     * 时间复杂度为O（N）,空间复杂度为O（N）的解法
     *
     * 对于子数组arr[j...i], sum(j+1,i) = sum(0,i) - sum(0,j)
     * 所以用一个map记录累加和为sum的最早下标
     *
     * @param arr 数组元素可正可负可为0
     */
    public int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0, len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        //注意，当累加和等于k（即sum(0,i)==k 而 sum(0,j)==0）时，此时长度为 i+1
        //从直观上讲是当前位置到开始位置的元素个数为i+1，从公式上讲是由于sum(j+1,i)的j+1>=1,没考虑0位置开始的子数组
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (!map.containsKey(sum)){
                map.put(sum, i);
            }
            if (map.containsKey(sum - k)){
                len = Math.max(len, i - map.get(sum - k));
            }
        }
        return len;
    }

    private int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSubarrayLenInArray arrayLen = new MaxSubarrayLenInArray();
        /*int len = 20;
        int sum = 10;
        int[] arr = arrayLen.generatePositiveArray(len);*/
        int[] arr = new int[]{1, 4, 3, 2, 4, 1, 1};
        System.out.println(arrayLen.maxLength(arr, 8));
        System.out.println(arrayLen.maxLength1(arr, 8));
        //assert arrayLen.maxLength(arr, 6) == arrayLen.maxLength1(arr, 6);
    }
}
