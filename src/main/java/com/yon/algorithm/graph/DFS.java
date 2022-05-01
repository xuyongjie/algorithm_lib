package com.yon.algorithm.graph;

import java.util.List;

public class DFS {

    int[] dTime;//存储节点发现时间戳
    int[] fTime;//完成时间戳，按该值倒序排得到有向无环图拓扑排序
    int time;//时间
    int[] color;
    int[] parent;

    /**
     * 深度优先搜索
     *
     * @param adj 邻接表，数组下标代表节点id,list的每个元素代表下标节点到该节点有边
     */
    public void dfs(List<Integer>[] adj) {
        int vCount = adj.length;
        dTime = new int[vCount];//存储节点发现时间戳
        fTime = new int[vCount];//完成时间戳
        time = 0;//时间
        color = new int[vCount];
        parent = new int[vCount];

        for (int i = 0; i < vCount; i++) {
            color[i] = 0;
            parent[i] = -1;
        }

        for (int i = 0; i < vCount; i++) {
            if (color[i] == 0) {
                doDfs(adj, i);
            }
        }
    }

    private void doDfs(List<Integer>[] adj, int u) {
        color[u] = 1;
        //visit u
        time = time + 1;
        dTime[u] = time;
        for (int v : adj[u]) {
            if (color[v] == 0) {
                parent[v] = u;
                doDfs(adj, v);
            }
        }
        color[u] = 2;
        time = time + 1;
        fTime[u] = time;
    }
}
