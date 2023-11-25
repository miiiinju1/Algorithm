import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] map = null;
	static int[] dy = { 0, 0, 1, -1 };
	static int hole_Y, hole_X;
	static int[] dx = { 1, -1, 0, 0 };// r,l,down,up

	static class Point {
		int red_Y, red_X;

		int blue_Y, blue_X;

		int count;

		boolean success = false;

		public Point(int rY, int rX, int bY, int bX, int count) {
			this.red_Y = rY;
			this.red_X = rX;
			this.blue_Y = bY;
			this.blue_X = bX;
			this.count = count;
		}
		
		public Point(Point o, int count)
		{
			this.red_Y = o.red_Y;
			this.red_X = o.red_X;
			this.blue_Y = o.blue_Y;
			this.blue_X = o.blue_X;
			this.count =count;
		}

		@Override
		public int hashCode() {
			return Objects.hash(blue_X, blue_Y, red_X, red_Y);
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
			Point other = (Point) obj;
			return blue_X == other.blue_X && blue_Y == other.blue_Y && red_X == other.red_X && red_Y == other.red_Y;
		}

	}

	public static Point Move(Point now, int direction) {
		Point temp = now;
		boolean suc = false;
		boolean r = false, b = false;
		int rY=temp.red_Y, rX=temp.red_X, bY=temp.blue_Y, bX=temp.blue_X;
		while (true) {
			if(!suc&&!map[rY+dy[direction]][rX+dx[direction]])
			{
				map[rY][rX]=false;
			}
			if (!b && map[bY + dy[direction]][bX + dx[direction]]) {
				bY += dy[direction];
				bX += dx[direction];
				
				if(!suc&&!map[bY+dy[direction]][bX+dx[direction]])
				{
					map[bY][bX]=false;
				}
				if (bY == hole_Y && bX == hole_X)
				{
					map[bY][bX]=true;
					map[rY][rX]=true;
					return null;
				}
			} else {
				b = true;
				map[bY][bX]=false;
			}
			if (!r && map[rY + dy[direction]][rX + dx[direction]]) {
				rY +=dy[direction];
				rX += dx[direction];
				if (rY == hole_Y && rX == hole_X) {
					suc = true;

				}
				if(!suc&&!map[rY+dy[direction]][rX+dx[direction]])
				{
					map[rY][rX]=false;
				}
				
			} else {
				r = true;
                if(!suc)
				map[rY][rX]=false;
			}
			if (r && b)
			{
				map[bY][bX]=true;
				map[rY][rX]=true;
				break;
			}
		}

		temp = new Point(rY,rX,bY,bX,temp.count);
		temp.success = suc;
		return temp;
	}
//R와 B가 겹치는 경우만 해결하면 끝인데, 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new boolean[N][M];// 벽 = false, 갈 수 있으면 true
		int rY = -1, rX = -1, bY = -1, bX = -1;
		for (int i = 0; i < N; i++) {
			Arrays.fill(map[i], true);
			//st = new StringTokenizer(br.readLine(), "");
			String[] ary = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				String temp = ary[j];
				if (temp.equals("#")) {
					map[i][j] = false;
				} else if (temp.equals("R")) {
					rY = i;
					rX = j;
				} else if (temp.equals("B")) {
					bY = i;
					bX = j;
				} else if (temp.equals("O")) {
					hole_Y = i;
					hole_X = j;
				} else
					map[i][j] = true;
			}
		}

		////////////////////////////////////////////////////
		Queue<Point> queue = new LinkedList<Point>();
		HashSet<Point> visited = new HashSet<Point>();
		visited.add(new Point(rY,rX,bY,bX,0));
		queue.add(new Point(rY, rX, bY, bX, 0));
		
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				Point temp = Move(now, i);
				if (temp  != null) {
					if(temp.count>=10)
					{
						System.out.println(-1);
						return ;
					}
					if (temp.success) {
							System.out.println(temp.count + 1);
							return;
						}
					if (!visited.contains(temp)) {
						

						temp.count++;
						queue.add(temp);
						visited.add(temp);
					}
				}
			}

		}
		System.out.println(-1);

	}

}
