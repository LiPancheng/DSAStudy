package newIdea;

/**
 * 给定一个整型数组 arr 和一个大于 1 的正整数的 k .其中只有一个数出现 1 次，其余数出现 k 次，找到这个出现 1 次的数
 */
public class FindOnceNum {
    /**
     * k 进制的两个数a、b，在第i位上相加的结果是 (a[i] + b[i]) % k
     * 则 k 个 相同的 k 进制数进行无进位加，相加结果一定是每一位都是 0 的 k 进制数
     * 所以遍历数组，将每一个数都转换为 k 进制数，然后进行无进位累加，结束后将结果转换回十进制就是答案
     */
    public int onceNum(int[] arr, int k){
        return 0;
    }
}
