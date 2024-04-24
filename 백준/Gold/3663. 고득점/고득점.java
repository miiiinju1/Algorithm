import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] ary = new int[26];
        int i = 0;
        for(;i<14;i++) ary[i] = i;
        for(;i<26;i++) ary[i] = 26-i;
        for(int tc = 0;tc<T;tc++) {
            final String str = br.readLine();
            int sum = 0;
            for(int s = 0;s<str.length();s++) {
                sum+= ary[str.charAt(s)-'A'];
            }
            //좌우로 움직여서 최소를 찾아내는 방법
            sum += shortest(str);

            bw.write(sum + "\n" );
        }
        bw.flush();bw.close();

    }
    private static int shortest(String str) {

        int sum = str.length() - 1;

        for(int j = str.length()-1;j>0;j--) {
            if(str.charAt(j)!='A')
                break;
            sum--;
        }
        // i는 최대한 오른쪽까지 한 번 가는 위치
        for(int i= 0;i<str.length();i++) {
            for(int j = i+1;j<str.length();j++) {
                if(str.charAt(j) != 'A') {
                    sum = Math.min(sum, i * 2 + str.length()  - j);
                    break;
                }
            }
        }
        for(int i = str.length()-1;i>=0;i--) {
            for(int j = i - 1;j>=0;j--) {
                if(str.charAt(j) != 'A') {
                    sum = Math.min(sum, (str.length()-i) * 2 +j);
                    break;
                }
            }
        }
        return sum;


    }
}



