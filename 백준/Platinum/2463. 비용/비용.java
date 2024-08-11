
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	static int[] size;
	static class Edge implements Comparable<Edge> {
		int a,b,cost;

		public Edge(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.cost, this.cost);
		}
	}
	static int find(int v) {
		if(parent[v] == v) {
			return v;
		}
		parent[v] = find(parent[v]);
		return parent[v];
	}

	static int union(int a, int b, long cost) {
		int fa = find(a);
		int fb = find(b);
		if (fa != fb) {
			parent[fa] = fb;
			int sizeA = size[fa];
			int sizeB = size[fb];

			size[fb] += size[fa];

			return multiply(multiply(sizeA, sizeB), cost);

		}
		return 0;
	}
	static int MOD = 1_000_000_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		var st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		size = new int[N + 1];
		for(int i= 1;i<=N;i++) {
			parent[i] = i;
			size[i] = 1;
		}

		List<Edge> edges = new ArrayList<>();
		int[] costs = new int[100_001];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			costs[cost] += 1;
			edges.add(new Edge(a, b, cost));
		}
		Collections.sort(edges);

		for(int i= 1;i<=100_000;i++) {
			costs[i] = multiply(costs[i], i);
			costs[i] = add(costs[i], costs[i-1]);
		}

		int sum = 0;
		for (Edge edge : edges) {
			int u = union(edge.a, edge.b, costs[edge.cost]);
			sum = add(sum, u);

		}
		System.out.println(sum);
	}

	static int add(int a, int b) {
		return (a % MOD + b % MOD) % MOD;
	}

	static int multiply(long a, long b) {
		return (int)((a * b) % MOD);
	}

}
