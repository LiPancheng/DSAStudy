package datastructure.stackqueue;

import java.util.LinkedList;

/**
 * 给定数组arr和整数num，求满足max(arr[i...j]) - min(arr[i...j]) <= num 的子数组个数, i < j
 *
 * 要求：时间复杂度为O(n)
 */
public class ArraysSatisfyRange {
    public static int getArrayNum(int[] arr, int num){
        if (arr ==null || arr.length == 0){
            return 0;
        }

        int count = 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int i = 0;
        int j = 0;
        for (; i < arr.length; i++) {
            for (; j < arr.length; j++) {
                while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[j]){
                    qmax.removeLast();
                }
                qmax.addLast(j);

                while (!qmin.isEmpty() &&  arr[j] <= arr[qmin.getLast()]){
                    qmin.removeLast();
                }
                qmin.addLast(j);

                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num){
                    break;
                }
            }
            /*
            arr[i...i]即arr[i]一个也算一个子数组
            不用考虑i右移会超过j，在前面if判断时，到i==j后就不会break，然后j会自增
            */
            count += j - i;

            /*由于接下来i要向右移一位，所以得更新qmax和qmin结构*/
            if (qmax.getFirst() == i){
                qmax.removeFirst();
            }
            if (qmin.getFirst() == i){
                qmin.removeFirst();
            }
        }

        return count;
    }
}
