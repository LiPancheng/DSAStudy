package someexercise;

import java.util.Stack;

/**
 * 括号匹配
 * 题1：判断给定串中的括号是否匹配
 * 题2：计算至少需要添加多少个括号才能使这些括号匹配
 */
public class BracketsMatch {
    public static void main(String[] args) {
        String s = "([)]]";
        System.out.println(new BracketsMatch().leastAdd2(s));
    }

    /**
     * 常规思路 用栈实现
     */
    public boolean isMatched(String str){
        Stack<Character> stack = new Stack<>();
        char[] chs = str.toCharArray();
        for (char c : chs) {
            if (c == '(')
                stack.push(c);
            else if (c == ')'){
                if (stack.empty())
                    return false;
                else
                    stack.pop();
            }
        }
        return stack.empty();
    }

    /**
     * 只用递归实现
     */
    public boolean isMatchedUseRecur(char[] chs, int index, int unMatchedCount){
        if (unMatchedCount < 0)
            return false;
        if (index == chs.length)
            return unMatchedCount == 0;
        char c = chs[index];
        int op = c == '(' ? 1 : (c == ')' ? -1 : 0);
        return isMatchedUseRecur(chs, index + 1, unMatchedCount + op);
    }


    /**
     * 给定一个字符串，里面只包含"(",")","[","]"四种符号，请问需要至少添加多少个括号才能使这些括号匹配起来
     * ([])[] 输出 0
     * ((] 输出 3
     * ([)] 输出 2
     *
     * 思路：动态规划
     * http://lixinzhang.github.io/dp-gua-hao-pi-pei-wang-yi-you-dao-mian-shi-ti.html
     * dp[i][j]表示字符串中 i 到 j 需要至少添加多少个括号
     * 初始化的DP[i][i] = 1 ;第i个位置的话需要匹配的最小括号数是1；
     * 如果第i个位置和第j个位置的两个括号是匹配的，那么DP[i][j] = DP[i+1][j-1]，相当于两边分别往里缩了一个；
     * 如果两个边界不匹配，由于中间某位置字符k，使其分为两部分，如“(XXXXX)(XXXX)”，则有DP[i][j] = DP[i][k]+DP[k+1][j] ;
     */
    public int leastAdd2(String str){
        char[] chs = str.toCharArray();
        int[][] dp = new int[chs.length][chs.length];
        for (int i = 0; i < chs.length; i++) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < chs.length; len++) {
            for (int i = 0; i < chs.length - len; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                if ((chs[i] == '(' && chs[j] == ')') || (chs[i] == '[' && chs[j] == ']'))
                    dp[i][j] = i + 1 == j ? 0 : dp[i + 1][j - 1]; // 或者写为 dp[i][j] = min(dp[i][j], dp[i+1][j-1])

                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j]);
            }
        }
        return dp[0][chs.length - 1];
    }


}
