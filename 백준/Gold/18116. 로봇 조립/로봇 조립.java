
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static int[] size;
	static int find(int v) {
		if(parent[v] == v) {
			return v;
		}
		parent[v] = find(parent[v]);
		return parent[v];
	}

	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa!=fb) {
			parent[fa] = fb;
			size[fb] += size[fa];
			size[fa] = 0;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		parent = new int[1000001];
		size = new int[1000001];

		for(int i= 1;i<1000001;i++) {
			parent[i] = i;
			size[i] = 1;
		}
		for(int i= 0;i<N;i++) {
			var st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);

			if(command == 'I') {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}
			else {
				int a = Integer.parseInt(st.nextToken());
				bw.write(size[find(a)] + "\n");

			}

		}
		bw.flush();bw.close();
	}
}
