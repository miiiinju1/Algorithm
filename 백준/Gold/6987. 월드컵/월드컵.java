import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] game;
    static int[][] map;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

 static boolean flag;
    static void dfs(int phase)  {
        if(phase==15) {
            flag = true;
            return ;
        }
        int a = game[phase][0];
        int b = game[phase][1];
        if(map[a][0]>0 && map[b][2]>0) {
            map[a][0]--;
            map[b][2]--;
            dfs(phase + 1);
            map[a][0]++;
            map[b][2]++;
        }
        if(map[a][1]>0 && map[b][1]>0) {
            map[a][1]--;
            map[b][1]--;
            dfs(phase + 1);
            map[a][1]++;
            map[b][1]++;
        }

        if(map[a][2]>0 && map[b][0]>0) {
            map[a][2]--;
            map[b][0]--;
            dfs(phase + 1);
            map[a][2]++;
            map[b][0]++;
        }
    }

        public static void main (String[]args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            a:for (int tc = 0; tc < 4; tc++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                map = new int[6][3];
                game = new int[15][2];
                int index = 0;
                for (int i = 0; i < 6; i++) {
                    for (int j = i + 1; j < 6; j++) {
                        game[index][0] = i;
                        game[index++][1] = j;
                    }
                }
                for (int i = 0; i < 6; i++) {
                    int temp = 0;
                    for (int j = 0; j < 3; j++) {
                        map[i][j] = Integer.parseInt(st.nextToken());
                        temp += map[i][j];
                    }
                    if (temp != 5) {
                        bw.write("0 ");
                        continue a;
                    }
                }
                dfs(0);
                if (flag) {
                    bw.write("1 ");
                }
                else {
                    bw.write("0 ");

                }
                flag = false;


            }

        bw.flush();
        bw.close();
    }
}
