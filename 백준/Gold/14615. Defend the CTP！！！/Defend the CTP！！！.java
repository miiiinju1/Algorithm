
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static Map<Integer, List<Integer>> map = new HashMap<>();
    static Map<Integer, List<Integer>> reverseMap = new HashMap<>();

    static boolean[] distance, reverseDistance;

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; ++i) {
            map.put(i, new ArrayList<>());
            reverseMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            map.get(X).add(Y);
            reverseMap.get(Y).add(X);
        }

        distance = new boolean[N + 1];
        reverseDistance = new boolean[N + 1];

        search();


        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; ++i) {
            int point = Integer.parseInt(br.readLine());

            if (distance[point] && reverseDistance[point]) {
                bw.write("Defend the CTP\n");

            } else {
                bw.write("Destroyed the CTP\n");

            }

        }
        bw.flush();bw.close();

    }

    static void search() {
        distance[1] = true;

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);

        while(!q.isEmpty()) {
            Integer now = q.poll();

            for (Integer next : map.get(now)) {
                if (!distance[next]) {
                    distance[next] = true;
                    q.add(next);
                }
            }
        }
        q = new ArrayDeque<>();
        reverseDistance[N] = true;
        q.add(N);
        while(!q.isEmpty()) {
            Integer now = q.poll();
            for (Integer next : reverseMap.get(now)) {
                if (!reverseDistance[next]) {
                    reverseDistance[next] = true;
                    q.add(next);
                }
            }
        }


    }
}
