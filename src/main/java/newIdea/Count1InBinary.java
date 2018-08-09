package newIdea;

/**
 * 给定32位整数，计算此整数的二进制表达中1的个数
 */
public class Count1InBinary {
    /**
     * 常规做法，不断右移，判定最低位是否位1
     * 需循环 32 次
     */
    public int count1(int num){
        int res = 0;
        while (num != 0){
            res += num & 1;
            num >>= 1;
        }
        return res;
    }

    /**
     * 改进做法，只需循环 （1的个数） 次
     * 如 n = 010000100 , n-1 = 01000011 , n & (n - 1) = 010000000
     * n & (n - 1)实质是去掉 n 中最右边的那个 1
     */
    public int count2(int num){
        int res = 0;
        while (num != 0){
            num &= (num - 1);
            res++;
        }
        return res;
    }

    /**
     * 平行算法
     * 该算法和归并排序很像，首先算出相邻两位1的个数，再算相邻四位1的个数...直到相邻32位
     */
    public int count3(int n){
        n = (n & 0x55555555) + ((n>>1) & 0x55555555);
        n = (n & 0x33333333) + ((n>>2) & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n>>4) & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n>>8) & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n>>16) & 0x0000ffff);
        return n;
    }
}
