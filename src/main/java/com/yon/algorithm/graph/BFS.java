package com.yon.algorithm.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    /**
     * 广度优先搜索
     *
     * @param adj   邻接表，数组下标代表节点id,list的每个元素代表下标节点到该节点有边
     * @param start 起始节点
     */
    public void bfs(List<Integer>[] adj, int start) {
        int vCount = adj.length;
        int[] color = new int[vCount];//存储搜索过程的着色情况，初始为0-白色，初次发现置为1-白色，可达节点全部发现后置位2-黑色
        int[] parent = new int[vCount];//存储发现该节点的前置节点
        int[] distance = new int[vCount];//存储源点到该节点的距离
        //init
        for (int i = 0; i < vCount; i++) {
            color[i] = 0;
            parent[i] = -1;
            distance[i] = Integer.MAX_VALUE;
        }
        Queue<Integer> queue = new LinkedList<>();//辅助队列
        color[start] = 1;
        parent[start] = -1;
        distance[start] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            //visit u
            for (int v : adj[u]) {
                if (color[v] == 0) {
                    color[v] = 1;
                    parent[v] = u;
                    distance[v] = distance[u] + 1;
                    queue.add(v);
                }
            }
            color[u] = 2;
        }
    }
}
