import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        ArrayList<Integer> small = new ArrayList<>();
        ArrayList<Integer> large = new ArrayList<>();
        for(int i= 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("3")) {
                small.add(Integer.parseInt(st.nextToken()));
            }
            else {
                large.add(Integer.parseInt(st.nextToken()));
            }
        }
        Collections.sort(small, Collections.reverseOrder());
        Collections.sort(large, Collections.reverseOrder());

        long[] smallAry = new long[small.size()+1];
        long[] largeAry = new long[large.size()+1];

        for(int i = 1;i<=small.size();i++) {
            smallAry[i] = small.get(i - 1) + smallAry[i - 1];
        }
        for (int i = 1; i <=large.size(); i++) {
            largeAry[i] = large.get(i - 1) + largeAry[i - 1];
        }

        int smallIndex = Math.min(small.size(), w / 3);
        long max = smallAry[smallIndex];
//        for (long l : smallAry) {
//            System.out.print(l+" ");
//        }
//        System.out.println();

        for(int i = smallIndex;i>=0;i--) {
            max = Math.max(max, smallAry[i] + largeAry[Math.min(large.size(), ((w - i * 3) / 5 ))]);

        }
        System.out.println(max);






    }
}
