package newIdea;

public class FindOddCountNum {
    /**
     * 在一个整型数组中，其中只有一个数出现奇数次，其余数出现偶数次，找到这个出现奇数次的数
     */
    public int findNum(int[] arr){
        int res = 0;
        for (int v : arr) {
            res ^= v;
        }
        return res;
    }

    /**
     * 在一个整型数组中，有两个个数出现奇数次，其余数出现偶数次，打印这两个数
     *
     * 就假设这两个数是a 和 b。首先对所有数进行异或，那么得到的结果就是 a^ b , 且这个数肯定不为0，即必能找到bit位为 1 的位
     * 我们找出这个数的二进制数的最右边的1(设最右边的1在第 k 位)，说明a 和 b的第 k 位肯定一个是1另一个是0；
     * 法一：遍历数组，判断元素第 k 位是1还是0，是1的为一组，是0的为一组，两组分别进行异或，结果分别就是所求的两个数。
     * 法二：遍历数组，只对元素第 k 位是1的进行异或，这样得到就是a或b中的一个，再与a^ b进行异或就得到另一个
     */
    public void findTwoNum(int[] arr){
        int eO = 0, eOHasOne = 0;
        for (int v : arr) {
            eO ^= v;
        }
        int rightOne = eO & (~eO + 1);
        for (int v : arr) {
            if ((v & rightOne) != 0){
                eOHasOne ^= v;
            }
        }
        System.out.println(eOHasOne + " " + (eO ^ eOHasOne));
    }
}
