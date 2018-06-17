package datastructure.stackqueue;

import java.util.LinkedList;

/**
 * 滑动窗口最大值数组
 *
 * 有一个大小为n的整型数组arr和一个大小为w的滑动窗口，窗口从左向右滑过数组，每次向右移动一个位置
 * 求滑动过程中窗口中的最大值（n-w+1个）
 *
 * 要求：时间复杂度为O(n)
 */
public class SlideWindow {

    /**
     * 思路：利用一个双端队列qmax实现窗口最大值的更新,qmax存储数组下标
     */
    public int[] maxValuesInWindow(int[] arr, int w){
        int len = arr.length;
        int[] res = new int[len-w+1];
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int i = 0; i < len-1; i++) {
            while (!qmax.isEmpty() && arr[i] >= arr[qmax.getLast()]){
                qmax.removeLast();
            }
            qmax.addLast(i); //对于每一个下标都进双端队列，反正遇到较大值时会移除所有小的
            if (qmax.getFirst() == i - w){
                qmax.removeFirst();
            }
            if (i >= w-1){ //从下标w-1开始（即第一个窗口）应该记录窗口最大值，每移动一位记录一个
                res[i-w+1] = arr[qmax.getFirst()];
            }
        }
        return res;
    }
}
