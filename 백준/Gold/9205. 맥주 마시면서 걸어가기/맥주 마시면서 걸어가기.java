
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Store implements Comparable<Store> {
        int y, x, i;
        int gValue; // 현재 노드까지의 비용
        int fValue; // f(N) 값은 gValue와 휴리스틱 값의 합

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Store{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append('}');
            return sb.toString();
        }
        @Override
        public int compareTo(Store o) {
            return this.fValue - o.fValue;
        }

        // fValue 계산을 위한 함수
        public void calculateFValue(int destY, int destX) {
            int hValue = Math.abs(this.y - destY) + Math.abs(this.x - destX); // 휴리스틱 함수 (맨해튼 거리)
            this.fValue = this.gValue + hValue;
        }

        public Store(int y, int x,int i) {
            this.y = y;
            this.x = x;
            this.i=i;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        a : for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            ArrayList<Store> stores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                stores.add(new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),-1));
            }
            st = new StringTokenizer(br.readLine());
            int destY = Integer.parseInt(st.nextToken());
            int destX = Integer.parseInt(st.nextToken());
            if(Math.abs(startX-destX)+Math.abs(startY-destY)<=1000) {
                bw.write("happy\n");
                continue a;
            }
            boolean[] visited = new boolean[N];

            PriorityQueue<Store> q = new PriorityQueue<>();
            Store start = new Store(startY, startX, -1);
            start.gValue = 0; // 시작 지점에서의 gValue는 0
            start.calculateFValue(destY, destX); // 시작 지점에서의 fValue 계산
            q.add(start);

            while(!q.isEmpty()) {
                Store now = q.poll();
                int nowY = now.y;
                int nowX = now.x;
                if (now.i != -1) {
                    if (!visited[now.i]) {
                        visited[now.i] = true;
                    }
                }
                if (Math.abs(nowX - destX) + Math.abs(nowY - destY) <= 1000) {
                    bw.write("happy\n");
                    continue a;
                }

                for (int i = 0; i < N; i++) {
                    if (!visited[i] && Math.abs(nowX - stores.get(i).x) + Math.abs(nowY - stores.get(i).y) <= 1000) {
//                        min = Math.abs(nowX - stores.get(i).x) + Math.abs(nowY - stores.get(i).y);
                        q.add(new Store(stores.get(i).y, stores.get(i).x, i));

                    }
                }
//
            }
            bw.write("sad\n");



        }

        bw.flush();bw.close();
    }
}
