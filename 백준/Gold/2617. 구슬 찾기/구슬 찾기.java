import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        for (int m = 0; m < M; m++) {
            st =  new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map[A][B] = 1;
            map[B][A] = -1;
        }
        int half = (N-1)/2;
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {

                    if(map[i][k]!=0&&map[i][k]==map[k][j]) {
                        map[i][j] = map[i][k];
                    }
                }
            }
        }
        int[] big = new int[N+1];
        int[] small = new int[N+1];
        for (int i = 1; i <= N; i++ ) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j] == 1)
                    big[i]++;

                if (map[i][j] == -1)
                    small[i]++;

            }
        }
        int answer=0;
        for (int i = 1; i <= N; i++) {
            if (big[i] > half) {
                answer++;
            }
            if (small[i] > half) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}


