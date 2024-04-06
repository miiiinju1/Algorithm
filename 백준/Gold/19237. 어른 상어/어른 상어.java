import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Trace {
        int num, time;

        public Trace(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }

    static class Shark {
        int num, y, x;
        int[][] dir;
        int nowDir;

        public Shark(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Shark(int num, int y, int x, int[][] dir, int nowDir) {
            this.num = num;
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.nowDir = nowDir;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Shark{");
            sb.append("num=").append(num);
            sb.append(", y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", nowDir=").append(nowDir);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Shark shark = (Shark) o;

            if (y != shark.y) return false;
            return x == shark.x;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }
    }

    static class Point{
        int y, x,i;

        public Point(int y, int x, int i) {
            this.y = y;
            this.x = x;
            this.i = i;
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Trace[][] visited = new Trace[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());

                if (temp == 0) {
                    visited[i][j] = new Trace(-1, -1);
                } else {
                    visited[i][j] = new Trace(temp, k);
                }
            }
        }


        HashSet<Shark> set = new HashSet<>();



        st = new StringTokenizer(br.readLine());
        int[] initialDir = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            initialDir[i] = Integer.parseInt(st.nextToken())-1;
        }

        for (int num = 1; num <= M; num++) {
            int[][] dir = new int[4][4];
            for (int i = 0;i<4;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j= 0;j<4;j++) {
                    dir[i][j] = Integer.parseInt(st.nextToken())-1;
                }
            }
            for(int i= 0;i<N;i++) {
                for(int j= 0;j<N;j++) {
                    if(visited[i][j].num==num) {
                        set.add(new Shark(num, i, j, dir, initialDir[num]));
                        break;
                    }
                }
            }
        }

        final ArrayDeque<Shark> q = new ArrayDeque<>(set);

        int time = 0;
        while (q.size() > 1) {

            time++;
            if (time > 1000) {
                System.out.println(-1);
                return;
            }

            List<Shark> list = new ArrayList<>();
            while (!q.isEmpty()) {
                final Shark now = q.poll();


                Queue<Point> candidate = new ArrayDeque<>();
                // 일단 빈칸 찾기
                for (int i = 0; i < 4; i++) {
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];

                    if (isValid(y, x)) {
                        if (visited[y][x].time < time) {
                            candidate.add(new Point(y, x, i));
                        }
                    }
                    if (candidate.size() > 1) {
                        break;
                    }
                }

                if (candidate.isEmpty()) {
                    // 내 흔적으로
                    // 내 흔적에서 가기
                    for (int i = 0; i < 4; i++) {

                        int y = now.y + dy[now.dir[now.nowDir][i]];
                        int x = now.x + dx[now.dir[now.nowDir][i]];

                        if (isValid(y, x)  && visited[y][x].num == now.num) {
                            candidate.add(new Point(y, x, now.dir[now.nowDir][i]));
                            break;
                        }
                    }
                }

                if (candidate.size() == 1) {
                    // 그냥 선택
                    final Point poll = candidate.poll();
                    list.add(new Shark(now.num, poll.y, poll.x, now.dir, poll.i));
                } else {
                    for (int i = 0; i < 4; i++) {

                        int y = now.y + dy[now.dir[now.nowDir][i]];
                        int x = now.x + dx[now.dir[now.nowDir][i]];

                        if (isValid(y, x)) {
                            // 흔적 없으면
                            if (visited[y][x].time < time) {

                                list.add(new Shark(now.num, y, x, now.dir, now.dir[now.nowDir][i]));
                                break;
                            }
                        }

                    }
                }


            }
//            System.out.println("list = " + list);

            // list에서 겹치는거 없는지 확인
            final Map<String, List<Shark>> collect = list.stream()
                    .collect(Collectors.groupingBy(shark -> shark.y + " " + shark.x));


            for (Map.Entry<String, List<Shark>> entry : collect.entrySet()) {
                if(entry.getValue().size()>1) {
                    // 가장 작은거
                    final Shark min = entry.getValue().stream()
                            .min(Comparator.comparingInt(o -> o.num))
                            .get();
                    visited[min.y][min.x].num = min.num;
                    visited[min.y][min.x].time = time + k;
                    q.add(min);
                } else {
                    final Shark shark = entry.getValue().get(0);
                    visited[shark.y][shark.x].num = shark.num;
                    visited[shark.y][shark.x].time = time + k;
                    q.add(entry.getValue().get(0));
                }
            }
//            for(int i= 0;i<N;i++) {
//                for(int j= 0;j<N;j++) {
//                    if(visited[i][j].time<=time) {
//                        visited[i][j].time = -1;
//                        visited[i][j].num = -1;
//                    }
//                }
//            }
//            System.out.println(q);


        }

        System.out.println(time);


    }
    private static boolean isValid(int y, int x) {
        return y>=0&&x>=0&&y<N&&x<N;
    }
}