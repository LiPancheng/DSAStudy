package datastructure.string;


import java.util.HashMap;

/**
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
        示例：
        给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
        给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
        给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。

       "c" 输出1，"dvdf"输出3
 */
public class LongestRepeatString {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringNo1("xcmmrjdox"));
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int from = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer last = map.get(c);
            if (last != null){
                from = from > last ? from : (last + 1);
            }
            maxLen = Math.max(maxLen, i - from + 1);
            map.put(c, i);
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstringNo1(String s) {
        int max = 0;
        int from = 0;

        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++){
            for(int j = from; j < i; j++){
                if(c[i] == c[j]){
                    from = j + 1;
                    break;
                }
            }
            max = Math.max(max, i- from + 1);
        }
        return max;

    }
}
