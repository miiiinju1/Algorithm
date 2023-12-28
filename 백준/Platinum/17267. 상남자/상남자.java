import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int y, x, L, R;

        public Point(int y, int x, int l, int r) {
            this.y = y;
            this.x = x;
            L = l;
            R = r;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", L=").append(L);
            sb.append(", R=").append(R);
            sb.append('}');
            return sb.toString();
        }


    }

//    static int[] dx = {-1, 0, 1};

    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new int[N][M];
//        for(int i =0;i<N;i++) {
//            Arrays.fill(visited[i], );
//        }
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        ArrayList<Point> startPoint = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            char[] array = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (array[j] == '1') {
                    visited[i][j] = -1;
                } else if (array[j] == '2') {
                    startPoint.add(new Point(i, j, 0, 0));
                    visited[i][j] = 1;
                }
            }
        }
        int count = 0;
        for (Point start : startPoint) {


            ArrayDeque<Point> q = new ArrayDeque<>();
            q.add(start);
            count++;
            while (!q.isEmpty()) {
                Point now = q.poll();
                for(int i = now.y;i>=0;i--) {
                    if(visited[i][now.x]==-1) {
                        break;
                    }
                    if(visited[i][now.x]==0) {
                        count++;
                        visited[i][now.x] = 1;
                        q.add(new Point(i, now.x, now.L, now.R));
                    }
                }
                for(int i = now.y;i<N;i++) {
                    if(visited[i][now.x]==-1) {
                        break;
                    }
                    if(visited[i][now.x]==0) {
                        count++;
                        visited[i][now.x] = 1;
                        q.add(new Point(i, now.x, now.L, now.R));
                    }
                }

                for (int z = 0; z <2; z++) {
                    int y = now.y + dy[z];
                    int x = now.x + dx[z];

                    if (y >= 0 && x >= 0 && y < N && x < M && visited[y][x]==0) {
                        if (z == 0  && now.L < L) {
                            visited[y][x] =1;// visited[now.y][now.x] + 1;
                            count++;
                            q.add(new Point(y, x, now.L + 1, now.R));
                        } else if (z == 1&& now.R < R) {
                            visited[y][x] = 1;//visited[now.y][now.x] + 1;
                            count++;
                            q.add(new Point(y, x, now.L, now.R + 1));
                        }
                    }


                }
            }


//                for (int i = now.y; i >= 0; i--) {
//                    if (visited[i][now.x] == -1) {
//                        break;
//                    }
//                    for (int z = 0; z < 3; z++) {
//                        int x = now.x + dx[z];
//                        if (x >= 0 && x < M && visited[i][x] == 0) {
//                            if (z == 0 && now.L < L) {
////                                visited[i][x] = 1;
////                                count++;
//                                q.add(new Point(i, x, now.L + 1, now.R));
//                            } else if (z == 2 && now.R <R) {
////                                visited[i][x] = 1;
////                                count++;
//                                q.add(new Point(i, x, now.L, now.R + 1));
//                            } else if(z==0){
//                                visited[i][x] = 1;
//                                count++;
//                                q.add(new Point(i, x, now.L, now.R));
//                            }
//                        }
//                    }
//                }
//                    for (int i = now.y; i < N; i++) {
//                        if (visited[i][now.x] == -1) {
//                            break;
//                        }
//                        for (int z = 0; z < 3; z++) {
//                            int x = now.x + dx[z];
//                            if (x >= 0 && x < M && visited[i][x] == 0) {
//                                if (z == 0 && now.L < L) {
////                                    visited[i][x] = 1;
////                                    count++;
//                                    q.add(new Point(i, x, now.L + 1, now.R));
//                                } else if (z == 2 && now.R <R) {
////                                    visited[i][x] = 1;
////                                    count++;
//                                    q.add(new Point(i, x, now.L, now.R + 1));
//                                } else if(z==0) {
//                                    visited[i][x] = 1;
//                                    count++;
//                                    q.add(new Point(i, x, now.L, now. R));
//                                }
//                            }
//                        }
//                    }
//                }


//            }

            System.out.println(count);
        }
    }
}