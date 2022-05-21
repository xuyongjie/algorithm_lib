package com.yon.algorithm.array;

import java.util.Arrays;

/**
 * 归并排序，同时可以统计逆序对数，即统计第i个元素的右边小于该元素的个数
 */
public class MergeSort {

    public static void main(String[] args) {
        printArray(new MergeSort().sort(new int[]{1, 4, 5, 62, 3, 4, 6, 7, 34, 8}));
    }

    public static void printArray(int[] arr) {
        for (int each : arr) {
            System.out.println(each);
        }
    }


    public int[] sort(int[] data) {
        if (data.length < 2) {
            return data;
        }
        int mid = data.length / 2;
        int[] left = sort(Arrays.copyOfRange(data, 0, mid));
        int[] right = sort(Arrays.copyOfRange(data, mid, data.length));
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int[] tmp = new int[left.length + right.length];
        int i = 0, j = 0;
        int t = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                tmp[t++] = left[i++];
            } else {
                tmp[t++] = right[j++];
            }
        }
        while (i < left.length) {
            tmp[t++] = left[i++];
        }
        while (j < right.length) {
            tmp[t++] = right[j++];
        }
        return tmp;
    }


    public void sort2(int[] data, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        sort2(data, l, mid);
        sort2(data, mid + 1, r);
        merge2(data, l, mid, r);
    }

    private void merge2(int[] data, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l, j = mid + 1;
        int t = 0;
        while (i <= mid && j <= r) {
            if (data[i] <= data[j]) {
                tmp[t++] = data[i++];
            } else {
                tmp[t++] = data[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = data[i++];
        }
        while (j <= r) {
            tmp[t++] = data[j++];
        }
        for (i = l, j = 0; i <= r; i++, j++) {
            data[i] = tmp[j];
        }
    }
}
