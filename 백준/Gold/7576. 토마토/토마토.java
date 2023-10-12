
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		
		int y,x,count;
		public Point(int y, int x,int count)
		{
			this.y=y;
			this.x=x;
			this.count = count;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = Integer.parseInt(st.nextToken());
		
		int y = Integer.parseInt(st.nextToken());
		boolean[][] visited = new boolean[y][x];
		int max = x*y;
		int now=0;
		Queue<Point> queue = new LinkedList<Point>();
		for(int i = 0;i<y;i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j<x;j++)
			{
				int temp = Integer.parseInt(st.nextToken());
				
				if(temp==-1)
				{
					max--;
					visited[i][j]=true;
				}
				else if(temp == 1)
				{
					now++;
					visited[i][j]=true;
					queue.add(new Point(i,j,0));
					
				}
				
				
				
			}
		}
		if(now==max) {
			System.out.println(0);
			return ;
		}
		int[] dy = {0,1,0,-1};
		int[] dx = {1,0,-1,0};
		
		while(!queue.isEmpty())
		{
			Point temp = queue.poll();
			for(int i = 0;i<4;i++)
			{
				int Y = temp.y+dy[i];
				int X = temp.x+dx[i];
				if(Y>=0&&X>=0&&Y<y&&X<x)
				{
					if(!visited[Y][X])
					{
						now++;
						if(now==max)
						{
							System.out.println(temp.count+1);
							return;
						}
						queue.add(new Point(Y,X,temp.count+1));
						visited[Y][X]=true;
						
						
					}
					
					
					
					
				}
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
		}
		System.out.println(-1);
	}

}
