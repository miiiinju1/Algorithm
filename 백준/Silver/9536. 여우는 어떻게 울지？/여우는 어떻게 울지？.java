import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0;tc<T;tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            HashSet<String> set = new HashSet<>();
            while(true) {
                String input = br.readLine();
                if (input.equals("what does the fox say?")) {
                    break;
                }
                String[] animals = input.split(" ");
                set.add(animals[2]);
            }

            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if(!set.contains(token)) {
                 bw.write(token+" ");
                }
            }
            bw.write("\n");

        }
bw.flush();bw.close();

    }
}
