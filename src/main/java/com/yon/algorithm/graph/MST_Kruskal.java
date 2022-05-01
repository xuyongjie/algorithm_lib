package com.yon.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 最小生成树（minimum spanning tree），kruskal算法
 */
public class MST_Kruskal {
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
        MST_Kruskal mst_kruskal = new MST_Kruskal();
        mst_kruskal.MST(graph).forEach(each -> System.out.println(Arrays.toString(each)));
    }

    /**
     * 算法思路：
     * 所有节点独立作为一棵树
     * 每次选择一条最小权重的边(u,v)
     * 检查u,v是否连通，不连通的话执行union操作（利用并查集结构）
     * 所有执行union的边集就是最小生成树的边集
     *
     * @param adj 邻接表表示法，数组下标为节点id，list中的每一项存的是相邻节点和对应边权重 int[]{点，边权重}
     * @return 联通所有节点的权重和最小的边集
     */
    public List<int[]> MST(List<int[]>[] adj) {
        int vCount = adj.length;
        List<int[]> allEdges = new ArrayList<>();//int[]{weight,u,v}
        List<int[]> selectedEdges = new ArrayList<>();
        for (int i = 0; i < vCount; i++) {
            for (int[] v : adj[i]) {
                allEdges.add(new int[]{v[1], i, v[0]});
            }
        }
        //针对所有边进行按权重排序
        allEdges.sort(Comparator.comparing(o -> o[0]));
        UnionFind unionFind = new UnionFind(vCount);
        for (int i = 0; i < allEdges.size(); i++) {
            int[] edge = allEdges.get(i);
            if (unionFind.find(edge[1]) != unionFind.find(edge[2])) {
                //联通这两个节点
                unionFind.union(edge[1], edge[2]);
                //将该边加入selected边集
                selectedEdges.add(edge);
            }
        }
        return selectedEdges;
    }
}
