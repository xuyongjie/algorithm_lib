package com.yon.algorithm.array;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 线段树，处理区间问题
 */
public class SegmentTree {

    public static void main(String[] args) {
        SegmentTree segmentTree = new SegmentTree(new int[]{3, 6, 2, 8, 11, 23, 5, 7, 2});
        segmentTree.print();
        System.out.println(segmentTree.query(1, 6));
        System.out.println(segmentTree.query(0, 8));
        System.out.println(segmentTree.query(1, 10));
        segmentTree.update(1, 3, 2);
        segmentTree.print();
    }

    public static class Node {
        int l;
        int r;
        int data;

        @Override
        public String toString() {
            return "[" + l + "," + r + "]" + data;
        }
    }

    //线段树节点
    Node[] nodes;
    //节点懒标记,mark[i]表示对位置i的节点的修改值
    int[] mark;
    //原始区间数据
    int[] data;

    public SegmentTree(int[] data) {
        this.data = data;
        nodes = new Node[data.length * 4];
        mark = new int[data.length * 4];
        build(0, data.length - 1, 1);
    }

    private void build(int l, int r, int p) {
        Node node = new Node();
        node.l = l;
        node.r = r;
        if (l == r) {
            node.data = data[l];
            nodes[p - 1] = node;
            return;
        }
        int mid = (l + r) / 2;
        build(l, mid, p << 1);
        build(mid + 1, r, (p << 1) + 1);
        node.data = nodes[(p << 1) - 1].data + nodes[p << 1].data;
        nodes[p - 1] = node;
    }


    public int query(int l, int r) {
        return doQuery(l, r, 1);
    }

    private int doQuery(int l, int r, int p) {
        Node cur = nodes[p - 1];
        if (l > cur.r || r < cur.l) {
            //不相交
            return 0;
        } else if (l <= cur.l && r >= cur.r) {
            //查询区间覆盖当前区间
            return cur.data;
        } else {
            //因为要向下查询，所以对懒标记向下传递
            pushDown(p, cur.r - cur.l + 1);
            return doQuery(l, r, p << 1) + doQuery(l, r, (p << 1) + 1);
        }
    }

    /**
     * 更新区间[l,r]，每个加val
     *
     * @param l
     * @param r
     * @param val
     */
    public void update(int l, int r, int val) {
        doUpdate(l, r, val, 1);
    }

    private void doUpdate(int l, int r, int val, int p) {
        Node cur = nodes[p - 1];
        if (l > cur.r || r < cur.l) {
            //不相交
            return;
        } else if (l <= cur.l && r >= cur.r) {
            //更新区间覆盖当前区间
            cur.data += (cur.r - cur.l + 1) * val;
            if (cur.r > cur.l) {
                //非叶子节点，懒标记，留着向下传递
                mark[p - 1] += val;
            }
        } else {
            //部分覆盖
            //因为要向下调用，所以需要将当前节点标记向下传递
            pushDown(p, cur.r - cur.l + 1);
            doUpdate(l, r, val, p << 1);
            doUpdate(l, r, val, (p << 1) + 1);
            cur.data = nodes[(p << 1) - 1].data + nodes[p << 1].data;
        }
    }

    /**
     * 将位置p节点懒标记向下传递
     *
     * @param p      节点位置
     * @param length 节点区间长度
     */
    private void pushDown(int p, int length) {
        mark[(p << 1) - 1] += mark[p - 1];
        nodes[(p << 1) - 1].data += ((length + 1) >> 1) * mark[p - 1];
        mark[p << 1] += mark[p - 1];
        nodes[p << 1].data += ((length - 1) >> 1) * mark[p - 1];
        //清空当前节点标记
        mark[p - 1] = 0;
    }

    public void print() {
        System.out.println(Arrays.stream(nodes).filter(Objects::nonNull).map(Node::toString).collect(Collectors.joining(",")));
    }
}
