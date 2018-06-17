package datastructure;


import java.util.Arrays;
import java.util.HashMap;

public class Temple {

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer j = map.get(complement);
            if ((j != null) && (i != j)){
                res[0] = i;
                res[1] = j;
                break;
            }
        }
        return res;
    }

    public static int[] twoSum1(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer j = map.get(complement);
            if ((j != null) && (i != j)){
                res[0] = j;
                res[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return res;
    }

    public static void main(String[] args){
        //int[] arr = new int[]{3,3};
        int[] arr = new int[]{2, 7, 11, 15};
        int[] res = twoSum1(arr, 9);
        for (int a : res) {
            System.out.print(a + " ");
        }

    }

}
