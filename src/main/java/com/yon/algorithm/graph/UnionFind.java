package com.yon.algorithm.graph;

/**
 * 并查集
 */
public class UnionFind {

    private int[] group;//存储集团代表

    public UnionFind(int vCount) {
        group = new int[vCount];
        for (int i = 0; i < vCount; i++) {
            group[i] = i;
        }
    }

    public int find(int i) {
        if (group[i] == i) {
            return i;
        }
        group[i] = find(group[i]);//路径压缩
        return group[i];
    }

    public boolean union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);
        if (aGroup == bGroup) {
            return false;
        }
        group[aGroup] = bGroup;
        return true;
    }
}
