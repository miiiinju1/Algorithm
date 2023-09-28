
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int distance(int before_x, int before_y, int x, int y)
    {
        return Math.abs(before_x-x)+Math.abs(before_y-y);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] map = new int[N+1][2];
        for(int i=1;i<=N;i++)
        {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
        }

        int max=0;
        for(int i=2;i<N;i++)
        {
            int before =distance(map[i - 1][0], map[i - 1][1], map[i][0], map[i][1]);
            int after=distance(map[i][0], map[i][1],map[i+1][0], map[i+1][1]);
            int jump = distance(map[i-1][0],map[i-1][1],
                    map[i+1][0],map[i+1][1]);
            if(before+after-jump>max)
            {
                max = before+after-jump;
            }
        }
        long sum=0;
        for(int i=2;i<=N;i++)
        {
            sum += distance(map[i - 1][0], map[i - 1][1], map[i][0], map[i][1]);
        }

        System.out.println(sum - max);

    }
}
