import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Puzzle{
		int y,x,count;
		int[][] map = new int[3][3];
		public Puzzle() {
				
		}
		public Puzzle(Puzzle p, int y, int x) {
			int temp = p.map[y][x];
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
				{
					map[i][j]=p.map[i][j];
				}
			this.map[y][x] = 0;
			this.map[p.y][p.x]=temp;
			this.y=y;
			this.x=x;
			this.count = p.count+1;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(map);
			return result;
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
			Puzzle other = (Puzzle) obj;
			return Arrays.deepEquals(map, other.map);
		}
		
		
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Puzzle answer = new Puzzle();
		answer.map[0]= new int[] {1,2,3};
		answer.map[1]= new int[] {4,5,6};
		answer.map[2]= new int[] {7,8,0};

		StringTokenizer st= null; 
		Puzzle start = new Puzzle();
		//0좌표 설정해주기
		
		for(int i=0;i<3;i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++)
			{
				int temp = Integer.parseInt(st.nextToken());
				if(temp==0) {
					start.y=i;
					start.x=j;
				}
				start.map[i][j] = temp;
			}
		}
		if(start.equals(answer))
		{
			System.out.println(0);
			return;
		}
		Queue<Puzzle> queue = new LinkedList<Puzzle>();
		HashSet<Puzzle> visited = new HashSet<Puzzle>();
		visited.add(start);
		queue.add(start);
		
		
		int[] dy = {1,0,0,-1};
		int[] dx = {0,1,-1,0};
		
		while(!queue.isEmpty())
		{
			Puzzle temp = queue.poll();
			
			for(int i= 0;i<4;i++)
			{
				int y=temp.y+dy[i];
				int x = temp.x+dx[i];
				if(y>=0&&y<3&&x>=0&&x<3)
				{
					Puzzle new_puzzle = new Puzzle(temp,y,x);
					if(answer.equals(new_puzzle))
					{
						System.out.println(new_puzzle.count);
						return;
					}
					if(!visited.contains(new_puzzle))
					{
						visited.add(new_puzzle);
						queue.add(new_puzzle);
					}
					
				}
				
				
				
				
			}
			
			
			
		}
		System.out.println(-1);
	}

}
