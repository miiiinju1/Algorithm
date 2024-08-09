
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int y,x;

		public Edge(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int find(int v) {
		if (parent[v]==v) {
			return v;
		}
		parent[v] = find(parent[v]);
		return parent[v];
	}

	static int union(int a, int b) {
		int fa = find(a);
		int fb = find(b);

		if(fa!=fb) {
			parent[fa] = fb;
			int s1 = size[fb];
			int s2 = size[fa];
			size[fb] += size[fa];
			size[fa] = 0;
			return s1*s2;
		}
		return 0;
	}
	static int[] parent, size;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());


		List<Edge> edges = new ArrayList<>();
		int N = Integer.parseInt(st.nextToken());
		parent = new int[N + 1];
		size = new int[N + 1];
		for(int i= 1;i<=N;i++) {
			parent[i] = i;
			size[i] = 1;
		}
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		for(int i= 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			edges.add(new Edge(y, x));
		}

		List<Edge> removeList = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		for(int i = 0;i<Q;i++) {
			int r = Integer.parseInt(br.readLine());
			removeList.add(edges.get(r - 1));
			set.add(r - 1);
		}

		for(int i = 0;i<M;i++) {
			if(set.contains(i)) continue;

			Edge edge = edges.get(i);
			union(edge.y, edge.x);
		}
		long sum = 0;

		Collections.reverse(removeList);

		for (Edge edge : removeList) {
			sum += union(edge.y, edge.x);
		}
		System.out.println(sum);
	}
}
