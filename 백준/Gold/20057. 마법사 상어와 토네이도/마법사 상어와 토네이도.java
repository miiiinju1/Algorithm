import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class Sand {
		int y,x, percentage;

		public Sand(int y, int x, int percentage) {
			this.y = y;
			this.x = x;
			this.percentage = percentage;
		}
		
		
	}
	
	static Sand[] sandX;
	static Sand[] sandY;
	
	static int outSum = 0;

	public static void sandMove(int y, int x, int[][] map, Sand[] sands, int dir, int nextY, int nextX) {
		
		int initial = map[y][x];
		int sum = 0;
		for(int i = 0;i<sands.length;i++) {
			int Y = sands[i].y*dir + y;
			int X = sands[i].x*dir + x;
			
			int temp = initial * sands[i].percentage / 100;
			sum += temp;
			
			if(!(Y>=0&&X>=0&&Y<map.length&&X<map[0].length)) {
				outSum+= temp;
			}
			else {
				map[Y][X] += temp;
			}
		}
		
		if(nextY>=0&&nextX>=0&&nextY<map.length&&nextX<map[0].length)  {
			map[nextY][nextX] += (initial - sum);
		}
		else {
			outSum += (initial-sum);
		}
		
		
		
		map[y][x] = 0;
	}
	
	public static void main(String[] args) throws Exception, IOException {
		
		sandX = new Sand[9];
		sandX[0] = new Sand(1,1,1);
		sandX[1] = new Sand(-1,1,1);
		sandX[2] = new Sand(1,0,7);
		sandX[3] = new Sand(2,0,2);
		sandX[4] = new Sand(1,-1,10);
		sandX[5] = new Sand(0,-2,5);
		sandX[6] = new Sand(-1,-1,10);
		sandX[7] = new Sand(-1,0,7);
		sandX[8] = new Sand(-2,0,2);
		
		sandY = new Sand[9];
		sandY[0] = new Sand(-1,-1,1);
		sandY[1] = new Sand(-1,1,1);
		sandY[2] = new Sand(0,-1,7);
		sandY[3] = new Sand(0,1,7);
		sandY[4] = new Sand(0,-2,2);
		sandY[5] = new Sand(0,2,2);
		sandY[6] = new Sand(1,-1,10);
		sandY[7] = new Sand(1,1,10);
		sandY[8] = new Sand(2,0,5);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for(int i = 0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j= 0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int y=N/2,x=N/2;
		int d = 0;
		
		while(true) {
			++d;
			
			if(d==N) {
				for(int i = 1;i<d;i++) {
					--x;
					sandMove(y,x,map, sandX, 1, y,x-1);
				}
				break;
			}
			for(int i = 0;i<d;i++) {
				--x;
				sandMove(y,x,map, sandX, 1, y,x-1);
			}
			
			for(int i = 0;i<d;i++) {
				++y;
				sandMove(y,x,map, sandY, 1, y+1,x);

			}
			++d;
			for(int i = 0;i<d;i++) {
				++x;
				sandMove(y,x,map, sandX, -1, y,x+1);

			}
			for(int i = 0;i<d;i++) {
				--y;
				sandMove(y,x,map, sandY, -1, y-1,x);

			}
		}
		
		System.out.println(outSum);
		
	}

}
