package com.yon.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 针对有向无环图的单源最短路径
 * 只需要按拓扑排序的顺序进行出边松弛即可
 */
public class SSSP_DAG {

    public static void main(String[] args) {
        List<int[]>[] graph = new ArrayList[6];
        ArrayList<int[]> v0Adj = new ArrayList<>();
        v0Adj.add(new int[]{1, 5});
        v0Adj.add(new int[]{2, 3});
        graph[0] = v0Adj;
        ArrayList<int[]> v1Adj = new ArrayList<>();
        v1Adj.add(new int[]{2, 2});
        v1Adj.add(new int[]{3, 6});
        graph[1] = v1Adj;
        ArrayList<int[]> v2Adj = new ArrayList<>();
        v2Adj.add(new int[]{3, 7});
        v2Adj.add(new int[]{4, 4});
        v2Adj.add(new int[]{5, 2});
        graph[2] = v2Adj;
        ArrayList<int[]> v3Adj = new ArrayList<>();
        v3Adj.add(new int[]{4, -1});
        v3Adj.add(new int[]{5, 1});
        graph[3] = v3Adj;
        ArrayList<int[]> v4Adj = new ArrayList<>();
        v4Adj.add(new int[]{5, -2});
        graph[4] = v4Adj;
        graph[5] = new ArrayList<>();
        SSSP_DAG sssp_dag = new SSSP_DAG();
        System.out.println(Arrays.toString(sssp_dag.SSSP(graph, 1)));
    }

    /**
     * 算法思想：
     * 带权有向图G(V,E)，无环
     * 先进行拓扑排序
     * 按拓扑排序节点顺序对出边进行松弛
     *
     * @param adj 邻接表表示法，点i->list[int[]{点，边权值}]
     * @param s   源点
     * @return 最短路径结果，i节点到s节点的最短路径
     */
    public int[] SSSP(List<int[]>[] adj, int s) {
        int vCount = adj.length;
        List<Integer> topoSorted = getTopoSorted(adj);
        int[] d = new int[vCount];
        int[] parent = new int[vCount];//存储节点前驱
        for (int i = 0; i < vCount; i++) {
            d[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        d[s] = 0;
        for (int u : topoSorted) {
            for (int[] v : adj[u]) {
                //无穷大加上一个数还是无穷大
                int duPlusV = d[u] == Integer.MAX_VALUE ? Integer.MAX_VALUE : d[u] + v[1];
                if (duPlusV < d[v[0]]) {
                    d[v[0]] = duPlusV;
                    parent[v[0]] = u;
                }
            }
        }
        return d;
    }

    /**
     * 获取有向无环图的拓扑排序
     *
     * @param adj
     * @return
     */
    private List<Integer> getTopoSorted(List<int[]>[] adj) {
        int vCount = adj.length;
        LinkedList<Integer> topoSorted = new LinkedList<>();
        boolean[] visited = new boolean[vCount];
        for (int i = 0; i < vCount; i++) {
            if (!visited[i]) {
                dfs(adj, i, visited, topoSorted);
            }
        }
        return topoSorted;
    }

    private void dfs(List<int[]>[] adj, int u, boolean[] visited, LinkedList<Integer> topoSorted) {
        visited[u] = true;
        for (int[] v : adj[u]) {
            if (!visited[v[0]]) {
                dfs(adj, v[0], visited, topoSorted);
            }
        }
        topoSorted.addFirst(u);
    }
}
