
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int count = 0;
    static void search(int y, int x, int index, char[] str) {
        if(index == str.length) {
            ++count;
            return;
        }
        for (int d = 0; d < 8; ++d) {
            int Y = dy[d] + y;
            int X = dx[d] + x;

            if (Y >= N) {
                Y = 0;
            } else if(Y<0) {
                Y = N-1;
            }
            if (X >= M) {
                X = 0;
            } else if (X < 0) {
                X = M-1;
            }
            if (str[index] == map[Y][X]) {
                search(Y, X, index + 1, str);
            }
        }
    }
    static char[][] map;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> cache = new HashMap<>();
        int K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i = 0;i<N;++i) {
            String str = br.readLine();

            for (int j = 0; j < M; ++j) {
                map[i][j] = str.charAt(j);
            }
        }

        while (--K >= 0) {
            String str = br.readLine();

            if(cache.containsKey(str)) {
                bw.write(cache.get(str)+"\n");
                continue;
            }
            char[] strArray = str.toCharArray();

            for(int i =0;i<N;++i) {
                for (int j = 0; j < M; ++j) {
                    if(strArray[0] == map[i][j]) {
                        search(i, j, 1, strArray);
                    }
                }
            }
            
            cache.put(str, count);
            bw.write(count + "\n");
            count = 0;

        }
        bw.flush();bw.close();
    }

}
