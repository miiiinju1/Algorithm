
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Move {
		int K_count;
		int count;
		int x, y;

		public Move(int y, int x, int K_count, int count) {
			this.x = x;
			this.y = y;
			this.K_count = K_count;
			this.count = count;

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		boolean[][][] visited = new boolean[K+1][H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				if (st.nextToken().equals("1"))
					for(int z=0;z<=K;z++)
						visited[z][i][j] = true;

			}
		}

		Queue<Move> queue = new LinkedList<Move>();
		queue.add(new Move(0, 0, K, 0));
		visited[K][0][0]=true;
		int[] K_dx = { 2, 1, -1, -2, -2, -1, 1, 2 };
		int[] K_dy = { 1, 2, 2, 1, -1, -2, -2, -1 };

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		for(int i = 0;i<=K;i++)
			if (visited[i][H - 1][W - 1]) {
				System.out.println(0);
				return;
			}
		while (true) {

			Move now = queue.poll();
			if (now == null) {
				System.out.println(-1);
				return;
			}
			for (int d = 0; d < 4; d++) {

				if ((now.y + dy[d] >= 0 && now.y + dy[d] < H && now.x + dx[d] >= 0 && now.x + dx[d] < W)
						&& !visited[now.K_count][now.y + dy[d]][now.x + dx[d]]) {
					visited[now.K_count][now.y + dy[d]][now.x + dx[d]] = true;
					queue.add(new Move(now.y + dy[d], now.x + dx[d], now.K_count, now.count + 1));

				}
			}

			if (now.K_count > 0)
				for (int d = 0; d < 8; d++) {
					if ((now.y + K_dy[d] >= 0 && now.y + K_dy[d] < H && now.x + K_dx[d] >= 0 && now.x + K_dx[d] < W)
							&& !visited[now.K_count-1][now.y + K_dy[d]][now.x + K_dx[d]]) {
						visited[now.K_count-1][now.y + K_dy[d]][now.x + K_dx[d]] = true;
						queue.add(new Move(now.y + K_dy[d], now.x + K_dx[d], now.K_count - 1, now.count + 1));
					}

				}

		
			for(int i = 0;i<=K;i++)
			if (visited[i][H - 1][W - 1]) {
				System.out.println(now.count + 1);
				return;
			}

		}

	}

}
