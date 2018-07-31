package datastructure.string;

/**
 * 给定一个字符串str，求其中全部数字串所代表的数字之和
 * 要求 1.忽略小数点的意义，如“A1.2" 包含两个数字1和2
 *      2.紧贴数字字串左侧如果是‘-’，连续出现奇数次，数字视为负数，连续出现偶数次，数字视为正数。如“A-1CD--2”包含两个数字-1和2
 */
public class AllNumbersSum {
    /**
     * num为收集的数字代表的值，isPositive代表当前数字的正负
     * 遍历字符序列
     * 如果是数字字符，则更新收集的数字
     * 如果不是数字字符，则此时都应该累加num，然后num归0。然后分其是‘-’字符和其他字符讨论
     */
    public int numSumInString(String str){
        int sum = 0, num = 0;
        boolean isPositive = true;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int k = chars[i] - '0';
            if (k < 0 || k > 9){
                sum += num;
                num = 0;
                isPositive = chars[i] != '-' || (i - 1 > -1 && chars[i-1] == '-' && !isPositive);
                /*if (chars[i] == '-'){
                    if (i - 1 > -1 && chars[i-1] == '-'){
                        isPositive = !isPositive;
                    }
                    else {
                        isPositive = false;
                    }
                }
                else {
                    isPositive = true;
                }*/
            }
            else {
                num = 10 * num + (isPositive ? k : -k);
            }
        }
        sum += num;
        return sum;
    }

    public static void main(String[] args){
        System.out.println(new AllNumbersSum().numSumInString("1K-100ABC500D-T--100F200G!!100H---300"));

    }
}
