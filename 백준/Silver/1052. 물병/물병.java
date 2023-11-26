import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int result = 0;
        int count = Integer.bitCount(N+result);
        while(count>K) {
            count = Integer.bitCount(N+result);
            if(count<=K)
                break;
            result++;
        }
        System.out.println(result);

    }
}
//1 1 1 1 1 1 1 1 1 1 1 1 1 1
// 2 2 2 2 2 2 /1
// 4 4 4 /1
// 8 /4 1
// 8 /4 1

// 8 /4 1 1
// 4 2
// 4 2 2
// 4 4
// 8


//3



// 8 4
// 8 4 4
