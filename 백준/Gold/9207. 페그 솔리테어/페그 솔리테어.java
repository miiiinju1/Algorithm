import java.io.*;
import java.util.ArrayDeque;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for(int tc = 1;tc<=N;tc++) {
            solution();
            if(tc!=N)
                br.readLine();
        }
        bw.flush();bw.close();
    }

    static class Phase {
        char[][] map;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Phase{");
            sb.append(", depth=").append(depth);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        int depth, count;

        public Phase(char[][] map, int depth, int count) {
            this.map = map;
            this.depth = depth;
            this.count = count;
        }
    }
    static void solution() throws IOException {
        char[][] initialMap = new char[5][9];
        int initialCount = 0;
        for (int i = 0; i < 5; i++) {
            initialMap[i] = br.readLine().toCharArray();
            for(int j= 0;j<9;j++) {
                if (initialMap[i][j] == 'o') {
                    initialCount+=1;
                }
            }
        }
        ArrayDeque<Phase> q = new ArrayDeque<>();
        q.add(new Phase(initialMap, 0, initialCount));

        int sumDepth = Integer.MAX_VALUE,
                sumCount = Integer.MAX_VALUE;

        while(!q.isEmpty()) {

            final Phase now = q.poll();
            char[][] map = now.map;

            if(sumCount > now.count) {
                sumCount = now.count;
                sumDepth = now.depth;

            }
            else if(sumCount==now.count) {
                if(sumDepth > now.depth) {
                    sumDepth = now.depth;
                }
            }
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') {
                        // 좌우 확인
                        if (j+1<9&&map[i][j + 1] == 'o') {
                            //오른쪽으로 넘어갈 수 있나
                            if(j+2<9 && map[i][j+2] == '.') {
                                char[][] temp = new char[5][9];
                                arrayCopy(map, temp);
                                temp[i][j] = '.';
                                temp[i][j + 1] = '.';
                                temp[i][j + 2] = 'o';
                                q.add(new Phase(temp, now.depth + 1, now.count - 1));
                            }
                            //왼쪽으로 넘어갈 수 있나
                            if(j-1>=0 && map[i][j-1] == '.') {
                                char[][] temp = new char[5][9];
                                arrayCopy(map, temp);
                                temp[i][j] = '.';
                                temp[i][j + 1] = '.';
                                temp[i][j - 1] = 'o';
                                q.add(new Phase(temp, now.depth + 1, now.count - 1));
                            }
                        }
                        // 상하 확인
                        if (i+1<5 && map[i + 1][j] == 'o') {
                            //아래로 넘어갈 수 있나
                            if(i+2<5 && map[i+2][j] == '.') {
                                char[][] temp = new char[5][9];
                                arrayCopy(map, temp);
                                temp[i][j] = '.';
                                temp[i + 1][j] = '.';
                                temp[i + 2][j] = 'o';
                                q.add(new Phase(temp, now.depth + 1, now.count - 1));
                            }
                            //위쪽으로 넘어갈 수 있나
                            if(i-1>=0 && map[i-1][j] == '.') {
                                char[][] temp = new char[5][9];
                                arrayCopy(map, temp);
                                temp[i][j] = '.';
                                temp[i + 1][j] = '.';
                                temp[i - 1][j] = 'o';
                                q.add(new Phase(temp, now.depth + 1, now.count - 1));
                            }
                        }
                    }

                }
            }

        }

        bw.write(sumCount + " " + sumDepth + "\n");

    }

    static void arrayCopy(char[][] from, char[][] to) {
        for (int i = 0; i < from.length; i++) {

            for (int j = 0; j < from[0].length; j++) {
                to[i][j] = from[i][j];
            }
        }
    }

}
