import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] game;
    static int[][] map;

    static boolean dfs(int phase) {
        if(phase==15) {
            return true;
        }

        int a = game[phase][0];
        int b = game[phase][1];

        if(map[a][0]>0 && map[b][2]>0) {
            map[a][0]--;
            map[b][2]--;
            if(dfs(phase + 1)) {
                return true;
            }
            map[a][0]++;
            map[b][2]++;
        }
        if(map[a][1]>0 && map[b][1]>0) {
            map[a][1]--;
            map[b][1]--;
            if(dfs(phase + 1)) {
                return true;
            }
            map[a][1]++;
            map[b][1]++;
        }

        if(map[a][2]>0 && map[b][0]>0) {
            map[a][2]--;
            map[b][0]--;
            if(dfs(phase + 1)) {
                return true;
            }
            map[a][2]++;
            map[b][0]++;
        }
        
        return false;
    }

        public static void main (String[]args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            a:
            for (int tc = 0; tc < 4; tc++) {
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
                if (dfs(0)) {
                    bw.write("1 ");
                    
                }
                else {
                    bw.write("0 ");
                    
                }


            }
        
        bw.flush();
        bw.close();
    }
}

//5 4 3 2 1
// 15
// 15

//또는
//총 합은 일단 30이여야함


// 0 0 2 4 3
// 1 3 2
// 0 2 2
//0 1 1
//


//0 1 2 4 0 3

//0 1 1 3 0 2

// 0 0 1 2 0 2
//5 0 0 3 0 2 2 0 3 0 0 5 4 0 1 1 0 4
//4 1 0 3 0 2 4 1 0 1 1 3 0 0 5 1 1 3
//5 0 0 4 0 1 2 2 1 2 0 3 1 0 4 0 0 5
//5 0 0 3 1 1 2 1 2 2 1 3 0 0 5 1 0 4

//0 1 2 3 3 4
//0 0 0 1 2 1
//5 4 3 1 0 0


//5 0 0 4 0 3 3 0 2 1 1 1 0 2 3 0 1 4