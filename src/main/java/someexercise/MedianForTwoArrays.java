package someexercise;


/**
 * 给定两个有序数组，找到这两个有序数组的中位数
 */
public class MedianForTwoArrays {

    /**
     * 两个长度均为 N 的有序数组，找到这两个有序数组的"上中位数"
     *
     * 要求：时间复杂度为 O(logN)
     *
     * 示例 1:
     * arr1 = [1, 3]
     * arr2 = [2]
     * 上中位数是 2
     *
     * 示例 2:
     * arr1 = [1, 2]
     * arr2 = [3, 4]
     * 上中位数是 2
     */
    public int findUpMedian(int[] arr1, int[] arr2, int n){
        int start1 = 0, end1 = n - 1,
            start2 = 0, end2 = n - 1;

        while (start1 < end1){
            int mid1 = (start1 + end1) / 2;
            int mid2 = (start2 + end2) / 2;

            int evenOffset = ((end1 - start1 + 1) & 1) ^ 1;  //元素个数为偶数，则较小的肯定是前N/2个，下个片段时应被跳过
            if (arr1[mid1] == arr2[mid2]){
                return arr1[mid1];
            }
            else if (arr1[mid1] > arr2[mid2]){
                //此时arr1[mid1]已经至少是整体的第N+1个数，不可能是上中位数，但arr2[mid2]有可能是，所以mid2应包含在下一次计算数组片段中，
                //而又必须保证寻找过程两段数组长度一致，所以end1 = mid1;将arr1[mid1]仍包含到下一次计算数组片段中
                end1 = mid1;
                start2 = mid2 + evenOffset;
            }
            else {
                start1 = mid1 + evenOffset;
                end2 = mid2;
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }

//------------------------------ 来自leetcode第4题 -------------------------------
    /**
     * 两个长度分别为 m 和 n的有序数组，找到这两个有序数组的中位数
     *
     * 要求：时间复杂度为 O(log (m+n))
     *
     * 示例 1:
     * arr1 = [1, 3]
     * arr2 = [2]
     * 中位数是 2.0
     *
     * 示例 2:
     * arr1 = [1, 2]
     * arr2 = [3, 4]
     * 中位数是 (2 + 3)/2 = 2.5
     *
     * 解法1：找到两个数组的分割，满足len(left_part)==len(right_part)，并且max(left_part)<=min(right_part)
     * https://www.cnblogs.com/summerkiki/p/5781757.html
     *
     * 解法2：同样采用分治思想找到两个数组的分割
     * 与前一方法不同的是为了统一奇数总长度和偶数总长度的处理情况，虚拟加入‘#’让数组长度恒为奇数
     * https://blog.csdn.net/hk2291976/article/details/51107778
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2){
        if (nums1.length > nums2.length){
            return findMedianSortedArrays2(nums2, nums1); //在长度最小的数组上找分割，因此始终保证num1长度最小
        }
        int arr1_leftPartMax = Integer.MIN_VALUE, arr2_leftPartMax = Integer.MIN_VALUE;
        int arr1_rightPartMin = Integer.MAX_VALUE, arr2_rightPartMin = Integer.MAX_VALUE;
        int seg1, seg2; //两个数组各自的分割位置
        int low = 0, high = 2 * nums1.length; //用于寻找分割

        while (low <= high){
            seg1 = (low + high) / 2;
            seg2 = nums1.length + nums2.length - seg1; //2 * (nums1.length + nums2.length) / 2 - seg1

            arr1_leftPartMax = seg1 == 0 ? Integer.MIN_VALUE : nums1[(seg1 - 1) / 2];
            arr2_leftPartMax = seg2 == 0 ? Integer.MIN_VALUE : nums2[(seg2 - 1) / 2];
            arr1_rightPartMin = (seg1 == nums1.length * 2) ? Integer.MAX_VALUE : nums1[seg1 / 2];
            arr2_rightPartMin = (seg2 == nums2.length * 2) ? Integer.MAX_VALUE : nums2[seg2 / 2];

            if (arr1_leftPartMax > arr2_rightPartMin){ //说明seg1在num1中分得偏右了，应向左减小，采用二分减小
                high = seg1 - 1;
            }
            else if (arr2_leftPartMax > arr1_rightPartMin){
                low = seg1 + 1;
            }
            else { //满足len(left_part)==len(right_part)，并且max(left_part)<=min(right_part)
                break;
            }
        }
        return (Math.max(arr1_leftPartMax, arr2_leftPartMax) + Math.min(arr1_rightPartMin, arr2_rightPartMin)) / 2.0;
    }

    /**
     * 由于两个都是有序数组，那么不考虑O(log (m+n))的时间复杂要求，利用O(m+n)的空间，合并为一个数组直接确定中位数
     *
     * 时间复杂度O(m+n)，但往往执行比上述方法快
     */
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        double result = 0.0;
        int arrlen = nums1.length+nums2.length;
        int[] arr = new int[arrlen];
        int p = 0;
        int p1 = 0;
        int p2 = 0;
        while(p < arrlen){
            if(p1 >= nums1.length){
                arr[p++] = nums2[p2++];
            }else if(p2 >= nums2.length){
                arr[p++] = nums1[p1++];
            }else if(nums1[p1] < nums2[p2]){
                arr[p++] = nums1[p1++];
            }else{
                arr[p++] = nums2[p2++];
            }
        }
        if((arrlen & 1) == 0){
            result  = (arr[arrlen/2] + arr[arrlen/2-1]) / 2.0;
        }else{
            result = arr[arrlen / 2];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2,3};
        System.out.println(new MedianForTwoArrays().findMedianSortedArrays2(nums1, nums2));
    }
}
