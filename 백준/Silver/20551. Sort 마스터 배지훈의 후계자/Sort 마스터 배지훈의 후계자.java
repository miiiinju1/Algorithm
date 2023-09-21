import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] ary;
    static int binarySearch(int i, int j,int ans)
    {
        while(i+1<j)
        {
            int mid = i + (j - i) / 2;
            if(ary[mid]<ans)
                i=mid;
            else
                j=mid;
        }
        if(ary[j]==ans)
          return j;
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ary = new int[N];
        for(int i=0;i<N;i++)
        {
            ary[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(ary);
        for(int i=0;i<M;i++)
            System.out.println(binarySearch(-1,N-1,Integer.parseInt(br.readLine())));

    }
}
