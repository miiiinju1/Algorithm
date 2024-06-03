
import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;

    static class Node {
        int y, x;
        int type;

        public Node(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static final int FIRE = -1;

    static int h,w;

    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < h && x < w;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        testCase : for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
             w = Integer.parseInt(st.nextToken());
             h = Integer.parseInt(st.nextToken());

            map = new char[h][w];

            boolean[][] visited = new boolean[h][w];

            int startY = -1, startX = -1;
            List<Node> initFire = new ArrayList<>();
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);

                    if(map[i][j]=='#') {
                        visited[i][j] = true;
                    }
                    else if(map[i][j] == '*') {
                        //불
                        visited[i][j] = true;
                        initFire.add(new Node(i, j, FIRE));
                    }
                    else if(map[i][j] == '@') {
                        //상근
                        startY = i;
                        startX = j;
//                        map[i][j] = '.';
                    }
                }
            }

            Deque<Node> q = new ArrayDeque<>();
            q.add(new Node(startY, startX, 0));
            q.addAll(initFire);
            visited[startY][startX] = true;

            total: while(!q.isEmpty()) {
                final Node now = q.poll();

                if(now.type==FIRE) {
                    for (int d = 0; d < 4; d++) {
                        int y = dy[d] + now.y;
                        int x = dx[d] + now.x;
                        if (isValid(y, x) && (map[y][x] == '.' || map[y][x] == '@')) {
                            map[y][x] = '*';
                            visited[y][x] = true;
                            q.add(new Node(y, x, FIRE));
                        }
                    }
                }
                else if (map[now.y][now.x] == '@') {
                    for (int d = 0; d < 4; d++) {
                        int y = dy[d] + now.y;
                        int x = dx[d] + now.x;

                        if (isValid(y, x)) {
                            if(!visited[y][x]) {
                                map[now.y][now.x] = '.';
                                map[y][x] = '@';
                                visited[y][x] = true;
                                q.add(new Node(y, x, now.type + 1));
                            }
                        } 
                        else {
                            bw.write((now.type + 1) + "\n");
                            continue testCase;
                        }
                    }
                }
                
            }

            bw.write("IMPOSSIBLE\n");

        }
        bw.flush();bw.close();
    }
}
