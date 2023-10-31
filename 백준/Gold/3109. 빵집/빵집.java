
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static boolean[][] visited = null;
	public static int C;
	public static int count=0;
	public static boolean Next(int nowY, int nowX)
	{
		if(nowX == C-1&&visited[nowY][C-1])
			{
				visited[nowY][C-1]=false;
				count++;
				return true;
			}
		/*if(!visited[nowY][nowX]) {
			
			return false;
		}*/
		if(visited[nowY-1][nowX+1]&&Next(nowY-1,nowX+1)) {
			visited[nowY-1][nowX+1]=false;
			return true;
			}
		
		
		else if(visited[nowY][nowX+1]&&Next(nowY,nowX+1)) {
			visited[nowY][nowX+1]=false;
			return true;
		}
		else if(visited[nowY+1][nowX+1]&&Next(nowY+1,nowX+1)) {
			visited[nowY+1][nowX+1]=false;
			return true;
		}
		visited[nowY][nowX]=false;
		return false;
		
	
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R+2][C];
		//갈 수 있는 길을 true로 하고
		//못 가는 길을 false로 하자
		
		
		
		for(int i = 1;i<=R;i++)
		{
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			for(int j = 0;j<C;j++)
			{
				if(temp.charAt(j)=='.')
					visited[i][j] = true;
				else
					visited[i][j] = false;
			}
		}
		
		for(int i = 1;i<=R;i++)
			Next(i,0);
		
		System.out.println(count);
		
		
		
		}	

}
