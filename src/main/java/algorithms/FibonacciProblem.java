package algorithms;

/**
 * 1. 递归会有大量重复计算，复杂度为 O(2^n)
 * 2. 消除尾递归，直接递推，复杂度降为 O(N)
 * 3. 如果递推式严格符合F(n) = a*F(n-1) + b*F(n-2) + .... + k*F(n-i), 那么他就是一个 i 阶的递推式，必然有与 ixi 的状态矩阵
 *    有关的矩阵乘法的表达。这种情况下可以使用加速矩阵乘法的动态规划将复杂度降为O(logN)
 */
public class FibonacciProblem {
    /*斐波那契数列*/
    public int fibonacci(int n){
        if (n < 1)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        else
            return fibonacci(n - 2) + fibonacci(n-1);
    }

    public int fibonacciNonRec(int n){
        if (n < 1)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int prepre = 1, pre = 1, cur = 2;
        for (int i = 3; i < n; i++) {
            cur = prepre + pre;
            prepre = pre;
            pre = cur;
        }
        return cur;
    }

    public int fibonacciUseMatrix(int n){
        if (n < 1)
            return 0;
        if (n == 1 || n == 2)
            return 1;
        int[][] base = {{1, 1},{1, 0}};
        int[][] res = matrixPower(base, n-1);
        return res[0][0] + res[1][0];
    }

    /*
    * 给定整数N，代表台阶数。一次可以跨一个或两个阶梯，返回共有多少种走法
    * 例如N=3，可以三次都跨一个，或者先一个在两个，或者先两个再一个，共三种
    */
    public int routesNum(int n){
        if (n < 1)
            return 0;
        else if (n == 1 || n == 2)
            return n;
        else return routesNum(n-1) + routesNum(n-2);
    }

    public int routesNumNonRec(int n){
        if (n < 1)
            return 0;
        else if (n == 1 || n == 2)
            return n;
        int prepre = 1, pre = 2, cur = 3;
        for (int i = 3; i < n; i++) {
            cur = prepre + pre;
            prepre = pre;
            pre = cur;
        }
        return cur;
    }


    /*
    *
    */
    public int getCowNum(int year){
        if (year < 0)
            return 0;
        if (year >= 1 && year <= 4)
            return year;
        /*今年的牛 = 去年的牛 + 今年新出生的牛
        * 今年新出生的牛数量 = 三年前的牛数量（因为三年前那些牛到今年全部能生育，每头生一头）*/
        else return getCowNum(year-1) + getCowNum(year-3);
    }

    public int getCowNumNonRec(int year){
        if (year < 0)
            return 0;
        if (year >= 1 && year <= 4)
            return year;
        int pre3 = 2, pre = 4, cur = 6;
        for (int i = 5; i < year; i++) {
            cur = pre3 + pre;
            pre3 = pre;
            pre = cur;
        }
        return cur;
    }

    public int getCowNumUseMatrx(int year){
        if (year < 0)
            return 0;
        if (year >= 1 && year <= 4)
            return year;
        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, year-3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }


    /**
     * 矩阵相乘
     */
    private int[][] mulmatrix(int[][] m1, int[][] m2){
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 求矩阵的幂
     * 能进行幂运算的一定是n阶矩阵（方阵）
     *
     * 求矩阵的幂的最快方法为：（以整数为例 10^75）
     * 1. 75的二进制为1001011
     * 2. 10^75 = 10 * 10^2 * 10^8 * 10^64
     */
    private int[][] matrixPower(int[][] m, int power){
        int[][] res = new int[m.length][m.length];
        // 先把res设为单位矩阵。
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = res;
        for (; power !=0; power >>= 1) {
            if ((power & 1) != 0){
                res = mulmatrix(res, tmp);
            }
            tmp = mulmatrix(tmp, tmp);
        }
        return res;
    }
}
