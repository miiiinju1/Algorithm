import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = null;
	static int n, max = 0;// map최대 20이면n은 19,

	static class Phase {
		int[][] map = new int[n][n];
		int count = 0;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(map);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			Phase other = (Phase) obj;
			return Arrays.deepEquals(map, other.map);
		}

		public Phase(int[][] ary, int count) {
			this.count = count;
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					map[i][j] = ary[i][j];
		}

		public Phase(Phase o) {
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					map[i][j] = o.map[i][j];
			this.count = o.count;
		}

	}

	// 오른쪽으로 이동
	public static Phase Move(Phase nowPhase) {
		Phase temp = new Phase(nowPhase);

		for (int i = 0; i < n; i++) {
			int index = n - 1;
			for (int prev_j = n - 1; prev_j >= 0;) {// prev_j는 처음거 prev는 한 칸 전에껄 검

				int now_j = prev_j;
				// now_j는 다음

				if (temp.map[i][prev_j] != 0) {
					while (now_j > 0 && temp.map[i][--now_j] == 0)
						;

					// 여기서 now_j는 0이 아닌 다른 칸을 가리키고

					if (now_j != prev_j) {
						if (temp.map[i][prev_j] == temp.map[i][now_j])// 일반적으로 같은 경우에는
						{
							if (max < temp.map[i][now_j] * 2)
								max = temp.map[i][now_j] * 2;
							int now = temp.map[i][prev_j]*2;
							temp.map[i][now_j] = 0;
							temp.map[i][prev_j]=0;
							temp.map[i][index--] = now;

						} else// 예를 들어 2와 8같이 다른 경우, 8을 검사
						{
							int now = temp.map[i][prev_j];
							
							temp.map[i][prev_j]=0;
							temp.map[i][index--] = now;

						}
					} 
					else// 0, 0이라서 while 안 돌고 같은 거면

					{
						int now = temp.map[i][now_j];
						temp.map[i][now_j] = 0;
						temp.map[i][index--] = now;
						now_j--;
					}
					prev_j = now_j;
				} else
					prev_j--;

			}
		}
		return temp;
	}

	// 왼쪽으로 이동
	public static Phase Move2(Phase nowPhase) {
		Phase temp = new Phase(nowPhase);

		for (int i = 0; i < n; i++) {
			int index = 0;
			for (int prev_j = 0; prev_j < n;) {// prev_j는 처음거 prev는 한 칸 전에껄 검

				int now_j = prev_j;
				// now_j는 다음

				if (temp.map[i][prev_j] != 0) {
					while (now_j < n - 1 && temp.map[i][++now_j] == 0)
						;

					// 여기서 now_j는 0이 아닌 다른 칸을 가리키고

					if (now_j != prev_j) {
						if (temp.map[i][prev_j] == temp.map[i][now_j])// 일반적으로 같은 경우에는
						{
							if (max < temp.map[i][now_j] * 2)
								max = temp.map[i][now_j] * 2;
							int now =temp.map[i][now_j] * 2;
							temp.map[i][now_j] = 0;
							temp.map[i][prev_j]=0;
							
							temp.map[i][index++] = now;
							

						} else// 예를 들어 2와 8같이 다른 경우, 8을 검사
						{
							int now = temp.map[i][prev_j];
							temp.map[i][prev_j]=0;
							temp.map[i][index++] = now;
						}
					} else// 0, 0이라서 while 안 돌고 같은 거면

					{
						int now = temp.map[i][now_j];
						temp.map[i][now_j] = 0;
						temp.map[i][index++] = now;
						now_j++;
					}
					prev_j = now_j;
				} else
					prev_j++;

			}
		}
		return temp;
	}

	// 아래쪽으로 이동
	public static Phase Move3(Phase nowPhase) {
		Phase temp = new Phase(nowPhase);

		for (int j = 0; j < n; j++) {
			int index = n - 1;
			for (int prev_i = n - 1; prev_i >= 0;) {// prev_j는 처음거 prev는 한 칸 전에껄 검

				int now_i = prev_i;
				// now_j는 다음

				if (temp.map[prev_i][j] != 0) {
					while (now_i > 0 && temp.map[--now_i][j] == 0)
						;

					// 여기서 now_j는 0이 아닌 다른 칸을 가리키고

					if (now_i != prev_i) {
						if (temp.map[prev_i][j] == temp.map[now_i][j])// 일반적으로 같은 경우에는
						{
							if (max < temp.map[now_i][j] * 2)
								max = temp.map[now_i][j] * 2;
							int now = temp.map[now_i][j] * 2;
							temp.map[now_i][j] = 0;
							temp.map[prev_i][j] = 0;
							temp.map[index--][j] = now;

						} else// 예를 들어 2와 8같이 다른 경우, 8을 검사
						{
							int now = temp.map[prev_i][j];
							//?????
							temp.map[prev_i][j]=0;
							temp.map[index--][j] = now;
						}
					} else// 0, 0이라서 while 안 돌고 같은 거면

					{
						int now = temp.map[now_i][j];
						temp.map[now_i][j] = 0;
						temp.map[index--][j] = now;
						now_i--;
					}
					prev_i = now_i;
				} else
					prev_i--;

			}
		}
		return temp;
	}

	// 위쪽으로 이동
	public static Phase Move4(Phase nowPhase) {
		Phase temp = new Phase(nowPhase);

		for (int j = 0; j < n; j++) {
			int index = 0;
			for (int prev_i = 0; prev_i < n;) {// prev_j는 처음거 prev는 한 칸 전에껄 검

				int now_i = prev_i;
				// now_j는 다음

				if (temp.map[prev_i][j] != 0) {
					while (now_i < n - 1 && temp.map[++now_i][j] == 0)
						;

					// 여기서 now_j는 0이 아닌 다른 칸을 가리키고

					if (now_i != prev_i) {
						if (temp.map[prev_i][j] == temp.map[now_i][j])// 일반적으로 같은 경우에는
						{
							if (max < temp.map[now_i][j] * 2)
								max = temp.map[now_i][j] * 2;
							int now= temp.map[now_i][j] * 2;
							temp.map[now_i][j] = 0;
							temp.map[prev_i][j] = 0;
							temp.map[index++][j] =now;

						} else// 예를 들어 2와 8같이 다른 경우, 8을 검사
						{
							int now= temp.map[prev_i][j];
							temp.map[prev_i][j]=0;
							temp.map[index++][j] = now;
							
						}
					} else// 0, 0이라서 while 안 돌고 같은 거면

					{
						int now = temp.map[now_i][j];
						temp.map[now_i][j] = 0;
						temp.map[index++][j] = now;
						now_i++;
					}
					prev_i = now_i;
				} else
					prev_i++;

			}
		}
		return temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > max)
					max = map[i][j];
			}
		}

		Queue<Phase> queue = new LinkedList<Phase>();
		HashSet<Phase> visited = new HashSet<Phase>();

		Phase temp = new Phase(map, 0);
		queue.add(temp);
		visited.add(temp);

		while (!queue.isEmpty()) {
			Phase now = queue.poll();

			// 오른쪽
			temp = Move(now);
			if (!visited.contains(temp) & (temp.count < 9)) {
				temp.count++;
				visited.add(temp);
				queue.add(temp);
			}

			temp = Move2(now);
			if (!visited.contains(temp) & (temp.count < 9)) {
				temp.count++;
				visited.add(temp);
				queue.add(temp);
			}

			temp = Move3(now);
			if (!visited.contains(temp) & (temp.count < 9)) {
				temp.count++;
				visited.add(temp);
				queue.add(temp);
			}

			temp = Move4(now);
			if (!visited.contains(temp) & (temp.count < 9)) {
				temp.count++;
				visited.add(temp);
				queue.add(temp);
			}

		}

		System.out.println(max);

	}

}
