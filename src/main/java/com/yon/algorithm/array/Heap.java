package com.yon.algorithm.array;

import java.util.Arrays;

/**
 * 堆
 */
public class Heap {

    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{3, 45, 6, 56, 23, 12, 35, 6, 89, 200, 21, 15});
        System.out.println("source:" + heap.toString());
        heap.buildMaxHeap();
        System.out.println("after build:" + heap.toString());
        heap.sort();
        System.out.println("after heap sort:" + heap.toString());
    }

    private int[] data;

    public Heap(int[] data) {
        this.data = data;
    }

    /**
     * 构造大顶堆
     */
    public void buildMaxHeap() {
        int size = data.length;
        for (int i = size / 2; i >= 1; i--) {
            maxHeapify(i, size);
        }
    }

    public void sort() {
        int heapSize = data.length;
        for (int i = heapSize; i >= 1; i--) {
            int max = data[0];
            data[0] = data[i - 1];
            data[i - 1] = max;
            maxHeapify(1, i - 1);
        }
    }

    /**
     * 对第i个节点执行下虑操作
     *
     * @param i
     * @param heapSize
     */
    private void maxHeapify(int i, int heapSize) {
        int l = 2 * i;
        int r = 2 * i + 1;
        int largestPosition = i;
        if (l <= heapSize && data[i - 1] < data[l - 1]) {
            largestPosition = l;
        }
        if (r <= heapSize && data[largestPosition - 1] < data[r - 1]) {
            largestPosition = r;
        }
        if (largestPosition != i) {
            int tmp = data[i - 1];
            data[i - 1] = data[largestPosition - 1];
            data[largestPosition - 1] = tmp;
            maxHeapify(largestPosition, heapSize);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
