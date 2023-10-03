import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
        {
            map[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp =new int[N];
        
        dp[N-1]=1;
        for(int i=N-2;i>=0;i--)
        {
            if(i+map[i]+1>=N)
                dp[i]=1;
            else
            {
                dp[i] = dp[i+map[i]+1]+1;
            }
        }
        for(int i=0;i<N;i++)
            System.out.print(dp[i]+" ");
    }
}
