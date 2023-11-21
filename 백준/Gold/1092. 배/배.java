import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> window = null;
	static ArrayList<Integer> ary = null;

	public static void main(String[] args) throws IOException {
		int N, M;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		window = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			window.add(Integer.parseInt(st.nextToken()));

		M = Integer.parseInt(br.readLine());
		ary = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			ary.add(Integer.parseInt(st.nextToken()));
		Collections.sort(window);
		Collections.reverse(window);
		Collections.sort(ary);
		Collections.reverse(ary);
		int ans = -1;
		int j =0;
		int loop = ary.size();
		if(window.get(0)>=ary.get(0))
		while (!ary.isEmpty()) {
			
			for (int i = 0; i < window.size();) {
				if(ary.size()==0)
					break;
				
				for (j = 0; j < loop;loop=ary.size()) {
					if (window.get(i) >= ary.get(j)) {
						ary.remove(j);
						j = 0;
						i++;
					}
					else {
						j++;
					}
					if(i==window.size())
						break;
				}
				if(j==loop)
					break;

			}
			if(j<ary.size()&&window.get(0)<ary.get(j)) {
				ans--;
				break;
			}
			ans++;
		}
		else
			ans--;

		System.out.println(ans + 1);

	}

}
