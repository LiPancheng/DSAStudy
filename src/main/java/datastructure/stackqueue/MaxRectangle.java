package datastructure.stackqueue;

import java.util.Stack;

/**
 * 最大子矩阵问题
 *
 * 给定一个整型矩阵map，其中值只有0或1，求其中全是1的最大矩形区域
 */
public class MaxRectangle {
    public int maxAreaRec(int[][] map){
        if (map == null || map.length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j]+1;
            }
            maxArea = Math.max(maxRecBasedOnCurrentLine(height), maxArea);
        }
        return maxArea;
    }

    public int maxRecBasedOnCurrentLine(int[] height){
        if (height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.empty() && height[i] <= height[stack.peek()]){
                int cur = stack.pop();
                int leftFirstMin = stack.empty() ? -1 : stack.peek();
                int curIndexMaxArea = (i - leftFirstMin - 1) * height[cur];
                maxArea = Math.max(maxArea, curIndexMaxArea);
            }
            stack.push(i);
        }
        while (!stack.empty()){
            int cur = stack.pop();
            int leftFirstMin = stack.empty() ? -1 : stack.peek();
            int curIndexMaxArea = (height.length - leftFirstMin - 1) * height[cur];
            maxArea = Math.max(maxArea, curIndexMaxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = new int[][]{{1,0,1,1},{1,1,1,1},{1,1,1,0}};
        System.out.println(new MaxRectangle().maxAreaRec(map));
    }
}
