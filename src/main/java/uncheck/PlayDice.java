package uncheck;

import java.util.Scanner;

/**
 * 掷骰子
 * 有一个 n 面的骰子，掷每一面都是等概率的。其中有 m 个奖励面
 * 如果投掷道奖励面则可以再投掷一次。玩家每投掷一次都已获得掷到那一面的分数
 * 求玩家可获得分数的期望
 *
 * 输入
 * 第一行两个正整数n m (0 <= m < n <= 10^9)
 * 第二行输入 n 个数字用空格隔开，表示 n 个面的分数
 *
 * 输出期望，保留两位小数
 *
 * 示例
 * 6 1
 * 1 1 1 1 1 1
 * 输出
 * 1.20
 */
public class PlayDice {

    /**
     * 设 n 个面的面值（分数）各自为 a[1] a[2] ... a[n]
     * 每个面掷到的概率均为 1/n
     *
     * 当投掷到奖励面时，由于还可以投掷一次，下次投掷得到的面值即归约为玩家投掷一次
     * 可获得分数的期望，仍为E
     *
     * E = a[1] /n + a[2] /n + ... + a[n-m] / n
     *             + (a[n-m+1] + E) / n + ... (a[n] + E) / n
     * 化简求得
     * E = (a[1] + a[2] ...+ a[n]) / (n - m)
     *   = S / (n - m)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = sum + sc.nextInt();
        }
        System.out.println(sum / ( n - m));
    }
}
