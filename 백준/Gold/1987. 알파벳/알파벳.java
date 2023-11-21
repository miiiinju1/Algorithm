import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int[][] map = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int R,C;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int ans=0;
	static HashSet<Integer> visit = new HashSet<Integer>();
	public static boolean compare(int x, int y)
	{
		if(x>=0 && x<R&&y>=0&&y<C)
			return true;
		
		return false;
		
	}
	public static void dfs(int x, int y, int count)
	{
		if(visit.contains(map[x][y]))
		{
			ans=Math.max(ans,count);
			return;
			
		}
		else {
			visit.add(map[x][y]);
			for(int i = 0;i<4;i++) {
				int cx = x+dx[i];
			int cy = y+dy[i];
			if(compare(cx,cy))
				dfs(cx,cy,count+1);
			}
		}
		visit.remove(map[x][y]);
		
	}
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
        if(R==1&&C==1) {
            System.out.println(1);
            return ;
        }
		for (int i = 0; i < R ; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j =0;j<C;j++)
			{
				map[i][j]=str.charAt(j)-'A';
			}
			
		}
		dfs(0,0,0);
		System.out.println(ans);

	}

}
