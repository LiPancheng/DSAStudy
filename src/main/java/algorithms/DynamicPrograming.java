package algorithms;


import java.util.Scanner;

/**
 * 动态规划
 * 具有“最优子结构”、“子问题重叠”、“边界”和“子问题独立”四个性质，
 *
 * 动态规划中最重要的两个概念： 状态 和 状态转移方程
 */
public class DynamicPrograming {

    /**
        7
        3   8
        8   1   0
        2   7   4   4
        4   5   2   6   5
        在上面的数字三角形中寻找一条从顶部到底边的路径，使得路径上所经过的数字之和最大。
    路径上的每一步都只能往左下或 右下走。只需要求出这个最大和即可，不必给出具体路径。
    三角形的行数大于1小于等于100，数字为 0 - 99
    http://blog.csdn.net/baidu_28312631/article/details/47418773

     @param linesCount 行数
     @param value 此三角形

     测试：
     int[][] value = {{7,0,0,0,0},{3,8,0,0,0},{8,1,0,0,0},{2,7,4,4,0},{4,5,2,5,6}};
     System.out.println(triangleMaxPath(5, value));
     */
    private int triangleMaxPath(int linesCount, int[][] value){
        int maxIndex = linesCount - 1;
        for (int i = 0; i < maxIndex; i++) {
            value[maxIndex][i] = value[maxIndex-1][i] + Math.max(value[maxIndex][i], value[maxIndex][i+1]);
        }
        for (int i = maxIndex - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                value[maxIndex][j] += Math.max(value[i][j], value[i][j+1]);
            }
        }
        return value[maxIndex][0];
    }


    /*
     输入第一行有两个数，第一个是国王可用用来开采金矿的总人数，第二个是总共发现的金矿数。
     输入的第2至n+1行每行有两个数，第i行的两个数分别表示第i-1个金矿需要的人数和可以得到的金子数。

     输出仅一个整数，表示能够得到的最大金子数
     http://blog.csdn.net/woshioosm/article/details/7438834

     输入样例：
     100 5
     77 92
     22 22
     29 87
     50 46
     99 90

     输出样例：
     133
     */
    /**
     * 动态规划法，与经典01背包问题类似
     * 动态规划的两个关键概念：
     *     状态： dp[i][j]表示前i个矿用j个人来挖，所取得的最大价值
     *     状态转移方程： dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - people[i]] + value[i]);
     */
    private int maxMiningValue(){
        Scanner sc = new Scanner(System.in);
        int peopleNum = sc.nextInt();
        int mineNum = sc.nextInt();
        int[][] mineInfo = new int[mineNum + 1][2];
        for (int i = 1; i <= mineNum; i++) {
            mineInfo[i][0] = sc.nextInt(); // 挖矿所需人数
            mineInfo[i][1] = sc.nextInt(); // 矿的价值
        }
        // dp[i][j]表示前i个矿用j个人来挖，所取得的最大价值
        int[][] dp = new int[mineNum + 1][peopleNum + 1];
        for (int i = 1; i <= mineNum; i++) {
            for (int j = 1; j <= peopleNum; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - mineInfo[i][0] >= 0){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i - 1][j - mineInfo[i][0]] + mineInfo[i][1]);
                }
            }
        }
        return dp[mineNum][peopleNum];
    }

    /**
     * 博客中的解法，子结构递归的方式
     * 实际思想与上一解法一样
     */
    private int[][] mine = {{77,92},{22,22},{29,87},{50,46},{99,90}};
    private int[][] record = new int[101][6];

    private int miningGold(int p, int n){
        if (p <= 0 || n <= 0 || p < mine[n-1][0]){
            return 0;
        }
        else {
            int x = record[p- mine[n-1][0]][n-1];
            int y = record[p][n-1];

            record[p][n] = Math.max(
                    (x > 0 ? x : miningGold(p- mine[n-1][0], n-1)) + mine[n-1][1],
                    y > 0 ? y : miningGold(p, n-1));
            return record[p][n];
        }
    }


    /**
     * 给定一个矩阵m，从左上角开始每次只能往右或往下走，最后到达右下角的位置
     * 路径上所有数字累加起来就是路径和。求最小路径和
     * 题目及解答见书的第4章 递归和动态规划 问题2
     */
    public static int minPathSum(int[][] m){
        int row = m.length;
        int column = m[0].length;
        for (int i = row - 2; i > -1; i--) {
            m[i][column - 1] += m[i + 1][column - 1];
        }
        for (int i = column - 2; i > -1; i--) {
            m[row - 1][i] += m[row - 1][i + 1];
        }
        for (int i = row - 2; i > -1; i--) {
            for (int j = column - 2; j > -1 ; j--) {
                m[i][j] += Math.min(m[i+1][j], m[i][j+1]);
            }
        }
        return m[0][0];
    }


    public static void main(String[] args) {
        // int[][] m = generateRandomMatrix(3, 4);
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 },
                { 8, 8, 4, 0 } };
        //printMatrix(m);
        System.out.println(minPathSum(m));

    }
}
