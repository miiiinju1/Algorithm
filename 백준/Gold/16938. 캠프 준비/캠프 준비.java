import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,L,R,X, target;
    static int[] problems;
    static int[] problemSet;
    static int result = 0;

    static void combination(int index, int start) {
        if(index==target) {
            int sum = 0;
            int max = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0;i<target;i++) {
                sum+=problemSet[i];
                min = Math.min(min, problemSet[i]);
                max = Math.max(max, problemSet[i]);
            }
            if(sum>=L&&sum<=R&&max-min>=X) {
//                for(int i = 0;i<target;i++) {
//                    System.out.print(problemSet[i]+" ");
//                }
//                System.out.println();
                result++;
            }

            return ;

        }
        if(index ==0 ) {
            problemSet = new int[target];
        }

        for(int i = start;i<N;i++) {
            problemSet[index] = problems[i];
            combination(index+1,i+1);
        }

    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        problems = new int[N];
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 2;i<=N;i++) {
            target = i;
            combination(0,0);
        }


        System.out.println(result);

    }
}
