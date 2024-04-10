import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution {
	
	static class Association {
		public Association(int y, int x, int count, int d) {
			this.y = y;
			this.x = x;
			this.count = count;
			this.d = d;
		}

		int y, x, count, d;
	}
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,-1,0,1};

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			
			
			
			
			Queue<Association> q = new ArrayDeque<>();
			
			for(int i= 0;i<K;i++) {
				st = new StringTokenizer(br.readLine());
				
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				//상 하 좌 우
				
				if(d == 1) {
					d = 0;
				}
				else if(d==2) {
					d = 2;
				}
				else if(d==3) {
					d = 1;
				}
				else if(d==4) {
					d = 3;
				}
				
				
				q.add(new Association(y,x,count,d));
			}
			
			
			
			
			while(M>0) {
			List<Association> result = new ArrayList<>();
				while(!q.isEmpty()) {
					
					Association now = q.poll();
					
					int y = dy[now.d]+ now.y;
					int x = dx[now.d]+ now.x;
					
					if(y>=0&&x>=0&&y<N&&x<N) {
						if(!isHalfLife(y,x,N)) {
							result.add(new Association(y,x,now.count, now.d));
						}
						else {
							now.d=(now.d+2)%4;
							result.add(new Association(y,x,now.count/2, now.d));
						}
						
					}
					
					
					
				}
				
				
				Map<String, List<Association>> map = result.stream()
							.collect(Collectors.groupingBy(a -> a.y+" "+a.x));
				
				
				for(Map.Entry<String, List<Association>> entry : map.entrySet()) {
					
					// 한 개면 그냥넣기   
					if(entry.getValue().size()==1) {
						q.addAll(entry.getValue());
					}
					//여러 개면 합쳐서 넣
					
					else {
						
						int max = 0;
						int maxD = 0;
						int sum = 0;
						List<Association> temp = entry.getValue();
						int y = temp.get(0).y;
						int x = temp.get(0).x;
						
						for(Association a : temp) {
							if(a.count > max) {
								max = a.count;
								maxD = a.d;
								
							}
							
							sum+=a.count;
							
						}
						
						q.add(new Association(y,x,sum,maxD));
						
						
					}
					
					
				}
				
				--M;
			
			}
			
			
			int sum = q.stream().mapToInt(a -> a.count).sum();
			
			
			
			
			bw.write("#"+tc+" "+sum+"\n");
			
			
			
			
			
			
			
			
			
			
			
		}
		bw.flush();
		bw.close();

	}
	
	public static boolean isHalfLife(int y, int x, int N) {
		
		return y==0||x==0||y==N-1||x==N-1;
		
	}

}
