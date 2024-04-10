import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	
	static class Point {
		int y, x, depth;

		public Point(int y, int x, int depth) {
			this.y = y;
			this.x = x;
			this.depth = depth;
		}
	}
	
	public static int maxLength(int[][] map,int targetY, int targetX, int K) {
		
		int result = 0;
		
		int originalValue = map[targetY][targetX];
		for(int k = 0;k<=K;k++) {
			
			
			int max = 0;
			ArrayDeque<Point> q = new ArrayDeque<>();
			
			for(int i= 0;i<map.length;i++) {
				for(int j= 0;j<map[0].length;j++) {
					if(map[i][j]>max) {
						max = map[i][j];
						q = new ArrayDeque<>();
						q.add(new Point(i,j,1));
					}
					else if(map[i][j] == max) {
						q.add(new Point(i,j,1));
					}
				}
			}
			
			map[targetY][targetX]-=k;

			while(!q.isEmpty()) {
				Point now = q.poll();
				result = Math.max(result, now.depth);
				
				for(int i = 0;i<4;i++) {
					int y = dy[i]+ now.y;
					int x = dx[i]+ now.x;
					
					if(y>=0&&x>=0&&y<map.length&&x<map[0].length && map[y][x]<map[now.y][now.x]) {
						q.add(new Point(y,x,now.depth+1));
						
					}
				}
			}
			
            map[targetY][targetX] = originalValue;
			
		}
		
		
		return result;
	}

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for(int i= 0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j= 0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				
			}
			int max = 0;
			for(int i= 0;i<N;i++) {
				for(int j= 0;j<N;j++) {
					max = Math.max(max, maxLength(map,i,j,K));
				}
			}
			bw.write("#"+tc+" "+max+"\n");
			
			
		}
		bw.flush();
		bw.close();
	}

}
