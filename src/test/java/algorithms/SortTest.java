package algorithms;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.junit.Assert.*;

public class SortTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void quickSort() throws Exception {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        Sort sort = new Sort();
        sort.quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.print(i+ ", ");
        }
    }

    @Test
    public void mergeSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.mergeSort(arr, 0, arr.length - 1);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    @Test
    public void heapSort() throws Exception {
        /*int[] arr = new int[]{1,0,10,20,3,5,6,4,9,8,12,17,34,11};
        Sort sort = new Sort();
        sort.heapSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);*/

        int[] arr = new int[]{16, 7, 3, 20, 17, 8};
        Sort sort = new Sort();
        sort.heapSort(arr);
        Assert.assertArrayEquals(new int[]{3,7,8,16,17,20}, arr);
    }

    @Test
    public void bubbleSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.bubbleSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    @Test
    public void selectSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.selectSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    @Test
    public void insertSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.insertSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    @Test
    public void dichotomyInsertSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.dichotomyInsertSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    @Test
    public void shellSort() throws Exception {
        /*int[] arr = getArray();
        Sort sort = new Sort();
        sort.shellSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);*/

        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        Sort sort = new Sort();
        sort.shellSort(arr);
        for (int i : arr) {
            System.out.print(i+ ", ");
        }
    }

    @Test
    public void countingSort() throws Exception {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        Sort sort = new Sort();
        sort.countingSort(arr);
        for (int i : arr) {
            System.out.print(i+ ", ");
        }
    }

    @Test
    public void bucketSort() throws Exception {
        int[] arr = getArray();
        Sort sort = new Sort();
        sort.bucketSort(arr);
        Assert.assertArrayEquals(new int[]{0,1,3,4,5,6,8,9,10,11,12,17,20,34}, arr);
    }

    private int[] getArray(){
        return new int[]{1,0,10,20,3,5,6,4,9,8,12,17,34,11};
    }
}