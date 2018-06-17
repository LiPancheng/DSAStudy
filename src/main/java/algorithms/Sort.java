package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用排序算法的实现
 *
 * 对于标准库里的Arrays.sort()：
 * JDK6里对原始类型用的是快速排序，对于对象类型(Object[])，JDK6则使用归并排序。
 * 到了JDK7,快速排序升级为双轴快排(双基准快排)；归并排序升级为其改进版TimSort。
 * 到了JDK8，对大集合增加了Arrays.parallelSort()函数，使用fork-join框架，充分利用多核，
 * 对大的集合进行切分然后再归并排序，而在小的连续片段里，依然使用TimSort与DualPivotQuickSort。
 *
 * JDK8中对不同类型、不同规模的数组使用了不同的排序：
 * 对于int long float double等原始类型，长度小于47使用插入排序，小于286使用快排，否则使用归并，大于8192则使用parallelSort
 * 对于byte数组，小于29使用插入排序，否则使用计数排序
 * 对于short和char数组，小于3200使用双轴快排，否则使用计数排序
 * 对于Object对象类型，小于8192使用TimSort，否则使用parallelSort
 */
public class Sort {

//  ---------------------------------------- 快速排序 -------------------------------------------
    /**
     *1.从数列中挑出一个元素，称为"基准"（pivot），
     *2.重新排序数列，所有比基准值小的元素放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任何一边）
     *3.递归地把两边的子数列排序。
     *
     * 时间复杂度：最优 O(nlogn)，平均 O(nlogn)，最差 O(n2)即每次基准取到最大或最小
     * 稳定性：不稳定
     * 分类：比较排序
     */
    public void quickSort(int[] arr, int start, int end){
        if (arr == null || start >= end){
            return;
        }
        int i = start, j = end, pivot = arr[(start + end) / 2];
        while (i < j){
            while (arr[j] > pivot){ //不用写为 while (i < j && arr[j] > pivot)，因为在基准值处一定会停止
                j--;
            }
            while (arr[i] < pivot){
                i++;
            }
            if (i < j){
                swap(arr, i++, j--);
            }
            else if (i == j){
                i++;
            }
        }
        quickSort(arr, start, j);
        quickSort(arr, i, end);
    }


    //  ---------------------------------------- 归并排序 ------------------------------------------
    /**
     * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法的一个非常典型的应用。
     *
     * 时间复杂度： 最优 O(nlogn)，平均 O(nlogn)，最差 O(nlogn)
     * 稳定性: 稳定
     * 分类：比较排序
     */
    public void mergeSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid + 1, end);
        mergeArray(arr, start, mid, end);
    }

    private void mergeArray(int[] arr, int start, int middle, int end){
        int len = end - start + 1;
        int[] tmp = new int[len];
        int p = start, q = middle + 1, t = 0;
        while (p <= middle && q <= end){
            if (arr[p] <= arr[q]){
                tmp[t++] = arr[p++];
            }
            else {
                tmp[t++] = arr[q++];
            }
        }
        while (p <= middle){
            tmp[t++] = arr[p++];
        }
        while (q <= end){
            tmp[t++] = arr[q++];
        }
        for (t = 0; t < len; start++, t++)
        {
            arr[start] = tmp[t];
        }
    }


