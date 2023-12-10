import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        ary = Arrays.stream(ary).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();

        PriorityQueue <Integer> socket = new PriorityQueue<>();

        for (int i : ary) {
            if(socket.size()==M) {
                socket.add(socket.poll() + i);
            }
            else {
                socket.add(i);
            }
        }

        System.out.println(socket.stream().max(Comparator.naturalOrder()).get());





    }
}
