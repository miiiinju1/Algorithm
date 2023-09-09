import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[] kettle;
    public static int N,K;
    public static boolean check(int mid)
    {
        int sum = 0;  //사람 수
        for(int i =0;i<N;i++)
        {
            sum+=kettle[i]/mid;
        }
        if(sum>=K) //되면, 조금 더 늘려봐야하니깐, i = mid+1;
            return true;
        else //안 되면 범위 더 내리게 j = mid;
            return false;
    }

    public static int binarySearch(int i, int j)
    {
        while(i<=j) {
            int mid = i + (j - i) / 2;

            if (check(mid)) {
                i=mid+1;
            }
            else
                j=mid-1;
        }
        return j;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kettle = new int[N];
        int max = 0;
        for(int i =0;i<N;i++)
        {
            kettle[i] = Integer.parseInt(br.readLine());
            if(kettle[i]>max)
                max = kettle[i];
        }
        if(K ==1)
            System.out.println(max);
        else
            System.out.println(binarySearch(0,max));



    }
}