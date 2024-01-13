import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ary);

        int middleN = (N + 1) / 2;

        long sum = 0;
        for (int i = 0; i < middleN; i++) {
            sum += (Math.log(ary[i]) / Math.log(2));
        }
        System.out.println(sum + 1);
    }
}

//3 5 7 9
//2 3 7 9
//1 2 7 9
//1 1 7 9
//1 0 7 9

//5 2 1 0
//3 1 0