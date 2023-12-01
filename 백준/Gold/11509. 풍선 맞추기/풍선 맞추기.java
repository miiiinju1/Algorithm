import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int now =Integer.parseInt(st.nextToken());
            int next = now -1;
            if(map.containsKey(now)) {
                if (map.get(now) == 1) {
                    map.remove(now);
                } else {
                    map.replace(now, map.get(now) - 1);
                }
            }
            map.put(next, map.getOrDefault(next, 0) + 1);
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sum += entry.getValue();
        }
        System.out.println(sum);
    }
}
