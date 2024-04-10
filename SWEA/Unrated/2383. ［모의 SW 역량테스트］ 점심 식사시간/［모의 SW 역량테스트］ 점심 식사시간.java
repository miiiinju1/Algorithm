import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {
	static class Point {
		int y,x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}	
	}
	
	static class Stair {
		int y,x,k;

		public Stair(int y, int x,int k) {
			this.y = y;
			this.k = k;
			this.x = x;
		}	
	}

	public static int calculate(PriorityQueue<Integer> a, PriorityQueue<Integer> b,int aK, int bK) {
		
		int aTime = 0;
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		while(true) {
			
			while(!q.isEmpty()&& q.peek()==aTime) {
				q.poll();
			}
			
			while(!a.isEmpty()) {
				if(q.size()<3) {
					int t = a.poll();
					int temp = Math.max(aTime, t)+aK;
					if(aTime<t+1) {
						++temp;
					}
					q.add(temp);
				}
				else {
					break;
				}
				
			}
			if(q.isEmpty() && a.isEmpty()) {
				break;
			}
			++aTime;
			
		}
		int bTime = 0;
		q = new ArrayDeque<>();
		
		while(true) {
	        while(!q.isEmpty()&& q.peek()==bTime) {
				q.poll();
			}
			
			while(!b.isEmpty()) {
				if(q.size()<3) {
					int t = b.poll();
					int temp = Math.max(bTime, t)+bK;
					
					if(bTime<t+1) {
						++temp;
					}
					q.add(temp);
				}
				else {
					break;
				}
				
			}
			if(q.isEmpty() && b.isEmpty()) {
				break;
			}
			++bTime;
			
			
		}
//		System.out.println(bTime);
		return Math.max(aTime, bTime);
		
		
	}
	
	public static int distance(Point man, Stair stair) {
		return Math.abs(man.y-stair.y) + Math.abs(man.x-stair.x); 
		
	}
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());
			
			List<Point> man = new ArrayList<Point>();
			List<Stair> stair = new ArrayList<>();
			
			
			int[][] map = new int[N][N];
			
			int aK =0;
			int bK = 0;
			for(int i= 0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j= 0;j<N;j++) {
					int temp = Integer.parseInt(st.nextToken());
					
					if(temp==1) {
						//사람
						man.add(new Point(i,j));
					}
					else if(temp!=0){
						// 계단
						stair.add(new Stair(i,j,temp));		
					}
				}
			}
			
			
			int min = Integer.MAX_VALUE;
			
			aK = stair.get(0).k;
			bK = stair.get(1).k;
			
			int max = 1<<(man.size());
			//비트마스킹으로 검사
			for(int i = 0;i<max;i++) {
				PriorityQueue<Integer> a = new PriorityQueue<>();
				PriorityQueue<Integer> b = new PriorityQueue<>();
				for(int j = 0;j<man.size();j++) {
					if((i & (1<<j)) != 0) {
						a.add(distance(man.get(j), stair.get(0)));
					}
					else {
						b.add(distance(man.get(j), stair.get(1)));
					}
				}


				min = Math.min(min,calculate(a,b,aK,bK));
			}
			
			bw.write("#"+tc+" "+min+"\n");
		
			
		}	bw.flush();
			bw.close();
			
	}

}
