package datastructure.arrayandmatrix;

/**
 * 输入一个数组，它由数字构成，可以表示成一个数A，反转该数得到B，求A与B差的绝对值。（不允许使用Java类库）
 * 如[1,2,3] , 321-123 = 198, 输出 [1,9,8]
 * [1,2,1],输出[0]
 */
public class ArraySubtraction {
    public static int[] solve(int[] digits) {
        int[] reverse = new int[digits.length];
        for (int i = digits.length - 1, j = 0; i >= 0; i--, j++) {
            reverse[j] = digits[i];
        }
        int[] result = new int[digits.length];
        for (int i = 0; i < digits.length - 1; i++) {
            if (digits[i] - reverse[i] > 0){
                arrSub(digits, reverse, result);
                break;
            }
            else if (digits[i] - reverse[i] < 0){
                arrSub(reverse, digits, result);
                break;
            }
        }
        return result;
    }

    private static int[] arrSub(int[] m, int[] n, int[] result){
        int flag = 0;
        for (int i = m.length - 1; i >= 0; i--) {
            int sub;
            if (m[i] >= n[i] + flag){
                sub = m[i] - flag - n[i];
                flag = 0;
            }
            else {
                sub = m[i] - flag + 10 - n[i];
                flag = 1;
            }
            result[i] = sub;
        }
        return result;
    }

    public static int[] solve1(int[] digits) {
        int n = digits.length;
        int[] subArr = new int[n];
        boolean biggerThanReverse = true;
        for (int i = 0; i < n; i++){
            if (digits[i] < digits[n-i-1]){
                biggerThanReverse = false;
                break;
            }
        }
        int flag = 0;
        for (int i = 0; i < n; i++) {
            int sub = 0;
            if (biggerThanReverse) {
                sub = digits[n-1-i] - flag - digits[i];
            }
            else {
                sub = digits[i] - flag - digits[n-1-i];
            }
            if (sub < 0) {
                flag = 1;
                subArr[n - i - 1] = sub + 10;
            } else {
                flag = 0;
                subArr[n - i - 1] = sub;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (subArr[i] == 0)
                count++;
            else break;
        }
        if (count == n){
            return new int[]{0};
        }
        else {
            int len = n - count > 0 ? n - count : 1;
            int[] result = new int[len];
            System.arraycopy(subArr, count, result, 0, len);
            return result;
        }
    }

    public static void main(String[] args) {
        int[] m = new int[]{1,3,2, 4};
        int[] res  = ArraySubtraction.solve1(m);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