//  ---------------------------------------- 堆排序 ------------------------------------------
// 一个完全二叉树中，每个结点总是大于或等于（小于或等于）其左右孩子结点，则为大顶堆（小顶堆）
    /**
     * 1.将初始待排序关键字序列(R0,R1,R2....Rn)构建成大顶堆，此堆为初始的无序区；
     * 2.将堆顶元素R[0]与最后一个元素R[n]交换，此时得到新的无序区(R0,R1,R2,......Rn-1)和新的有序区(Rn)；
     * 3.由于交换后新的堆顶R[0]可能违反堆的性质，因此需要对当前无序区(R0,R1,R2,......Rn-1)调整为新堆。
     * 不断重复此2、3步骤直到有序区的元素个数为n-1，则整个排序过程完成。
     *
     * 其中堆构造流程：
     * 从最后一个非叶子节点i（i=n/2-1）开始到根结点，将以i为根节点的二叉树通过筛选调整为堆
     *
     * 时间复杂度： 最优 O(nlogn)，平均 O(nlogn)，最差 O(nlogn)
     * 稳定性: 不稳定
     * 分类：比较排序
     */
    public void heapSort(int[] arr){
        //构造初始堆
        for (int i = arr.length / 2 - 1; i > 0; i--) {
            adjustHeap(arr, i,arr.length -1);
        }
        for (int i = arr.length-1; i > 0 ; i--) {
            //交换头尾元素
            swap(arr, 0, i);
            //重建（调整）堆
            adjustHeap(arr, i/2 -1, i-1);
        }

    }

    private void adjustHeap(int[] arr, int current, int end){
        for (int i = current; i >= 0; i--) {
            int toCmp = 2*i+1;
            if (toCmp + 1 <= end && arr[toCmp] < arr[toCmp+1]){
                toCmp++;
            }
            if (arr[i] < arr[toCmp]){
                swap(arr, i, toCmp);
            }
        }
    }


    //  ---------------------------------------- 冒泡排序 ------------------------------------------
    /**
     * 时间复杂度： 使用flag情况下最优 O(n)， 平均 O(n2)， 最差 O(n2)
     * 稳定性: 稳定
     * 分类：比较排序
     */
    public void bubbleSort(int[] arr){
        boolean swapFlag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    swap(arr, j, j + 1);
                    swapFlag = true;
                }
            }
            if (!swapFlag) break;
        }
    }


    //  ---------------------------------------- 选择排序 ------------------------------------------
    /**
     * 在长度为N的无序数组中，第一次遍历n-1个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-2个数，找到最小的数值与第二个元素交换；以此类推，排序完成。
     *
     * 时间复杂度： 最优 O(n2)， 平均 O(n2)，最差 O(n2)
     * 稳定性: 不稳定
     * 分类：比较排序
     */
    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if (i != minIndex){
                swap(arr, i, minIndex);
            }
        }
    }


    //  ---------------------------------------- 插入排序 ------------------------------------------
    /**
     * 类似抓扑克牌，对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入
     *
     * 时间复杂度： 最优 O(n)即序列已经排好序， 平均 O(n2)，最差 O(n2)
     * 稳定性: 稳定
     * 分类：比较排序
     */
    public void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) { //按思想i应从0开始，但i=0时相当于什么也没做，所以直接从1开始
            int get = arr[i]; //抓起一张牌
            int j = i - 1;
            for (; j >= 0 && arr[j] > get; j--) { //与左手已经排好序的从右向左依次比较
                //比抓起的牌大则右移
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = get; //直到该手牌比抓到的牌小(或二者相等)，将抓到的牌插入到该手牌右边(相等元素的相对次序未变，所以插入排序是稳定的
        }
    }


    //  ---------------------------------------- 二分插入排序 ------------------------------------------
    /**
     * 改进的插入排序，即在对左边已排序序列扫描时使用二分查找，以减少比较操作的次数
     *
     * 时间复杂度： 最优 O(nlogn)， 平均 O(n2)，最差 O(n2)
     * 稳定性: 稳定
     * 分类：比较排序
     */
    public void dichotomyInsertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int get = arr[i]; //抓起一张牌
            int left = 0, right = i - 1;
            while (left <= right){
                int mid = (left + right) / 2;
                if (arr[mid] < get){
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = get;
        }
    }


    //  ---------------------------------------- 希尔排序 ------------------------------------------
    /**
     * 也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
     * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
     * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
     *
     * 时间复杂度： 最优 O(n)， 平均 根据步长序列的不同而不同，最差 O(n2)
     * 稳定性: 不稳定
     * 分类：比较排序
     */
    public void shellSort(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int get = arr[i];
                int j = i - gap;
                for (; j >= 0 && arr[j] > get; j -= gap) {
                    arr[j + gap] = arr[j];
                }
                arr[j + gap] = get;
            }
        }
    }


    /**
     * 异或方式交换两个数的值
     *
     * 注：不使用中间变量的方法还有做加减、乘除，但有溢出的隐患
     * a=a+b;   //a=a*b;
     * b=a-b;   //b=a/b;
     * a=a-b;   //a=a/b;
     */
    private void swap(int[] arr, int index1, int index2){
        arr[index1] ^= arr[index2];
        arr[index2] = arr[index1] ^ arr[index2];
        arr[index1] ^= arr[index2];
    }

    //######################################### 线性时间排序 ########################################

    //  ---------------------------------------- 计数排序 ------------------------------------------
    /**
     * 1.找出待排序的数组中最大和最小的元素；
     * 2.统计数组中每个值为 i 的元素出现的次数，存入数组 C 的第 i 项；
     * 3.对所有的计数累加（从 C 中的第一个元素开始，每一项和前一项相加）；
     * 4.反向填充目标数组，将每个元素 i 放在新数组的第 C(i) 项，每放一个元素就将 C(i) 减去 1；
     *
     * 时间复杂度： 最优 O(n + k)， 平均 O(n + k)，最差 O(n + k)
     * 稳定性: 稳定
     * 分类：非比较排序
     */
    public void countingSort(int[] arr){
        //找出待排序的数组中最大和最小的元素
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            min = min < v ? min : v;
            max = max > v ? max : v;
        }
        //统计数组中每个值为 i 的元素出现的次数
        int k = max - min + 1;
        int[] count = new int[k];
        for (int v : arr) {
            count[v - min]++;
        }
        //对所有的计数累加
        for (int i = 0; i < k - 1; i++) {
            count[i+1] += count[i];
        }
        //反向填充目标数组。反向填充的目的是为了保证稳定性。
        //比如数值99在累加后的count数组中值为20，反向遍历arr时，第一个遇到的99该放到20-1=19的索引位置
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, res.length);
        for (int i = arr.length - 1; i >=0 ; i--) {
            int v = res[i];
            arr[count[v - min] - 1] = v;
            count[v - min]--;
        }
    }


    //  ---------------------------------------- 桶排序 ------------------------------------------
    /**
     * 1.找出待排序的数组中最大和最小的元素；
     * 2.设置有限数量的桶，遍历数组，将元素放入对应的桶中（对应方式可以是hash函数或其他简单映射）；
     * 3.对每个非空的桶里面的元素进行排序（这里可使用其他排序，比如稳定的插入排序）；
     * 4.按桶依次回填目标数组；
     *
     * 时间复杂度： 最优 O(n)即每个元素一个桶， 平均 O(n + c) c为桶内排序时间，最差 O(n2)
     * 稳定性: 稳定
     * 分类：非比较排序
     */
    public void bucketSort(int[] arr){
        //找出待排序的数组中最大和最小的元素
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            min = min < v ? min : v;
            max = max > v ? max : v;
        }
        //桶的数量。该值也可根据实际情况选择
        int bucketNum = max/10 - min/10 + 1;
        List<Integer>[] buckets = new ArrayList[bucketNum];
        for (int v : arr) {
            int index = (v - min) / 10; //10为步长(桶的宽度/区间),由于计算桶数量时除的10。具体长度可根据情况设定
            index = index == bucketNum ? bucketNum - 1 : index;
            if (buckets[index] == null){
                buckets[index] = new ArrayList<>();
            }
            buckets[index].add(v);
        }
        int i = 0;
        for (List<Integer> bucket : buckets) {
            if (bucket != null){
                insertSort(bucket);
                for (int k : bucket) {
                    arr[i++] = k;
                }
            }
        }
    }
    private void insertSort(List<Integer> bucket){
        for (int i=1;i<bucket.size();i++){
            int temp=bucket.get(i);
            int j=i-1;
            for (; j>=0 && bucket.get(j)>temp;j--){
                bucket.set(j+1,bucket.get(j));
            }
            bucket.set(j+1,temp);
        }
    }
}
