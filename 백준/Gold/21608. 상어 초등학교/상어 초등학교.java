import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		
		HashMap<Integer, Set<Integer>> good = new HashMap<>();
		for(int i= 0;i<N*N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
		
			int student = Integer.parseInt(st.nextToken());

			HashSet<Integer> set  = new HashSet<>();
			
			
			for(int z = 0;z<4;z++) {
				set.add(Integer.parseInt(st.nextToken()));
			}
			
			
			
			good.put(student, set);
			
			Point selected = selectA(map,set);
			
			map[selected.y][selected.x]= student; 
			
		
		}
		int sum = 0;
		for(int i= 0;i<N;i++) {
			for(int j= 0;j<N;j++) {
				
				int student = map[i][j];
				
				Set<Integer> goods = good.get(student);
				
				
				int count = 0;
				for(int d = 0;d<4;d++) {
					int y = dy[d]+i;
					int x = dx[d]+j;
					
					if(y>=0&&x>=0&&y<N&&x<N) {
						if(goods.contains(map[y][x])) {
							++count;
						}
					}
					
				}
				
				if(count==0) {
					continue;
				}
				else {
					sum+=Math.pow(10, count-1);
				}
				
			}
		}
		System.out.println(sum);
	}
	
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	
	static class Point {
		int y,x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	public static Point selectA(int[][] map, Set<Integer> set) {
		List<Point> result = new ArrayList<>();
		int countMax = -1;
		for(int i= 0;i<map.length;i++) {
			for(int j= 0;j<map[0].length;j++) {
				
				if(map[i][j]!=0) {
					continue;
				}
				
				int nowCount = 0;
				
				for(int d = 0;d<4;d++) {
					
					int y = dy[d]+i;
					int x = dx[d]+j;
					
					
					if(y>=0&&x>=0&&y<map.length&&x<map[0].length) {
						
						if(set.contains(map[y][x])) {
							nowCount++;
						}
						
					}
					
				}
				
				
				
				if(countMax<nowCount) {
					countMax = nowCount;
					result = new ArrayList<>();
					result.add(new Point(i,j));
			
				}
				else if(countMax==nowCount) {
					result.add(new Point(i,j));
				}
			}
			
			
		
		}
		

		if(result.size()==1) {
			return result.get(0);
		}
		else {
			return selectB(map, result);
		}
		
	}
	
	
	public static Point selectB(int[][] map, List<Point> result) {
	
		
		List<Point> maxResult = new ArrayList<>();
		int max = 0;
		for(Point p : result) {
			
			int nowCount = 0;
			for(int d = 0;d<4;d++) {
				
				int y = dy[d]+p.y;
				int x = dx[d]+p.x;
				
				
				if(y>=0&&x>=0&&y<map.length&&x<map[0].length && map[y][x]==0) {
					++nowCount;
				}
				
			}
			
			
			if(max<nowCount) {
				maxResult = new ArrayList<>();
				maxResult.add(p);
				max = nowCount;
			}
			else if(max==nowCount) {
				maxResult.add(p);
			}
			
			
			
			
			
			
			
		}
		
		if(maxResult.size()==1) {
			return maxResult.get(0);
		}
		else {
			return maxResult.stream().min((o1,o2) -> {
				
				if(o1.y==o2.y) {
					return Integer.compare(o1.x, o2.x);
				}
				return Integer.compare(o1.y,o2.y);
				
			}).get();
			
			
		}

	}

	
	
}
