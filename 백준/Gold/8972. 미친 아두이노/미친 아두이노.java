import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Main {

    static int[] dy = {1,1,1,0,0,0,-1,-1,-1,0};
    static int[] dx = {-1,0,1,-1,0,1,-1,0,1,0};
    static char[][] map;

    static int distance(int r1,int s1, int r2, int s2) {
        return abs(r1-r2) + abs(s1-s2);
    }
    //i<9까지면 종수, i<8이면 미친놈

    static class Point{
        int y, x,time;
        public Point(int y, int x,int time) {
            this.y = y;
            this.x =x;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (y != point.y) return false;
            if (x != point.x) return false;
            return time == point.time;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", time=").append(time);
            sb.append('}');
            return sb.toString();
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            result = 31 * result + time;
            return result;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        int iY=-1,iX=-1;
        ArrayDeque<Point> 미친놈 = new ArrayDeque<>();
        for(int i= 0;i<R;i++) {
            final char[] input = br.readLine().toCharArray();
            for(int j = 0;j<C;j++) {
                map[i][j] = '.';
                if(input[j] =='I') {
                    iY = i;
                    iX = j;
                }
                else if(input[j] == 'R') {
                    미친놈.add(new Point(i, j,0));
                }
            }
        }


        String[] split = br.readLine().split("");
        HashMap<Point,Integer> 중복제거 = new HashMap<>();
        int time = 1;
        for(String next : split) {
            int d = Integer.parseInt(next)-1;

            iY += dy[d];
            iX += dx[d];

            for (Point point : 미친놈) {
                if(point.y==iY && point.x==iX) {
                    System.out.printf("kraj %d", time );
                    return ;
                }
            }

            int size = 미친놈.size();

            while(size>0) {
                Point point = 미친놈.poll();
                int min = Integer.MAX_VALUE;
                int minY=-1,minX=-1;
                for(int i= 0;i<9;i++) {
                    if(i==4)
                        continue;
                    int y = point.y + dy[i];
                    int x = point.x + dx[i];
                    int temp = distance(iY, iX, y, x);
                    if(min>temp) {
                        min = temp;
                        minY = y;
                        minX = x;
                    }

                }
                if(min==0) {
                    System.out.printf("kraj %d", time);
                    return;
                }
                Point ne = new Point(minY, minX, point.time + 1);
                중복제거.put(ne, 중복제거.getOrDefault(ne, 0) + 1);
                size--;
            }
            time++;
            미친놈 = 중복제거.entrySet().stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(ArrayDeque::new));
            중복제거 = new HashMap<>();
        }

        map[iY][iX] = 'I';
        for (Point point : 미친놈) {
            map[point.y][point.x] = 'R';
        }
        StringBuilder sb = new StringBuilder();
        for(int i= 0;i<R;i++) {
            for(int j= 0;j<C;j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

