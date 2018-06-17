package newIdea;

/**
 * Created by LiPancheng on 2017/8/11.
 */
public class util {

    /**
     * 返回一个大于等于给定整数的最小的2的幂
     *
     * 来源：HashMap源码的容量计算
     * 原理：
     我们首先假定cap也就是给定数的的形式为0...01XXXXXXX,(X代表可为0也可为1，X前面的1为从最高位开始第一个为1的那一位)
     首先是n |= n >>> 1;也就是n变成n与n右移一位之后异或后的值，即
     n     : 0..01XXXXXXX
     n>>>1 : 0..001XXXXXX
     新n   ：0..011XXXXXX
     也许到这里你还看不出什么结果，那接着看第二步n |= n >>> 2;也就是n变成n与n右移两位之后异或的值，即
     n     : 0..011XXXXXX
     n>>>2 : 0..00011XXXX
     新n   : 0..01111XXXX
     到这里就不用再解释了吧，这个算法不断的通过把第一个1开始后面的位变成1，最后再返回n+1;
     注意，容量最大也就是32bit的正数，最多也就32个1。因此最后只到n |= n >>> 16
     */
    static int tableSizeFor(int cap) {
        int MAXIMUM_CAPACITY = 1 << 30;
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
