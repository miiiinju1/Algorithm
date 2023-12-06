import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken().replace(".", ""));
            if(n==0) {
                break;
            }
            int[] dp = new int[m+1];
            HashMap<Integer,Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken().replace(".", ""));

                if (map.containsKey(p)) {
                    if(map.get(p)<c) {
                        map.replace(p, c);
                    }
                }
                else {
                    map.put(p, c);
                }
            }
            List<Map.Entry<Integer, Integer>> list = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toList());

            for(int i=0;i<=m;i++) {
                for (Map.Entry<Integer, Integer> entry : list) {
                    if(entry.getKey()>i)
                        break;

                    if (dp[i] < dp[i - entry.getKey()] + entry.getValue()) {
                        dp[i] = dp[i - entry.getKey()] + entry.getValue();
                    }
                }
            }

            bw.write(Arrays.stream(dp).max().getAsInt() + "\n");




        }
        bw.flush();bw.close();
    }
}
