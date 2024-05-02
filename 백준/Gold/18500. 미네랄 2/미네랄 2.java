import java.io.*;
import java.util.*;

public class Main {

    static class Point {
        int y, x;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append('}');
            return sb.toString();
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int R, C;
    static int[][] map;
    static Map<Integer, List<Point>> cluster = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] initialMap = new char[R][C];
        map = new int[R][C];

        for(int i= 0;i<R;i++) {
            initialMap[i] = br.readLine().toCharArray();
        }

        initialize(initialMap);

//        System.out.println("cluster = " + cluster);
//
//        for(int i = 0;i<R;i++) {
//            System.out.println();
//            for(int j= 0;j<C;j++) {
//                System.out.print(map[i][j]+" ");
//            }
//        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            int height = R-Integer.parseInt(st.nextToken());
            //turn = 0이면 왼쪽, 1이면 오른쪽
            int turn = i%2;

            final Point throwing = throwing(height, turn);

            if(throwing==null) continue;

            final boolean reClustering = reClustering();

            //if(!reClustering) continue;

            //throwing에서 부터 다시 4방향 탐색하면서 cluster 중력으로 내리기

            searching(throwing);

        }


        for(int i = 0;i<R;i++) {
            for(int j= 0;j<C;j++) {
                if(map[i][j]==0) {
                    bw.write(".");
                }
                else
                    bw.write("x");
            }
            bw.write("\n");

        }
        bw.flush();bw.close();

    }
    static void gravity(int index) {

        List<Point> points = cluster.get(index);


        List<Point> updated;
        a: while(true) {
            updated = new ArrayList<>();
            for (Point point : points) {
                int y = dy[2] + point.y;
                int x = dx[2] + point.x;

                if (!(isValid(y, x) && (map[y][x] == index || map[y][x] == 0))) {

                    break a;
                }
                updated.add(new Point(y, x));
            }
            for (Point point : points) {
                map[point.y][point.x] = 0;
            }
            for (Point point : updated) {
                map[point.y][point.x] = index;
            }
//            System.out.println("cluster.get(index) = " + cluster.get(index));

//            for(int i= 0;i<R;i++) {
//                System.out.println();
//                for(int j= 0;j<C;j++) {
//                    System.out.print(map[i][j]+" ");
//                }
//            }
            cluster.replace(index, updated);
            points = new ArrayList<>(updated);
        }






    }
    static void searching(Point point) {
        for(int d = 0;d<4;d++) {
            int y = dy[d]+point.y;
            int x = dx[d]+point.x;

            if(isValid(y,x) && map[y][x]!=0) {
                gravity(map[y][x]);
            }
        }
    }


    static Point throwing(int height, int turn) {
        if(turn == 0) {
            for(int j= 0;j<C;j++) {
                if(map[height][j]!=0) {
                    map[height][j] = 0;
                    return new Point(height, j);
                }
            }
            return null;
        }
        if(turn == 1) {
            for(int j= C-1;j>=0;j--) {
                if(map[height][j]!=0) {
                    map[height][j] = 0;
                    return new Point(height, j);
                }
            }
            return null;
        }
        throw new RuntimeException("Illegal turn input");
    }

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};

    static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<R&&x<C;
    }
    static void initialize(char[][] initialMap) {
        boolean[][] visitied = new boolean[R][C];
        int count = 0;
        for(int i= 0;i<R;i++) {
            for(int j= 0;j<C;j++) {
                if(initialMap[i][j]=='x'&&!visitied[i][j]) {
                    visitied[i][j] = true;
                    count+=1;

                    List<Point> temp = new ArrayList<>();

                    Deque<Point> q = new ArrayDeque<>();
                    q.add(new Point(i, j));
                    while(!q.isEmpty()) {
                        final Point now = q.poll();

                        map[now.y][now.x] = count;
                        temp.add(now);
                        for(int d = 0;d<4;d++) {
                            int y = dy[d]+now.y;
                            int x = dx[d]+now.x;

                            if(isValid(y,x) && initialMap[y][x] == 'x' &&!visitied[y][x]) {
                                visitied[y][x] = true;
                                q.add(new Point(y, x));
                            }
                        }
                    }
                    cluster.put(count, temp);

                }
            }
        }



    }

    static boolean reClustering() {
        int beforeClusterCount = cluster.size();


        cluster = new HashMap<>();
        boolean[][] visitied = new boolean[R][C];
        int count = 0;
        for(int i= 0;i<R;i++) {
            for(int j= 0;j<C;j++) {
                if(map[i][j]!=0&&!visitied[i][j]) {
                    visitied[i][j] = true;
                    count+=1;

                    List<Point> temp = new ArrayList<>();
                    Deque<Point> q = new ArrayDeque<>();
                    q.add(new Point(i, j));
                    while(!q.isEmpty()) {
                        final Point now = q.poll();

                        map[now.y][now.x] = count;
                        temp.add(now);
                        for(int d = 0;d<4;d++) {
                            int y = dy[d]+now.y;
                            int x = dx[d]+now.x;

                            if(isValid(y,x) && map[y][x] != 0 &&!visitied[y][x]) {
                                visitied[y][x] = true;
                                q.add(new Point(y, x));
                            }
                        }
                    }
                    cluster.put(count, temp);
                }
            }
        }
        return count != beforeClusterCount;


    }
}
