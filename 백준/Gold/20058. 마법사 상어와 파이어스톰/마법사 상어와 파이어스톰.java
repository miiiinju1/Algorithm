import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		

		int row = (int)Math.pow(2,N);
		int[][] map = new int[row][row];
		
		for(int i= 0;i<row;i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j= 0;j<row;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		
		}
		
		
		

		st = new StringTokenizer(br.readLine());
		for(int i= 0;i<Q;i++) {
				
		
			map = rotate(map, Integer.parseInt(st.nextToken()));
			map = remove(map);
			
				
			
		}
		

		
		
		int sum = sum(map);
		System.out.println(sum);
		System.out.println(largest(map));
		
		
		
	}
	
	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		
	}
	public static int largest(int[][] map) {
		
		boolean[][] visited =new boolean[map.length][map[0].length];
		
		
		
		int max = 0;
		
		ArrayDeque<Point> q= new ArrayDeque<>();
		
		for(int i= 0;i<map.length;i++) {
			for(int j= 0;j<map[0].length;j++) {

				if(!visited[i][j] && map[i][j]!=0) {
					
					int temp = 0;
					
					q.add(new Point(i,j));
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						
						Point now = q.poll();
						++temp;
						
						for(int d = 0;d<4;d++) {
						
							int y = dy[d]+ now.y;
							int x = dx[d]+ now.x;

							if(y>=0&&x>=0&&y<map.length&&x<map[0].length &&!visited[y][x]&&map[y][x]!=0) {
								visited[y][x] = true;
								q.add(new Point(y,x));
//								
							}
							
						}
						
					}
					
					
					
					max = Math.max(max, temp);
				}
				
			}
		
		}
		return max;
		
	}
	
	public static int sum(int[][] map) {
		int sum = 0;
		for(int i= 0;i<map.length;i++) {
			for(int j= 0;j<map[0].length;j++) {
				sum+= map[i][j];
			}
		}
		return sum;
	}
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	public static int[][] remove(int[][] map) {
		
		
		int[][] result = new int[map.length][map[0].length];
		
		for(int i= 0;i<map.length;i++) {
			for(int j= 0;j<map[0].length;j++) {
				int nowCount = 0;
			
				for(int d = 0;d<4;d++) {
					
					int y = dy[d]+i;
					int x = dx[d]+j;
					
					
					if(y>=0&&x>=0&&y<map.length&&x<map[0].length) {
						
						if(map[y][x]>0) {
							++nowCount;
						}
					}
				}
				
				
				if(nowCount>=3) {
					result[i][j] = map[i][j];
				}
				else {
					if(map[i][j] == 0) {
						result[i][j] = 0;
					}
					else {
					result[i][j] = map[i][j]-1;
					}
				}
				
				
				
			}
		}
		
		return result;
		
	}
	
	
	
public static int[][] rotate(int[][] map, int L) {
		
		if(L==0) {
			return map;
		}
		int[][] result = new int[map.length][map[0].length];
		int d = (int)Math.pow(2,L);
		
		for (int colStart = 0; colStart < map.length; colStart += d) {
		    for (int rowStart = 0; rowStart < map[0].length; rowStart += d) {
		    	
		    	for(int i = 0;i<d;i++) {
		    		for(int j= 0;j<d;j++) {
		    			
		    			result[colStart+j][rowStart+d-i-1] = map[colStart+i][rowStart+j];
		    			
		    		}
		    	}

		    }
		}
		
		
		return result;
		
	}
}

	


