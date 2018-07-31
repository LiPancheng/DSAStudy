package algorithms;


/**
 * 动态规划
 * 具有“最优子结构”、“子问题重叠”、“边界”和“子问题独立”四个性质，
 * 当你发现你正在思考的问题具备这四个性质的话，那么你基本上已经找到了动态规划的方法
 *
 * 遇到问题如何用动态规划去解决呢？根据上面的分析我们可以按照下面的步骤去考虑：
   1、构造问题所对应的过程。
   2、思考过程的最后一个步骤，看看有哪些选择情况。
   3、找到最后一步的子问题，确保符合“子问题重叠”，把子问题中不相同的地方设置为参数。
   4、使得子问题符合“最优子结构”。
   5、找到边界，考虑边界的各种处理方式。
   6、确保满足“子问题独立”，一般而言，如果我们是在多个子问题中选择一个作为实施方案，而不会同时实施多个方案，那么子问题就是独立的。
   7、考虑如何做备忘录，防止重复计算。
   8、分析所需时间是否满足要求。
   9、写出转移方程式。
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


    /**
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
    private int[][] mineInfo = {{77,92},{22,22},{29,87},{50,46},{99,90}};
    private int[][] record = new int[101][6];

    private int miningSubProgress(int p, int n){
        if (p <= 0 || n <= 0 || p < mineInfo[n-1][0]){
            return 0;
        }
        else {
            int x = record[p-mineInfo[n-1][0]][n-1];
            int y = record[p][n-1];

            record[p][n] = Math.max(
                    (x > 0 ? x : miningSubProgress(p-mineInfo[n-1][0], n-1)) + mineInfo[n-1][1],
                    y > 0 ? y : miningSubProgress(p, n-1));
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
