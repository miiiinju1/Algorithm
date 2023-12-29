



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;





class Case implements Comparable<Case>{
	
	Integer src, dest,size;
	
	
	public Case(Integer src,Integer dest, Integer size) {
		this.src = src;
		this.dest = dest;
		this.size = size;
	}

	@Override
	public int compareTo(Case o) {
		if (this.dest.compareTo(o.dest) < 0)
			return -1;
		else if (this.dest.compareTo(o.dest) > 0)
			return 1;
		else if (this.src.compareTo(o.src) < 0)
			return -1;
		else if (this.src.compareTo(o.src) > 0)
			return 1;
		return 0;
	}


	
	
}


class FreeSpaceCal{
	ArrayList<Integer> freeSpace;
	
	public FreeSpaceCal(int N,int C)
	{
		freeSpace = new ArrayList<Integer>();
		for(int i = 0;i<N;i++)
			freeSpace.add(C);
	}
	public int minCal( Case x)
	{
		
		return Cal(x.src-1,x.dest-1,x.size);
	}
	
	private int Cal(int src, int dest, int c)
	{
		int min = 999999;
		for(int i = src; i<dest;i++)
		{
			if(min>freeSpace.get(i))
				min = freeSpace.get(i);
			
		}
		
		
		
		return Cal(src,dest,min,c);
		
		
	}
	private int Cal(int src, int dest,int min, int c)
	{
		if(min>c)
		{
			for(int i = src;i<dest;i++)
			{
				freeSpace.set(i, freeSpace.get(i)-c);
			}
			return c;
		}
		else
		{
			for(int i = src;i<dest;i++)
			{
				freeSpace.set(i, freeSpace.get(i)-min);
			}
			return min;
		}
			
	}
	
}



public class Main {

	public static void main(String[] args) throws IOException {
		int N, C;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int src, dest, size, M;
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st =new StringTokenizer(br.readLine());
		M =	Integer.parseInt(st.nextToken());
		ArrayList<Case> repository = new ArrayList<Case>();
		
		for(int i=0;i<M;i++){
			st = new StringTokenizer(br.readLine());
			src = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());
			repository.add(new Case(src,dest,size));
			
			
		}
		int answer = 0;
		
		Collections.sort(repository);
		//여기까지는 맞고
		FreeSpaceCal truck = new FreeSpaceCal(N,C);
		for(int i = 0;i<M;i++)
		{
			
			answer+=truck.minCal(repository.get(i));
		}
			
			
		
		System.out.print(answer);
		
	}

}
