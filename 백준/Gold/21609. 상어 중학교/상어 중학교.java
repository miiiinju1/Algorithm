import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	
	static int[][] map;
	
//	static boolean[][] visited;
	
	static int N,M;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st =new StringTokenizer(br.readLine());

		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		
		
		map = new int[N][N];
//		visited = new boolean[N][N];
		
		for(int i = 0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int sum = 0;
		
		
		
	
		while(true) {
			
			int temp = find();
//			System.out.println("?");
			
			if(temp==-1) {
				break;
			}
			
//			System.out.println("temp"+temp);
			sum+= temp;
//			System.out.println();
//			for(int i= 0;i<N;i++) {
//				System.out.println();
//				for(int j= 0;j<N;j++) {
//					System.out.printf("%2d ",map[i][j]);
//				}
//			}
//			System.out.println();
			gravity();
			
//			System.out.println("AfterGRAVITY 1");
//			for(int i= 0;i<N;i++) {
//				System.out.println();
//				for(int j= 0;j<N;j++) {
//					System.out.printf("%2d ",map[i][j]);
//				}
//			}
//			System.out.println();
			rotate();
//			System.out.println("AFTER ROTATE");
//			for(int i= 0;i<N;i++) {
//				System.out.println();
//				for(int j= 0;j<N;j++) {
//					System.out.printf("%2d ",map[i][j]);
//				}
//			}
//			System.out.println();
		
			gravity();
			
//			System.out.println("AFTER GRAVITY2");
//			for(int i= 0;i<N;i++) {
//				System.out.println();
//				for(int j= 0;j<N;j++) {
//					if(map[i][j]==Integer.MIN_VALUE) {
//						System.out.print("[ ]");
//					}
//					else
//					System.out.printf("%2d ",map[i][j]);
//				}
//			}
//			System.out.println();
			
		
		
		}
		
		
		System.out.println(sum);
		
		
		
	}
	

	static class Point { 
		int y, x;

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		
	}
	
	
	
	private static List<Point> search(int startY, int startX, int color) {
		
		Queue<Point> q = new ArrayDeque<>();
		
		
		boolean[][] visited = new boolean[N][N];
		q.add(new Point(startY,startX));
		
		visited[startY][startX]=true;
		List<Point> result = new ArrayList<>();
		while(!q.isEmpty()) {
			
			Point now = q.poll();
			
			result.add(now);
				
			for(int d =0;d<4;d++) {
				int y = dy[d]+now.y;
				int x = dx[d]+now.x;
				
				//방문하지 않은 곳이고 
				if(isValid(y,x) && !visited[y][x]) {
					if(map[y][x] == 0 || map[y][x] == color)  {
						visited[y][x] = true;
						q.add(new Point(y,x));
					}
					
				}
			}
		}
		
//		System.out.println("result:"+result);
		if(result.size()<2) {
			return new ArrayList<>();
		}
		
		
		for(Point p : result) {
			if(map[p.y][p.x]!=0) {
				findVisited[p.y][p.x] = true;
			}
		}
		
		return result;
	}
	
	static boolean isValid(int y, int x) {
		return y>=0&&x>=0&&y<N&&x<N;
	}
	
	static void rotate() {
		
		int[][] result = new int[N][N];
		for(int i= 0;i<N;i++) {
			for(int j=0;j<N;j++) {
				result[N-j-1][i] = map[i][j];
			}
		}
		map = result;
		
		
	}
	
	static class Group {
		List<Point> points;
		Point root;
		int rainbowCount=0;
		public Group(List<Point> points) {

			this.points = points;
			rainbowCount = (int) points.stream()
					.filter(p->map[p.y][p.x]==0).count();
			root = points.stream()
				.filter(p-> map[p.y][p.x]!=0)
				.min((o1,o2) -> {
					if(o1.y==o2.y) {
						return Integer.compare(o1.x, o2.x);
					}
					return Integer.compare(o1.y,o2.y);
				}).orElse(null);
		}
		
		//64 + 4 + 9 
		
		@Override
		public String toString() {
			return "Group [points=" + points + ", root=" + root + "]";
		}


		public List<Point >getGroup() {
			return points;
		}
		
	}
	
	static void gravity() {
		
		for(int j= 0;j<N;j++) {
			for(int i = N-1;i>=0;i--) {
				if(map[i][j]!=-1 && map[i][j]!=VISITED) {
					
					int y = i;
					int x = j;
					
					int value = map[i][j];
					map[i][j] = VISITED;
					while(true) {
				
						int Y = y+dy[2];
						int X = x+dx[2];
						
						if(!isValid(Y,X)||map[Y][X]>=0||map[Y][X]==-1 ) {
							map[y][x] = value;
							break;
						}
						
						y=Y;
						x=X;
					}
					
				}
				
				
			}
			
		}
		
		
		
	}
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	static boolean[][] findVisited;
	
	private static int find() {
		
		int max = 0;
		
		findVisited = new boolean[N][N];
		List<Group> maxGroup = new ArrayList<>();
		
		for(int i = 0;i<N;i++) {
			for(int j= 0;j<N;j++) {
				// 만약 일반블록이면 
				if(map[i][j]!=VISITED) {
					if(map[i][j]>0 && !findVisited[i][j]) {
						
						List<Point> result = search(i,j,map[i][j]);
						
//						System.out.println(i+" "+j+" "+result);
						if(result.isEmpty()) {
							continue;
						}
						if(max<result.size()) {
							max = result.size();
							maxGroup = new ArrayList<>();
							maxGroup.add(new Group(result));
						}
						else if(max==result.size()) {
							maxGroup.add(new Group(result));
						}
					}
				}
				
			}
		}
		
//		System.out.println("max: "+max+" "+maxGroup);
		if(maxGroup.isEmpty()) {
			return -1;
		}
		
		
		List<Point> result = maxGroup.stream()
			.min((o1,o2)-> {
				if(o1.rainbowCount==o2.rainbowCount ) {
					if(o1.root.y==o2.root.y) {
						return Integer.compare(o2.root.x, o1.root.x);
					}
					return Integer.compare(o2.root.y, o1.root.y);
				}
				return Integer.compare(o2.rainbowCount,o1.rainbowCount);
			}).map(Group::getGroup)
			.get();
		
		
		
		for(Point point : result) {
			map[point.y][point.x] =  VISITED;
		}
		
		return max*max;
		
	}
	
	static final int VISITED = Integer.MIN_VALUE;

}

/*
 *   
 *   
 *     -1
 *   1    2 
 *   3    3
 *        1 
 *     0    1
 * 
 * 
 * 
 *              1
 *       2 3 1 -1
 *       2 1 2  2
 *              0
 *       1 3 
 *        
 * */


//5 5
//1 1 -1 3 0
//-1 2 -1 5 5
//1 2 3 -1 2
//3 4 0 -1 3
//1 2 2 3 0

