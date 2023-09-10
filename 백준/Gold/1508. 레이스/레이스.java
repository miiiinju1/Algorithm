import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static Integer[] map = null;
	public static int K;
	public static int M;
	public static int Count(int distance)
	{
		int prev = map[0];
		int count=1;
		for(int i =1;i<K;i++)
			{
				if(prev+distance<=map[i])
				{
					prev = map[i];
					count++;
				}
			
			}
		return count;
	}
	public static void count_print(int distance)
	{
		int prev = map[0];
		System.out.print(1);
		int count =1;
		for(int i =1;i<K;i++)
			{
				if(count<M&&prev+distance<=map[i])
				{
					prev = map[i];
					System.out.print(1);
					count++;
				}
				else
					System.out.print(0);
			
			}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Integer[K];
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i<K;i++)
			map[i] = Integer.parseInt(st.nextToken());
		
		int lo = 1,hi = map[K-1]-map[0]+1;
		int mid=0;
		while(lo<hi)
		{
			mid = lo/2+hi/2;
			
			if(Count(mid)<M)
				hi= mid;
			else
				lo = mid+1;
			
				
		}
		count_print(lo-1);
		
	}

}
