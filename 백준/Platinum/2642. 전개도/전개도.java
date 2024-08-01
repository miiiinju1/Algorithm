
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashSet<Integer> answer = new HashSet<>();
    static int sum = 0;

    static boolean calculate(int depth, int[] result, boolean[] visited) {
        if(depth==6) {
//            System.out.println("answer = " + answer);

            if(answer.size()==6) {
                return true;
            }
            return false;
        }
        int now = result[depth];

        if(visited[now]) {
            calculate(depth + 1, result, visited);
        }

        for (Integer to : count.get(now)) {
            if(count.get(to).contains(now)) {
                if(visited[to]) continue;
                visited[to] = true;

                answer.add(to);
                if(calculate(depth + 1, result, visited)) {
                    if(to==1) {
                        sum = now;
                    }
                    else if(now ==1) {
                        sum = to;
                    }
                    return true;
                }
                visited[to] = false;
                answer.remove(to);
            }
        }
        return false;
    }

    static boolean test(int depth, int[] result, boolean[] visited) {
        if(depth == 6) {
            answer = new HashSet<>();
            return calculate(0, result, new boolean[7]);
        }
        for(int i=1;i<7;i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[depth] = i;
                if(test(depth+1, result, visited)) {
                    return true;
                }
                visited[i] = false;
            }

        }
        return false;
    }

    static int[] sdy = {-1, 0, 1, 0};
    static int[] sdx = {0, -1, 0, 1};
    static boolean dfs(int y, int x) {
        int c = 0;
        a : for (int d = 0; d < 4; d++) {
            for (int i = 1; i <= 2; i++) {
                int Y = sdy[d] * i + y;
                int X = sdx[d] * i + x;
                if(isValid(Y,X))
                if (isValid(Y, X) && map[Y][X] != 0) {
                    if (i == 2) {
                        c++;
                    }
                } else {
                    continue a;
                }
            }
        }
        return c<=1;
    }

    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[6][6];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i= 0;i<6;i++) {
            for(int j= 0;j<6;j++) {
                if(map[i][j]!=0) {
                    if (!dfs(i, j)) {
                        System.out.println(0);
                        return;
                    }
                }

            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != 0) {
                    search(i, j);
                }
            }
        }
        test(0, new int[6], new boolean[7]);



        System.out.println(sum);
//        System.out.println("count = " + count);




    }

    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < 6 && x < 6;
    }
    static Map<Integer, Set<Integer>> count = new HashMap<>();
    static {
        for(int i= 1;i<=6;i++)
            count.put(i, new HashSet<>());
    }
    private static void search(int startY, int startX) {
        int from = map[startY][startX];
        visited = new boolean[6][6];
        visited[startY][startX] = true;
        dfs(startY, startX, new int[4], from);
    }


    static boolean[][] visited;
    static void dfs(int startY, int startX, int[] c, int from) {

//        System.out.println("from = " + from);
//        System.out.println("startY = " + startY+" "+startX);
        for (int dir = 0; dir < 4; dir++) {
            int Y = startY + sdy[dir];
            int X = startX + sdx[dir];
//            System.out.println("Y+\" \"+X = " + Y+" "+X);
            if (isValid(Y, X) && !visited[Y][X] && map[Y][X] != 0) {

                visited[Y][X] = true;
                c[dir]++;
//                for(int i= 0;i<4;i++) {
//                    System.out.print(c[i]+" ");
//                }
//                System.out.println();
                if (c[dir] == 2) {
                    // 추가
                    count.get(from).add(map[Y][X]);
                }
                dfs(Y, X, c, from);
                c[dir]--;
                visited[Y][X] = false;

            }
        }
    }
}
