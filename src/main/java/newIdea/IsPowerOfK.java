package newIdea;

/**
 * 判断一个整数是否是 K 的幂
 */
public class IsPowerOfK {

    /**
     * 2的幂也有个特点，如2（0x10）、16（0x10000）。就是只有一位为1
     */
    boolean isPowerOf2(int n) {
        return n > 0 && (n & (n-1)) == 0;
    }


    /**
     * 利用对数的换底公式来做，换底公式为loga(b) = logc(b) / logc(a)，
     * 那么如果n是3的倍数，则log3(n)一定是整数，我们利用换底公式可以写为log3(n) = log10(n) / log10(3)
     * 最后一步则需判断结果是否为整数
     */
    boolean isPowerOf3(int n) {
        double res = Math.log10(n) / Math.log10(3);
        return res - (int)res == 0;
    }


    /**
     * 4的幂的数首先必然是2的幂，但反之不然，如8
     * 其次，4的幂也有个特点，如4（0x100）、16（0x10000）。就是必有奇数位为1
     * 因此获取其奇数位（与上0x0101010101010101）判断是否不为0
     *
     * 注意，4的N次幂不会小于零
     */
    public boolean isPowerOf4(int n){
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
    }

}
