
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        String[] strArray = new String[R];
        for(int i =0;i<R;i++) {
            strArray[i] = br.readLine();
        }
        HashSet<String> set = new HashSet<>();

        int i= 0;
        for(;i<C;i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < R; j++) {
                sb.append(strArray[j].charAt(i));
            }
            set.add(sb.toString());
        }

        for(i=1;i<C;i++) {
            String[] array = set.toArray(new String[0]);
            for (int j =0;j<array.length;j++) {

                if (!set.add(array[j].substring(1))) {
                    System.out.println(i - 1);
                    return;
                }
                set.remove(array[j]);
            }
        }
        System.out.println(i-1);



    }
}
