import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] visited;
    static char[][] map;

    static boolean search(int y, int x,int count) {

        if (map[y][x] == 'D') {
            if (visited[y + 1][x]>count) {
                visited[y + 1][x] = count;
                return search(y + 1, x,count);
            }
            else if(visited[y+1][x]<count) {
                return false;
            }
        } else if (map[y][x] == 'U') {
            if (visited[y - 1][x]>count) {
                visited[y - 1][x] = count;
                return search(y - 1, x,count);
            }
            else if(visited[y-1][x]<count) {
                return false;
            }
        } else if (map[y][x] == 'L') {
            if (visited[y][x - 1]>count) {
                visited[y][x - 1] = count;
                return search(y, x - 1,count);
            }
            else if(visited[y][x-1]<count) {
                return false;
            }
        } else {
            if(visited[y][x + 1]>count) {
                visited[y][x + 1] = count;
                return search(y, x + 1, count);
            }
            else if(visited[y][x+1]<count) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
       visited = new int[N][M];


        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().toCharArray();
            Arrays.fill(visited[n], Integer.MAX_VALUE);
        }

        int result = 0;
        int count = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if(visited[n][m]>count) {
                    count++;
                    visited[n][m] = count;
                    if(search(n,m,count))
                        result++;
                }
            }
        }

        System.out.println(result);
    }
}

//3 4
//DLLL
//DLLU
//RRRU

//3 4
//DLLL
//DLLU
//RRRU