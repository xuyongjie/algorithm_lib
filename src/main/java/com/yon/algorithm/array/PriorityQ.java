package com.yon.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 简易版优先级队列
 * 基于小顶堆实现
 * 支持入队、出队、减小队列元素权重
 */
public class PriorityQ {
    private Map<Integer, Integer> idWeight;//存储节点id及权重

    private int[] heap;//heap[i]存储的是节点id

    private int size;

    public PriorityQ(int capacity) {
        heap = new int[capacity];
        idWeight = new HashMap<>(capacity);
        size = 0;
    }

    /**
     * 入队
     *
     * @param id
     * @param weight
     */
    public void add(int id, int weight) {
        if (size == heap.length) {
            throw new RuntimeException("queue is full");
        }
        idWeight.put(id, weight);
        size++;
        heap[size - 1] = id;
        upAdjust(size);
    }

    /**
     * 插入时上滤
     *
     * @param position
     */
    private void upAdjust(int position) {
        while (position > 1) {
            int parent = position / 2;
            if (idWeight.get(heap[position - 1]) < idWeight.get(heap[parent - 1])) {
                int tmp = heap[position - 1];
                heap[position - 1] = heap[parent - 1];
                heap[parent - 1] = tmp;
                position = parent;
            } else {
                break;
            }
        }
    }

    /**
     * 移除堆顶时将堆尾元素放在堆顶执行下滤
     *
     * @param position
     */
    private void downAdjust(int position) {
        while (position <= size) {
            int l = position * 2;
            int r = position * 2 + 1;
            int min = position;
            if (l <= size && idWeight.get(heap[l - 1]) < idWeight.get(heap[min - 1])) {
                min = l;
            }
            if (r <= size && idWeight.get(heap[r - 1]) < idWeight.get(heap[min - 1])) {
                min = r;
            }
            if (min != position) {
                int tmp = heap[position - 1];
                heap[position - 1] = heap[min - 1];
                heap[min - 1] = tmp;
                position = min;
            } else {
                break;
            }
        }
    }

    /**
     * 选择队列顶部元素
     *
     * @return
     */
    public int peek() {
        return heap[0];
    }

    /**
     * 出队，返回id
     *
     * @return
     */
    public int poll() {
        if (isEmpty()) {
            throw new RuntimeException("queue is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = min;
        size--;
        idWeight.remove(min);
        downAdjust(1);
        return min;
    }

    /**
     * 减小id节点的权重到to
     *
     * @param id
     * @param to
     */
    public void decrease(int id, int to) {
        if (!idWeight.containsKey(id)) {
            throw new RuntimeException("already extract from queue");
        }
        int origin = idWeight.get(id);
        if (to > origin) {
            throw new RuntimeException("must little then origin");
        }
        idWeight.put(id, to);
        int position = -1;
        for (int j = 0; j < size; j++) {
            if (heap[j] == id) {
                position = j + 1;
                break;
            }
        }
        //上滤
        upAdjust(position);
    }

    public boolean isEmpty() {
        return size < 1;
    }

    public boolean contains(int id) {
        return idWeight.containsKey(id);
    }
}
