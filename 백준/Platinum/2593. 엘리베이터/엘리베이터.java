
import java.io.*;
import java.util.*;

public class Main {
    static class Elevator {
        int a, b;

        public Elevator(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] map = new List[N + 1];
        for(int i= 1;i<=N;i++) {
            map[i] = new ArrayList<>();
        }

        Elevator[] elevators = new Elevator[M + 1];

        for(int i = 1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            elevators[i] = new Elevator(a, b);
            while(true) {
                map[a].add(i);
                a+=b;
                if(a>N)
                    break;
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] visited = new int[M + 1];;

    //start 는 층수


        //층수에 따라 엘리베이터
        //엘리베이터 번호
        Deque<Integer> q = new ArrayDeque<>(map[start]);

        for (Integer i : map[start]) {
            visited[i] = i;
        }

        int endEle = -1;
        a: while(!q.isEmpty()) {
            //엘리베이터 번호
            final Integer now = q.poll();


            // 엘베에 포함되는 층들
            Elevator e = elevators[now];
            int floor = e.a;
            //엘베
            while (true) {
                // 층에 포함되는 엘베들
                for (Integer nextElevator : map[floor]) {
                    if (visited[nextElevator] == 0) {
                        visited[nextElevator] = now;
                        q.add(nextElevator);
                    }
                }
                if(floor==end) {
                    endEle = now;
                    break a;
                }
                floor += e.b;
                if (floor > N)
                    break;
            }
        }

        if (endEle == -1) {
            bw.write("-1\n");
            bw.flush();bw.close();
            return;
        }

        List<Integer> result = new ArrayList<>();
        int d = endEle;
        while(true) {
            result.add(d);
            if(visited[d]==d) {
                break;
            }
            d = visited[d];
        }

        Collections.reverse(result);

        bw.write(result.size() + "\n");
        for (Integer i : result) {
            bw.write(i + "\n");
        }

        bw.flush();bw.close();
    }

}
