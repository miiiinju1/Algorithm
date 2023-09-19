
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		}
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int nowLocation = 0, nowOil = P;

		ArrayList<Integer> set = new ArrayList<Integer>(map.keySet());
		// for(Integer z : set)
		// System.out.println(z);
		int count = 0;
		while (true) {
			
			int max = 0;
			int canGo = 0;
			int locate = 0;
			int maxlocate = 0;
			if (nowLocation+nowOil >= L) {
				System.out.println(count);
				return;
			}
			if (set.isEmpty()) {
				System.out.println(-1);
				return;
			}
			for (int i = 0; i < set.size(); i++) {
				canGo = nowLocation + nowOil;
				
				locate = set.get(i);
				if (locate <= canGo) {
					
					int oil = map.get(locate);
					/*
					if (max < nowOil - (locate - nowLocation) + oil) {
						max = nowOil - (locate - nowLocation) + oil;
						maxlocate = locate;
					}
					*/
					if(max<oil)
					{
						max = oil;
						maxlocate =locate;
					}
				}
				
				else
					break;
				
			} 
				if(nowLocation<maxlocate)
					{	
					nowOil = nowOil-(maxlocate-nowLocation)+max;
					nowLocation = maxlocate;
					}
				else
				{
					nowOil +=max;
				}
				if(set.contains(maxlocate))
					set.remove(set.indexOf(maxlocate));
				else
				{
					System.out.println(-1);
					return;
				}
				
				count++;
			
		}
	}
}


