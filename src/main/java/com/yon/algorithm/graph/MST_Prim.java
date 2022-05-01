package com.yon.algorithm.graph;

import com.yon.algorithm.array.PriorityQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 最小生成树（minimum spanning tree），prim算法
 */
public class MST_Prim {

    public static void main(String[] args) {
        List<int[]>[] graph = new ArrayList[5];
        ArrayList<int[]> v0Adj = new ArrayList<>();
        v0Adj.add(new int[]{1, 4});
        v0Adj.add(new int[]{2, 3});
        graph[0] = v0Adj;
        ArrayList<int[]> v1Adj = new ArrayList<>();
        v1Adj.add(new int[]{0, 4});
        v1Adj.add(new int[]{2, 8});
        v1Adj.add(new int[]{3, 7});
        graph[1] = v1Adj;
        ArrayList<int[]> v2Adj = new ArrayList<>();
        v2Adj.add(new int[]{0, 3});
        v2Adj.add(new int[]{1, 8});
        v2Adj.add(new int[]{4, 1});
        graph[2] = v2Adj;
        ArrayList<int[]> v3Adj = new ArrayList<>();
        v3Adj.add(new int[]{1, 7});
        v3Adj.add(new int[]{4, 9});
        graph[3] = v3Adj;
        ArrayList<int[]> v4Adj = new ArrayList<>();
        v4Adj.add(new int[]{2, 1});
        v4Adj.add(new int[]{3, 9});
        graph[4] = v4Adj;
        MST_Prim mst_prim = new MST_Prim();
        System.out.println(Arrays.toString(mst_prim.MST(graph)));
    }

    /**
     * 算法思路：
     * 最小生成树集合A，初始为空
     * 待加入节点集Q，初始为所有节点
     * 选择一个初始节点r加入A Q=Q-r
     * 从Q中选择A集合可达的边权重最小的节点加入A（可使用最小优先级队列来优化）
     * 直到Q集合为空
     *
     * @param adj 邻接表表示法，数组下标为节点id，list中的每一项存的是相邻节点和对应边权重 int[]{点，边权重}
     * @return 最小生成树parent数组表示法，索引表示节点，值表示父节点
     */
    public int[] MST(List<int[]>[] adj) {
        int vCount = adj.length;
        int[] key = new int[vCount];//存储i节点到集合A的最小权重
        int[] parent = new int[vCount];//最小生成树parent数组表示法(前驱子图)
        for (int i = 0; i < vCount; i++) {
            parent[i] = -1;
            key[i] = Integer.MAX_VALUE;
        }
        int root = 0;//0节点作为根节点
        parent[root] = -1;
        key[root] = 0;
        PriorityQ pq = new PriorityQ(vCount);
        for (int i = 0; i < vCount; i++) {
            pq.add(i, key[i]);
        }
        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (int[] v : adj[u]) {
                if (pq.contains(v[0]) && v[1] < key[v[0]]) {
                    parent[v[0]] = u;
                    key[v[0]] = v[1];
                    pq.decrease(v[0], v[1]);
                }
            }
        }
        return parent;
    }
}
