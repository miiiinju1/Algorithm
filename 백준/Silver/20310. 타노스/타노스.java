import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] ary = br.readLine().toCharArray();
        int N = ary.length;
        int zeroCount = 0;
        int oneCount = 0;
        for (char c : ary) {
            if(c=='0'){
                zeroCount++;
            }
            else if(c=='1') {
                oneCount++;
            }
        }
        oneCount/=2;
        zeroCount/=2;
        int count = 0;
        for(int i = 0;i<N;i++) {
            if(ary[i]=='1') {
                count++;
                ary[i] = '#';
            }
            if(count==oneCount) {
                break;
            }
        }
        count = 0;
        for(int i = N-1;i>=0;i--) {
            if(ary[i] =='0') {
                count++;
                ary[i] = '#';
            }
             if(count==zeroCount) {
                 break;
              }
        }

        StringBuilder sb = new StringBuilder();
        for(int i= 0;i<N;i++) {
            if(ary[i]!='#') {
                sb.append(ary[i]);
            }
        }
        System.out.println(sb);




    }
}
