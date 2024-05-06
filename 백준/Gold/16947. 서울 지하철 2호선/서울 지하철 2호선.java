import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static int[] cost;
    static int dfs(int now, int before, int i) {
        for (Integer next : map.get(now)) {
            if(next==before) continue;

            if (visitedInt[next] != i) {
                visitedInt[next] = i;
                int temp = dfs(next, now, i);
                if (temp == -1) {
                    return -1;
                }

                cost[now] = 0;
                if (temp == now) {
                    return -1;
                }

                return temp;
            } else {
                //cycle
                cost[now] = 0;
                return next;
            }
        }
        return -1;
    }

    static int[] visitedInt;

    static int detect(int now, int before, boolean[] visited) {
        for (Integer next : map.get(now)) {
            if(next==before) continue;

            if(!visited[next]) {
                visited[next] = true;
                final int detect = detect(next, now, visited);

                if(detect==now) {
                    cost[now] = 0;
                    return -1;
                }
                if(detect != -1) {
                    cost[now] = 0;
                    return detect;
                }

            }
            else {
                cost[now] = 0;
                return next;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        visitedInt = new int[N + 1];
        cost = new int[N + 1];
        Arrays.fill(cost, -1);
        for(int i= 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }

//        for(int i = 1;i<=N;i++) {
//            if(cost[i]==-1) {
//                visitedInt[i] = i;
//                dfs(i, -1, i);
//            }
//        }
        detect(1, -1, new boolean[N + 1]);
        distance(N);

        for(int i= 1;i<=N;i++) {
            bw.write(cost[i]+" ");
        }
        bw.flush();bw.close();

    }



    static class Node {
        int now, depth;

        public Node(int now, int depth) {
            this.now = now;
            this.depth = depth;
        }


    }
    static void distance(int N) {
        int[] visited = new int[N+1];
        for(int i= 1;i<=N;i++) {
            if(cost[i] == -1) {
                Deque<Node> q = new ArrayDeque<>();
                visited[i] = i;
                q.add(new Node(i, 0));

                while(!q.isEmpty()) {
                    final Node now = q.poll();

                    if(cost[now.now] == 0) {
                        cost[i] = now.depth;
                        continue;
                    }
                    for (Integer next : map.get(now.now)) {
                        if(visited[next]!=i) {
                            visited[next] = i;
                            q.add(new Node(next, now.depth + 1));
                        }
                    }
                }

            }
        }




    }


}

//8
//1 3
//1 4
//4 2
//3 2
//2 8
//3 5
//5 6
//6 7
