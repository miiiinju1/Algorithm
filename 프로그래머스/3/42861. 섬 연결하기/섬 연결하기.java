import java.util.*;
class Solution {
    static int[] parent;
    static int find(int v) {
        if(parent[v]==v) {
            return v;
        }
        parent[v] = find(parent[v]);
        return parent[v];
    }
    static int cost = 0;
    static void union(int a, int b, int c) {
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb) {
            cost+=c;
            parent[fa] = fb;
        }
    }
    public int solution(int n, int[][] costs) {
        parent = new int[n+1];
        for(int i= 1;i<=n;i++) {
            parent[i] = i;
        }
        List<int[]> edges = new ArrayList<>();
        for(int[] c : costs) {
            edges.add(c);
        }
        Collections.sort(edges, Comparator.comparingInt(arr -> arr[2]));
        for(int[] c : edges) {
            union(c[0],c[1],c[2]);
        }
        return cost;
    }
}