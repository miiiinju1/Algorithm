
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static ArrayList<String> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> count = new HashMap<>();

        int max = 0;
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            max = Math.max(num, max);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        result = count.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(entry -> IntStream.range(0, entry.getValue())
                        .mapToObj(i -> entry.getKey().toString()))
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = result.size(); i < N; i++) {
            result.add(String.valueOf(max));
        }

        Collections.sort(result, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        for(int i =0;i<result.size();i++) {
            bw.write(result.get(i) );
        }
        bw.flush();bw.close();
    }
}
