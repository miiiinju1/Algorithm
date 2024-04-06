import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dy = {-1,-1,0,1,1,1,0,-1};
    static int[] dx = {0,-1,-1,-1,0,1,1,1};

    static class Shark {
        int[][] map;
        int[][] direction;
        int y,x, sharkDir;
        int value;

        public boolean isValid(int Y, int X) {
            return Y>=0&&Y<4&&X>=0&&X<4&&!(y==Y&&x==X);
        }
        public void moveFish() {
            next:for(int num = 1;num<=16;num++) {

                for(int i= 0;i<4;i++) {
                    for(int j= 0;j<4;j++) {
                        if(map[i][j]==num) {

                            while(true) {
                                if(direction[i][j]==8) {
                                    direction[i][j] = 0;
                                }
                                int Y = dy[direction[i][j]] + i;
                                int X = dx[direction[i][j]] + j;

                                if(isValid(Y,X)) {
                                    //이제 교환
                                    int temp = map[i][j];
                                    map[i][j] = map[Y][X];
                                    map[Y][X] = temp;

                                    temp = direction[i][j];
                                    direction[i][j] = direction[Y][X];
                                    direction[Y][X] = temp;
                                    break;
                                }
                                direction[i][j]++;
                            }
                            continue next;
                        }
                    }
                }
            }
        }
        public Shark(int[][] map, int[][] direction, int y, int x, int sharkDir, int value) {
//            this.map = new int[4][4];
//            for(int i = 0;i<4;i++) {
//                for(int j= 0;j<4;j++) {
//                    this.map[i][j] = map[i][j];
//                }
//            }
            this.map = map;
            this.direction = direction;
            this.sharkDir = sharkDir;
//            this.direction = new int[4][4];
//            for(int i = 0;i<4;i++) {
//                for(int j= 0;j<4;j++) {
//                    this.direction[i][j] = direction[i][j];
//                }
//            }
            this.y = y;
            this.x = x;
            this.value = value;
        }
    }
    static int[][] arrayCopy(int[][] from) {
        int[][] ret = new int[4][4];
        for(int i= 0;i<4;i++) {
            for(int j= 0;j<4;j++) {
                ret[i][j] = from[i][j];
            }
        }
        return ret;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] initialMap = new int[4][4];
        int[][] initialDirection = new int[4][4];
        for (int i = 0; i < 4; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                initialMap[i][j] = Integer.parseInt(st.nextToken());
                initialDirection[i][j] = Integer.parseInt(st.nextToken())-1;
            }
        }
        int initialValue = initialMap[0][0];
        initialMap[0][0] = -1;
        int initialDir = initialDirection[0][0];
        initialDirection[0][0] = -1;

        Shark initialShark = new Shark(initialMap, initialDirection, 0,0, initialDir, initialValue);

        Queue<Shark> q = new ArrayDeque<>();
        q.add(initialShark);

        int max = 0;
        while(!q.isEmpty()) {
            final Shark now = q.poll();

            max = Math.max(now.value, max);
            now.moveFish();


            //이제 상어 이동하면서 q에 넣기
            for(int time = 1;time<4;time++) {

                int sharkY = now.y + dy[now.sharkDir]*time;
                int sharkX = now.x + dx[now.sharkDir]*time;

                if(sharkY>=0&&sharkY<4&&sharkX>=0&&sharkX<4) {
                    if (now.map[sharkY][sharkX] != -1) {

                        int[][] nextMap = arrayCopy(now.map);
                        int[][] nextDir = arrayCopy(now.direction);
                        int eaten = nextMap[sharkY][sharkX];
                        nextMap[sharkY][sharkX] = -1;
                        int sharkDir = nextDir[sharkY][sharkX];
                        nextDir[sharkY][sharkX] = -1;
                        q.add(new Shark(nextMap, nextDir, sharkY, sharkX, sharkDir, now.value+eaten));
                    }
                }

            }


        }

        System.out.println(max);

    }
}
