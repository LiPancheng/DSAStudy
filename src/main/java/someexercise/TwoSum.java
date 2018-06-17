package someexercise;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class TwoSum {
    /**
     * 先将数组建立为哈希表
     * 再遍历搜索
     * 由于哈希表的搜索平均为O(1)，此方法比暴力搜索嵌套for循环的方式好
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer j = map.get(complement);
            if ((j != null) && (i != j)){
                res[0] = i;
                res[1] = j;
                break;
            }
        }
        return res;
    }

    /**
     * 建哈希的同时搜索，只需一个for循环
     * 表面上看会比上一个方法快，但其在leetcode测试用例上用时表现并不比上一个方法好，
     * 猜测原因为由于大多时候的值还没建立哈希，因此每次map.get(complement)时都要遍历完整个map才返回没找到
     */
    public static int[] twoSumAnother(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer j = map.get(complement);
            if ((j != null) && (i != j)){
                res[0] = j;
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * leetcode耗时最短的答案
     */
    public int[] twoSumNo1(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2)
            return res;
        int max = nums[0];
        int min = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i])
                max = nums[i];
            if (min > nums[i])
                min = nums[i];
        }

        int[] index = new int[max - min+1];
        target = target ;
        int other =0;
        for (int i = 0; i < nums.length; i++) {
            other = target - nums[i];
            if(other < min || other > max) {
                continue;
            }
            if(index[other- min] > 0) {
                res[0] = index[other -min ]-1;
                res[1] = i;
                return res;
            }
            index[nums[i] - min] = i+1;
        }
        return res;
    }

}
