import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			TreeSet<Integer> set = new TreeSet<>(Comparator.reverseOrder());
			StringBuilder sb = new StringBuilder(br.readLine());

			int length = N/4;
			//그냥 
			for(int i= 0;i<sb.length();i+=length) {
				set.add(Integer.parseInt(sb.substring(i,i+length),16));
			}
			
			// 회전 후 
			for(int k = 0;k<length;k++) {
				char c = sb.charAt(0);
				sb.deleteCharAt(0);
				sb.append(c);
				for(int i= 0;i<sb.length();i+=length) {
					set.add(Integer.parseInt(sb.substring(i,i+length),16));
				}
			}
			
			int k = 1;
			for(Integer i : set) {
				if(k==K) {
					bw.write("#"+tc+" "+i+"\n");
					break;
				}
				++k;
			}
			
		}
		bw.flush();
		bw.close();
		
		
		
	}

}
