package com.yon.algorithm.graph;

import com.yon.algorithm.array.PriorityQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 单源最短路径（single source shortest path）,Dijkstra 算法
 */
public class SSSP_Dijkstra {
    public static void main(String[] args) {
        List<int[]>[] graph = new ArrayList[5];
        ArrayList<int[]> v0Adj = new ArrayList<>();
        v0Adj.add(new int[]{1, 6});
        v0Adj.add(new int[]{2, 7
        });
        graph[0] = v0Adj;
        ArrayList<int[]> v1Adj = new ArrayList<>();
        v1Adj.add(new int[]{2, 8});
        v1Adj.add(new int[]{3, 5});
        v1Adj.add(new int[]{4, 4});
        graph[1] = v1Adj;
        ArrayList<int[]> v2Adj = new ArrayList<>();
        v2Adj.add(new int[]{3, 3});
        v2Adj.add(new int[]{4, 9});
        graph[2] = v2Adj;
        ArrayList<int[]> v3Adj = new ArrayList<>();
        v3Adj.add(new int[]{1, 2});
        graph[3] = v3Adj;
        ArrayList<int[]> v4Adj = new ArrayList<>();
        v4Adj.add(new int[]{0, 2});
        v4Adj.add(new int[]{3, 7});
        graph[4] = v4Adj;

        SSSP_Dijkstra sssp_dijkstra = new SSSP_Dijkstra();
        System.out.println(Arrays.toString(sssp_dijkstra.SSSP(graph, 0)));
    }


    /**
     * 算法思想：
     * 带权有向图G(V,E)，允许有环，不允许权值为负
     * 节点集合S表示已确定最短路径的点集，Q=V-S表示尚未确定的点集
     * 每次选出Q中d值最小的加入S（借助优先级队列实现）
     * 对选出节点的出边进行松弛操作
     * 松弛定义：对于边(u,v)，if(d[u]+w(u,v)<d[v]) => d[v]=d[u]+w(u,v) p[v]=u (d[]数组存的是从源点s到该点的最短路径上界，p[]数组存储节点前驱)
     *
     * @param adj 邻接表表示法，点i->list[int[]{点，边权值}]
     * @param s   源点
     * @return 最短路径结果，i节点到s节点的最短路径
     */
    public int[] SSSP(List<int[]>[] adj, int s) {
        int vCount = adj.length;
        int[] d = new int[vCount];
        int[] parent = new int[vCount];//存储节点前驱
        for (int i = 0; i < vCount; i++) {
            d[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        d[s] = 0;
        List<Integer> S = new ArrayList<>();
        PriorityQ Q = new PriorityQ(vCount);
        for (int i = 0; i < vCount; i++) {
            Q.add(i, d[i]);
        }
        while (!Q.isEmpty()) {
            int u = Q.poll();
            S.add(u);
            for (int[] v : adj[u]) {
                //无穷大加上一个数还是无穷大
                int duPlusV = d[u] == Integer.MAX_VALUE ? Integer.MAX_VALUE : d[u] + v[1];
                if (duPlusV < d[v[0]]) {
                    d[v[0]] = duPlusV;
                    parent[v[0]] = u;
                    Q.decrease(v[0], duPlusV);
                }
            }
        }
        return d;
    }
}
