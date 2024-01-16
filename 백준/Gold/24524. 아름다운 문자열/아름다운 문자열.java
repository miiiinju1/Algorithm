import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] S;

    static  char[] T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        T = br.readLine().toCharArray();


        int[] ary = new int[T.length+1];
        ary[0] = Integer.MAX_VALUE;

        for(int i= 0;i<S.length;i++) {
            for(int t=0;t<T.length;t++) {
                if(S[i]==T[t]) {
                    int index = t+1;
                    if(ary[t]>ary[index]) {
                        ary[index]++;
                    }
                    break;
                }
            }
        }

        System.out.println(ary[T.length]);


    }
}
