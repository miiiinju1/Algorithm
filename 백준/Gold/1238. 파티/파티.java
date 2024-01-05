import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = Integer.MAX_VALUE/2;

    static class Point implements Comparable<Point>{
        int next,weight;

        public Point(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        int[][] reverseMap = new int[N + 1][N + 1];
        for(int i= 1;i<=N;i++) {
            Arrays.fill(map[i],MAX);
            Arrays.fill(reverseMap[i],MAX);
            map[i][i] = 0;
            reverseMap[i][i] = 0;
        }

        for(int i = 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[start][end] = weight;
            reverseMap[end][start] = weight;
        }

        int[] value = new int[N + 1];

        Arrays.fill(value, MAX);
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(X, 0));

        while(!pq.isEmpty()) {
            final Point now = pq.poll();

            if(value[now.next]<now.weight) {
                continue;
            }
            value[now.next] = now.weight;
            for(int i =1 ;i<=N;i++) {
                if(map[now.next][i]!=MAX&&value[i]>now.weight+map[now.next][i]) {
                    pq.add(new Point(i, now.weight+map[now.next][i]));
                }
            }
        }

        int[] value2 = new int[N + 1];
        Arrays.fill(value2, MAX);
        pq = new PriorityQueue<>();
        pq.add(new Point(X, 0));

        while(!pq.isEmpty()) {
            final Point now = pq.poll();

            if(value2[now.next]<now.weight) {
                continue;
            }
            value2[now.next] = now.weight;
            for(int i =1 ;i<=N;i++) {
                if(reverseMap[now.next][i]!=MAX&&value2[i]>now.weight+reverseMap[now.next][i]) {
                    pq.add(new Point(i, now.weight+reverseMap[now.next][i]));
                }
            }
        }
        int max = 0;
        for(int i =1 ;i<=N;i++) {
            max = Math.max(value[i] + value2[i], max);
        }
        System.out.println(max);
    }
}
