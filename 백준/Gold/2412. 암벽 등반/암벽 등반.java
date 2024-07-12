
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Map<Integer, List<Integer>> map = new HashMap<>();

    static class Point {
        int y, x;
        int count;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
    static class Visit {
        int y,x;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Visit visit = (Visit) o;
            return y == visit.y && x == visit.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

        public Visit(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        for(int i= 0;i<=T;i++) {
            map.put(i, new ArrayList<>());
        }

        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            map.get(y).add(x);
        }

        for(int i= 0;i<=T;i++) {
            map.get(i).sort(Integer::compare);
        }

        ArrayDeque<Point> q = new ArrayDeque<>();
        Point start = new Point(0, 0);
        q.add(start);

        HashSet<Visit> visited = new HashSet<>();
        visited.add(new Visit(0, 0));
        // 이분 탐색을 계속 해야하나
        // 매번 한 번 갈 때마다
        //만약 0에서 시작이면 1에서 범위 맞는 애들, 2에서 범위 맞는 애들
        // 이렇게 하면 만약 2만개씩 있더라도 100몇번만에 찾을 수 있게 되긴하는데

        while(!q.isEmpty()) {
            Point now = q.poll();
//            System.out.println("now = " + now);

            if (now.y == T) {
                System.out.println(now.count);
                return;
            }
//            if(now.y >= T-2) {
//                System.out.println(now.count + 1);
//                return;
//            }


            for (int d = 0; d < 5; d++) {
                int Y = dy[d] + now.y;
                if (Y >= 0 && Y <= T) {
                    //여기에서 map.get(Y)한 뒤 범위 맞는 애들 찾기
                    List<Integer> can_X = map.get(Y);
                    if(map.get(Y).isEmpty()) continue;
                    // 이분 탐색으로 최소 index 찾기
                    int x_MinIndex = binarySearch(can_X, now.x);

//                    System.out.println("x_Min = " + x_Min);
                    ///////////
                    for (int i = x_MinIndex; i < can_X.size(); i++) {
                        int candidate = can_X.get(i);
                        if (candidate <= now.x + 2) {
                            Point next = new Point(Y, candidate, now.count + 1);
                            Visit mark = new Visit(Y, candidate);
                            if(!visited.contains(mark)) {
                                q.add(next);
                                visited.add(mark);
                            }
                        }
                        else {
                            break;
                        }
                    }
                    ////////////
                }
            }

        }
            System.out.println(-1);

    }

    static int binarySearch(List<Integer> list, int target) {
        int lo = -1, hi = list.size();

        int least = Math.max(0, target - 2);
        if(least == 0) {
            return 0;
        }

        while (lo + 1 < hi) {
            int mid = (hi - lo) / 2 + lo;

            if(list.get(mid) >= least) {
                hi = mid;
            }
            else {
                lo = mid;
            }

        }
        return hi;

    }

    static int[] dy = {-2, -1, 0, 1, 2};
}
