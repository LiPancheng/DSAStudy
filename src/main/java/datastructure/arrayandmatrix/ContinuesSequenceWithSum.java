package datastructure.arrayandmatrix;

import java.util.ArrayList;

/**
 * 题目描述
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,
 * 他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的 找出所有和为S的连续正数序列?
 * 输出描述:
 * 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
 */
public class ContinuesSequenceWithSum {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new ContinuesSequenceWithSum().FindContinuousSequence3(90);
        res.forEach(System.out::println);
    }

    /**
     * 基本解法
     * 按照序列起始值依次递增的顺序，检测以当前起始值开始的一段序列是否满足和为sum
     */
    private ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3)
            return res;
        int start = 1;
        for (int i = start; i <= sum / 2; i++) {
            int tmp = i;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(i);
            for (int j = i + 1; j < sum; j++) {
                tmp += j;
                list.add(j);
                if (tmp == sum){
                    res.add(list);
                    break;
                }
                else if (tmp > sum)
                    break;
            }
        }
        return res;
    }

    /**
     * 解法2
     * 保存上一段计算的序列和 curSum，
     * 当curSum < sum 时，累加值
     * 当curSum == sum 时，添加序列。然后序列向右挪一位，即curSum 减去前一序列的起始值
     * 当curSum > sum 时，不断减去序列的起始值直到curSum < sum
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3)
            return res;
        int start = 1, curSum = 0;
        for (int i = start; i <= sum / 2 + 1; i++) {
            curSum += i;
            if (curSum > sum){
                while (curSum > sum){
                    curSum -= start;
                    start++;
                }
            }
            if (curSum == sum){
                ArrayList<Integer> list = new ArrayList<>(i - start + 1);
                for (int j = start; j <= i; j++) {
                    list.add(j);
                }
                res.add(list);
                curSum -= start;
                start++;
            }
        }
        return res;
    }

    /**
     * 解法3
     * 1）由于要找的序列是个公差为1的等差数列，序列的中间值即是平均值的大小。
     * 假设序列长度为n，那么这个序列的中间值为S / n，知道序列的中间值和长度，则可求出这段序列。
     * 2）满足条件的n分两种情况：
     * n为奇数时，序列中间的数正好是序列的平均值，所以条件为：(n & 1) == 1 && sum % n == 0；
     * n为偶数时，序列中间两个数的平均值是序列的平均值，而这个平均值的小数部分为0.5，即是序列长的一半，所以 sum % n == n / 2.
     * 3）由题可知n >= 2，那么n的最大值是多少呢？我们完全可以将n从2到S全部遍历一次，但是大部分遍历是不必要的。
     * 设最大序列长为 k (序列最长那就是初始值从1开始，即1 2 ....k), 根据求和公式S = (1 + k) * k / 2 , 得 k < (2 * sum)^ -2
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence3(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 3)
            return res;
        for (int n = (int)Math.sqrt(2 * sum); n >= 2; n--) {
            if ((n & 1) == 1 && sum % n == 0 || (sum % n) * 2 == n){
                ArrayList<Integer> list = new ArrayList<>(n);
                int start = sum / n - (n - 1) / 2;
                for (int j = 0; j < n; j++) {
                    list.add(start);
                    start++;
                }
                res.add(list);
            }
        }
        return res;
    }
}
