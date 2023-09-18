import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int X = Integer.parseInt(br.readLine().replace(" ", ""));
            int Y = Integer.parseInt(br.readLine().replace(" ", ""));

            StringBuilder sb = new StringBuilder(br.readLine().replace(" ",""));
            int count = 0;

            for(int i= 0;i<N;i++)
            {
                int temp =Integer.parseInt(sb.substring(0,M));
                if(temp>=X&&temp<=Y)
                    count++;
                sb.append(sb.charAt(0));
                sb.deleteCharAt(0);
            }
            System.out.println(count);


        }

    }
}
