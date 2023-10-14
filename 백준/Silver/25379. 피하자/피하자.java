
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
static int[] ary;
static int N;
    //홀수 왼쪽 짝수 오른쪽
    static long oddEven()
    {
        long count = 0;
        int left = 0;
        for(int i=0;i<N;i++)
        {
            if(ary[i]%2!=0)

                count+= i-(left++);

        }

        return count;
    }

    //짝수 왼쪽 홀수 오른쪽

    static long evenOdd(){
        long count = 0;
        int left = 0;
        for(int i=0;i<N;i++)
        {
            if(ary[i]%2==0)
                count+= i-(left++);
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ary = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++)
            ary[i] = Integer.parseInt(st.nextToken());


        System.out.println(Math.min(oddEven(), evenOdd()));

    }
}


// 1 3 5 7 2 4 6 8
//홀 4 짝 2

// 2 + 2

//2 4 1 3 5 6이거

//2 1 4 3 5 6
//4



//
//