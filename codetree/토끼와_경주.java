import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static class Rabbit {
		int y,x,pid,jumpCount;

		public Rabbit(int y, int x, int pid, int jumpCount) {
			this.y = y;
			this.x = x;
			this.pid = pid;
			this.jumpCount = jumpCount;
		}
		
	}

	static int N,M,P;
	static HashMap<Integer, Integer> moveSpeed = new HashMap<>();
	
	static HashMap<Integer, Long> score = new HashMap<>();
	
	static boolean isValid(int y, int x) {
		return y>=0&&x>=0&&y<N&&x<M;
		
	}
	static Point getPoint(int y, int x, int d, int speed) {
		//일단 그냥 구현 ,결과보고 최적화
		if(d%2==0) {
			//상하 
			int moveCount = speed%((N-1)*2);
			
				
				while(moveCount>0) {
					
					
					y = dy[d]*moveCount+y;
					x = dx[d]*moveCount+x;
					
					if(y>=0&&y<N) {
						break;
					}
					if(y<0) {
						
						moveCount = y*-1;
						y=0;
						d=(d+2)%4;
					}
					else if(y>=N) {
						moveCount = y-N+1;
						y=N-1;
						d=(d+2)%4;
					}
				}
				
			
		}
		else {
			int moveCount = speed%((M-1)*2);
			while(moveCount>0) {
				y = dy[d]*moveCount+y;
				x = dx[d]*moveCount+x;
				if(x>=0&&x<M) {
					break;
				}
				if(x<0) {
					moveCount = x*-1;
					x = 0;
					d=(d+2)%4;
				}
				else if(x>=M) {
					moveCount = x-M+1;
					x=M-1;
					d=(d+2)%4;
				}
			}
			

		}
		
		
		
//		System.out.println(y+" "+x);
		
		return new Point(y,x);
		
		
	}
	static long baseSum = 0;
	
	static Rabbit operator200() {
		
		Rabbit rabbit = pq.poll();
		
		PriorityQueue<Point> directionPq = new PriorityQueue<>(
				(o1,o2) -> {
					if(o1.y+o1.x == o2.y+o2.x) {
						if(o1.y==o2.y) {
							return Integer.compare(o2.x, o1.x);
						}
						return Integer.compare(o2.y, o1.y);
						
					}
					return Integer.compare(o2.y+o2.x, o1.y+o1.x);
				});
		for(int d = 0;d<4;d++) {
			directionPq.add(getPoint(rabbit.y,rabbit.x,d,moveSpeed.get(rabbit.pid)));
		}

		Point point = directionPq.poll();
		
		rabbit.jumpCount+=1;
		rabbit.y = point.y;
		rabbit.x = point.x;
		
		pq.add(rabbit);
		int addScore = point.y+point.x+2;
//		System.out.println("rabbit.pi"+rabbit.pid+" "+addScore);
		
		baseSum+=addScore;
		score.replace(rabbit.pid, score.get(rabbit.pid)-addScore);
//		for(Integer pid : score.keySet()) {
//			
//			if(pid == rabbit.pid)
//				continue;
//			
//			score.replace(pid, score.get(pid)+addScore);
//		}
		
		return rabbit;
		
		
		
	}
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};
	
	static PriorityQueue<Rabbit> pq;
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Q = Integer.parseInt(br.readLine());

		
		pq = new PriorityQueue<>(
				(o1,o2) -> {
					if(o1.jumpCount==o2.jumpCount) {
						if(o1.y+o1.x == o2.y+o2.x) {
							if(o1.y==o2.y) {
								if(o1.x==o2.x) {
									return Integer.compare(o1.pid, o2.pid);
								}
								return Integer.compare(o1.x, o2.x);
							}
							return Integer.compare(o1.y,o2.y);
						}
						return Integer.compare(o1.y+o1.x, o2.y+o2.x);
					}
					return Integer.compare(o1.jumpCount,o2.jumpCount);
				});
		
		
		for(int q = 0;q<Q;q++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String operator = st.nextToken();
			
			
			
			if(operator.equals("200")) {
				
				int K = Integer.parseInt(st.nextToken());
				int S = Integer.parseInt(st.nextToken());
				
				HashMap<Integer, Rabbit> temp = new HashMap<>();
				
				for(int k =0;k<K;k++) {
					Rabbit rabbit = operator200();
				
					
					if(temp.containsKey(rabbit.pid)) {
						temp.replace(rabbit.pid, rabbit);
					}
					else {
						temp.put(rabbit.pid, rabbit);
					}
					
				}
				
				
				int greatest = temp.values().stream()
				.max((o1,o2) -> {
				
						if(o1.y+o1.x == o2.y+o2.x) {
							if(o1.y==o2.y) {
								if(o1.x==o2.x) {
									return Integer.compare(o1.pid, o2.pid);
								}
								return Integer.compare(o1.x, o2.x);
							}
							return Integer.compare(o1.y,o2.y);
						}
						return Integer.compare(o1.y+o1.x, o2.y+o2.x);
					
				})
				.get().pid;
				
//				System.out.println("greatest"+greatest);
				score.replace(greatest, score.get(greatest)+S);
				
				
			}
			else if(operator.equals("300")) {
				
				int pid = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				
				moveSpeed.replace(pid,moveSpeed.get(pid)*L);
				
			}
			
			else if(operator.equals("100")) {
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());
				
				for(int p = 0;p<P;p++) {
					int pid = Integer.parseInt(st.nextToken());
					int d = Integer.parseInt(st.nextToken());
					score.put(pid, 0L);
					moveSpeed.put(pid, d);
					pq.add(new Rabbit(0,0,pid,0));
				}
				
			}
//			System.out.println(score);
			
			
			
			
		}
		System.out.println(score.values().stream()
					.max(Long::compare).get()+baseSum);
	}

}
