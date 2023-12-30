import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

int N = n+L+1;
        char[] array = new char[N];
        Arrays.fill(array, '0');

        char[] temp = br.readLine().toCharArray();

        for(int i= 0;i<temp.length;i++) {
            array[i] = temp[i];
        }

        boolean type = false;
        boolean flag = false;
        int lastUseIndex = -1;
        int start = 0;
        int flush = 0;
        for (int i = 0; i < N; i++) {
            if(!type) {
                if(array[i] == '1') {
                        start++;

                    if(start==K) {
                        type = true;
                        start = 0;
                    }
                }
                else {
                    start = 0;
                }
            }
            if(type) {
                if(array[i] =='0') {
                    flush++;
                    lastUseIndex = i;
                    if(flush==L) {
                        bw.write((i + 1) + "\n");
                        type = false;
                        flag = true;
                        flush = 0;
                    }
                }
                else {
                    flush = 0;
                }
            }

        }

//        if(type) {
//            bw.write(lastUseIndex + 2 + L + "\n");
//            flag = true;
//        }
        if(!flag) {
            bw.write("NIKAD");
        }


        bw.flush();
        bw.close();
    }
}
