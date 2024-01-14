
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            sum += (Integer.parseInt(st.nextToken())+ 1);
        }

        int blank = Math.max(0, M - sum);

        N+=1;
        long result = 0;
        int i = 1;
        while(blank>=0) {
            if(blank-N>0) {
                blank-=N;
                result += (long) (N * Math.pow(i++, 2));
            }
            else {
                break;
            }
        }
        result += (blank * Math.pow(i, 2));
        System.out.println(result);

    }
}

//-1 2 1 0 -1 2 1 0 -1 1 0 -1 -2
//1 1 1 1 4 = 