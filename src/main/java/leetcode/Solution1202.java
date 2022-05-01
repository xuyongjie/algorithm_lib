package leetcode;

import java.util.*;

public class Solution1202 {
    public static void main(String[] args) {
        Solution1202 solution1202 = new Solution1202();
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> pair1 = new ArrayList<>();
        pair1.add(0);
        pair1.add(3);
        List<Integer> pair2 = new ArrayList<>();
        pair2.add(1);
        pair2.add(2);
        List<Integer> pair3 = new ArrayList<>();
        pair3.add(0);
        pair3.add(2);
        pairs.add(pair1);
        pairs.add(pair2);
        pairs.add(pair3);
        System.out.println(solution1202.smallestStringWithSwaps("dcab", pairs));
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        char[] chars = s.toCharArray();
        Set<Integer>[] graph = new HashSet[s.length()];
        for (int i = 0; i < pairs.size(); i++) {
            int v1 = pairs.get(i).get(0), v2 = pairs.get(i).get(1);
            if (v1 == v2) {
                continue;
            }
            if (graph[v1] == null) {
                graph[v1] = new HashSet<>();
            }
            graph[v1].add(v2);
            if (graph[v2] == null) {
                graph[v2] = new HashSet<>();
            }
            graph[v2].add(v1);
        }

        boolean[] visited = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (!visited[i]) {
                List<Integer> connected = new ArrayList<>();
                dfs(graph, i, visited, connected);
                connected.sort(Comparator.comparingInt(o -> o));
                char[] connectedChar = new char[connected.size()];
                for (int j = 0; j < connectedChar.length; j++) {
                    connectedChar[j] = s.charAt(connected.get(j));
                }
                Arrays.sort(connectedChar);
                for (int k = 0; k < connected.size(); k++) {
                    chars[connected.get(k)] = connectedChar[k];
                }
            }
        }
        return new String(chars);
    }

    private void dfs(Set<Integer>[] graph, int v, boolean[] visited, List<Integer> connected) {
        if (!visited[v]) {
            visited[v] = true;
            connected.add(v);
            if (graph[v] != null) {
                for (int each : graph[v]) {
                    dfs(graph, each, visited, connected);
                }
            }
        }
    }
}
