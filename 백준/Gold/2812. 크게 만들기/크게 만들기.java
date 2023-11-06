
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
//이거 시간초과하면 배열로 index지정해가면서 하기
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int origK = Integer.parseInt(st.nextToken());
		int K = origK;
		String temp = br.readLine();
		int strlen = temp.length();
		Integer[] map_ = new Integer[strlen];
		for(int i = 0;i<strlen;i++) {
			map_[i]=temp.charAt(i)-48;
		}
		int i = 1;
		int a=0,b=1;
		int start = 0;
		Stack<Integer> stack = new Stack<Integer>();
		while(K>0)
		{
			
			if(map_[a]<map_[b])
			{
				map_[a]=-1;
				K--;
				if(a!=start) {
				//	if(!stack.isEmpty()) {
					a= stack.pop();
					}
				else
				{
					start=b;
					a=b;
					stack.add(a);
					b++;
				}
				
			}
			else {
			stack.add(a);
			a=b;
			//prev_a=a;
			b++;
			}
			if(b==N)
			{
				map_[N-1]=-1;
				K--;
				for(int x=0;x<K;x++) {
					map_[stack.pop()]=-1;
				}
				break;
			}
		}
		/*while(K>0)
		{
			if(i==size)
			{
				for(int x=0;x<K;x++)
					map.remove(--size);
				break;
			}
			if(map.get(i-1)<map.get(i))
			{
				map.remove(i-1);
				size--;
				K--;
				if(i!=1) {
					
					i--;}
			}
			else
			i++;
		}*/
		for(int r = 0;r<N;r++)
			if(map_[r]!=-1)
			System.out.print(map_[r]);	
		
	}

}
