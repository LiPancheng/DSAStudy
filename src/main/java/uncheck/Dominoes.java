package uncheck;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 多米诺骨牌
 *
 * 有 n 块多米诺骨牌放在 x 轴上，每块骨牌有自己的位置和高度，且只会向 x 轴正方向倒下
 * 处于 x 处的高度为 h 的骨牌倒下会推到 [x+1, x+h-1] 内的所有骨牌
 * 问对于每一块骨牌，如果将其推倒，至多可以倒下多少块骨牌（包含本身）
 *
 * 输入：第一行包含一个正整数n 表示多少张牌，接下来n行分别为每张骨牌的位置和高度
 * 4
 * 16 5
 * 20 5
 * 10 10
 * 18 2
 *
 * 输出
 * 3 1 4 1
 *
 */
public class Dominoes {
    /**
     * 从后往前算，以便利用已算出的结果
     * 对于第n块，能倒下a[n] = 1张牌
     * 对于第n - 1块，如果它能推到第n块，则能到下a[n] + 1
     * 以此类推，对于第 i 块，在它能推倒的 k 块中，则能倒下最多为 a[i+1] + 1、a[i+2] + 2 ...a[i+k] + k 中最大的
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> posHeightMap = new HashMap<>(n);
        int[] oriPos = new int[n];
        for (int i = 0; i < n; i++) {
            oriPos[i] = sc.nextInt();
            posHeightMap.put(oriPos[i], sc.nextInt());
        }
        int[] sortedPos = oriPos.clone();
        Arrays.sort(sortedPos);
        HashMap<Integer, Integer> resMap = new HashMap<>(n);

        for (int i = n - 1; i >= 0; i--) {
            int pos = sortedPos[i];
            int maxXPos = pos + posHeightMap.get(pos) - 1;
            int sum = 1;
            for (int j = i + 1; j < n; j++) {
                if (sortedPos[j] > maxXPos)
                    break;
                else {
                    Integer right = resMap.get(sortedPos[j]);
                    right = right != null ? right : 0;
                    sum = Math.max(sum, right + j - i);
                }
            }
            resMap.put(pos, sum);
        }
        for (int pos : oriPos) {
            System.out.print(resMap.get(pos) + " ");
        }
    }

}
