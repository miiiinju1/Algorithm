
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int index = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i= 0;i<L;i++) {
            String str = br.readLine();

            if (map.containsKey(str)) {
                map.replace(str, index++);
            }
            else {
                map.put(str,index++);
            }

        }

        final List<Map.Entry<String, Integer>> list = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(K)
                .collect(Collectors.toList());
        for (Map.Entry<String, Integer> entry : list) {
            bw.write(entry.getKey()+"\n");
        }

        bw.flush(); bw.close();

    }
}
