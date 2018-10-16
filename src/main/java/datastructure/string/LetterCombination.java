package datastructure.string;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 17
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射与电话按键相同。注意 1 不对应任何字母。
 */
public class LetterCombination {
    static char[][] map = new char[][]{{'a', 'b', 'c'},{'d', 'e', 'f'},
            {'g', 'h', 'i'},{'j', 'k', 'l'},{'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},{'t', 'u', 'v'},{'w', 'x', 'y', 'z'}};

    public static void main(String[] args) {
        LetterCombination ins = new LetterCombination();
        System.out.println(ins.letterCombinations("27"));
    }

    /**
     * 非递归组合
     * 根据数字串求得最后的组合数，然后生成存储所有可能组合的二维字符矩阵
     * 最后根据组合规则依次填充字符
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        if (digits == null || digits.length() == 0)
            return res;
        int num = Integer.parseInt(digits);
        int count = 1;
        while (num > 0){
            int v = num % 10;
            count = count * ((v == 7 || v == 9) ? 4 : 3);
            num /= 10;
        }
        char[][] combination = new char[count][digits.length()]; // 所有可能的组合，下面依次填充
        char[] chs = digits.toCharArray();
        for (int i = 0, group = 1; i < chs.length; i++) {
            char[] letters = map[chs[i] - 50]; // 每位数字对应的字符集, 如 2 -> "a b c"
            int numPreGroup = count / letters.length / group; // 每组个数
            int index = 0;
            for (int j = 0; j < group; j++) {
                for (char c : letters) {
                    for (int k = 0; k < numPreGroup; k++) {
                        combination[index++][i] = c;
                    }
                }
            }
            group *= letters.length;
        }
        for (char[] cs : combination) {
            res.add(String.valueOf(cs));
        }
        return res;
    }
}
