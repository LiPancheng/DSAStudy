package datastructure.arrayandmatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组，返回数组的所有子集
 * 如[1, 2, 3]
 * [
 [],
 [1],
 [2],
 [3],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2]
 ]
 */
public class AllSubsetsForArr {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        List<List<Integer>> res = new AllSubsetsForArr().subsets2(arr);
        for (List<Integer> l : res) {
            for (int a : l) {
                System.out.print(a);
            }
            System.out.println();
        }
    }

    /**
     * 递归回溯
     * 原数组中的每个元素有两种状态：存在和不存在。
     * 1. 外层循环逐一往中间集合 list 中加入元素 nums[i]，使这个元素处于存在状态
     * 2. 开始递归，递归中携带加入新元素的 list，并且下一次循环的起始是 i 元素的下一个，因而递归中更新 i 值为 i + 1
     * 3. 将这个从中间集合 list 中移除，使该元素处于不存在状态
     */
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        dfs(res, nums, list, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> list, int index){
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, nums, list, i + 1);
            list.remove(list.size() - 1);
        }
    }


    /**
     * 组合非递归
     * 1. 最外层循环逐一从 nums 数组中取出每个元素 num
     * 2. 内层循环从原来的结果集中取出每个中间结果集，并向每个中间结果集中添加该 num 元素
     * 3. 往每个中间结果集中加入 num
     * 4. 将新的中间结果集加入结果集中
     */
    public List<List<Integer>> subsets1(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int n : nums) {
            int size = res.size(); // 不能写到for循环中，因为res的大小处于变化中
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i)); // 注意需重新生成，不能引用获取到的
                temp.add(n);
                res.add(temp);
            }
        }
        return res;
    }


    /**
     * 按位取
     * 对于取元素，000表示一个都不取，001表示只取第一个，111表示三个都取
     */
    public List<List<Integer>> subsets2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        int size = 1 << len;
        for (int i = 0; i < size; i++) {
            List<Integer> temp = new ArrayList<>();
            int v = i;
            for (int k = 0; k < len; v >>= 1, k++) {
                if ((v & 1) != 0)
                    temp.add(nums[k]);
            }
            res.add(temp);
        }
        return res;
    }
}
