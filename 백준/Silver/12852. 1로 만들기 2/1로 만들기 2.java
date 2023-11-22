
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1로 만들기
//dp
public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		Integer[] d = new Integer[N + 1];
		Integer[] before = new Integer[N + 1];

		Queue<Integer> buf = new LinkedList<Integer>();
		Arrays.fill(d, -1);
		d[N] = 0;
		before[N] = -1;
		buf.add(N);
		while (true) {
			int now = buf.poll();
			if (now % 3 == 0) {
				if (d[now / 3] == -1) {
					d[now / 3] = d[now] + 1;
					before[now / 3] = now;
					buf.add(now/3);
				}
			}
			if (now % 2 == 0) {
				if (d[now / 2] == -1) {
					d[now / 2] = d[now] + 1;
					before[now / 2] = now;
					buf.add(now/2);
				}
			}
			
				if (d[now - 1] == -1) {
					d[now - 1] = d[now] + 1;
					before[now - 1] = now;
					buf.add(now-1);
				}

			
			if (d[1] != -1)
				break;

		}
		System.out.println(d[1]);
		ArrayList<Integer> result = new ArrayList<Integer>();
		int next=1;
		result.add(1);
		while(true) {
			
			next = before[next];
			if(next==-1)
				break;
		result.add(0,next);
		
		}		
		int size = result.size();
		for(int i =0;i<size;i++)
		{
			System.out.print(result.get(i)+" ");
		}
	
	}

}
