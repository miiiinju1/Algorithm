
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		Integer[] stair = new Integer[n+1];
		for(int i =1;i<=n;i++)
		{
			st = new StringTokenizer(br.readLine());
			stair[i] = Integer.parseInt(st.nextToken());
		}
		int count = 0;
		Integer[] d =new Integer[n+1];
		Arrays.fill(d, 0);
		d[1] = stair[1];
		
		if(n>1)
		{
			d[2]=stair[1]+stair[2];
		for(int i = 3;i<=n;i++)
			d[i] = Math.max(d[i-3]+stair[i-1]+stair[i], d[i-2]+stair[i]);
		}
		
		System.out.println(d[n]);
		
		
	}

}
