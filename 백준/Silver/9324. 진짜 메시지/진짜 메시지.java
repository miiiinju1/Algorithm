
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        a:for (int tc = 0; tc < T; tc++) {
            char[] str = br.readLine().toCharArray();
            HashMap<Character,Integer> map = new HashMap<>();

            for(int i= 0;i<str.length;i++) {
                char c = str[i];
                if (!map.containsKey(c)) {
                    map.put(c, 1);
                } else {
                    if(map.get(c)==2) {
                        map.replace(c, 3);
                        if(i+1<str.length&&c!=str[i+1]) {
                            bw.write("FAKE\n");
                            continue a;
                        }
                        else if(i+1>=str.length) {
                            bw.write("FAKE\n");
                            continue a;
                        }
                    }
                    else {
                        map.replace(c, map.get(c)+1);
                        if(map.get(c)==4) {
                            map.replace(c, 0);
                        }
                    }

                }
            }



            bw.write("OK\n");

        }
        bw.flush();bw.close();
    }

}
