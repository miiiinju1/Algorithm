import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] visited = new boolean[51][51];


    static int[] dy = {-1, -1, -1, 1, 1, 1};
    static int[] dx = {0, -1, 1, 1, -1, 0};

    //0 그냥 위로
    //1 왼쪽 위로
    //2 오른쪽 위
    //3 오른쪽 아래
    //4 왼쪽 아래
    //5 아래방향

    static int[][] canGo = { {1,2},
                            {0,4},
                            {0,3},
                            {5,2},
                            {1,5},
                            {3,4}};
    //만약 이전에 0이면 0, 1, 2만 갈 수 있고
    //만약 1이면 1, 0,4만 갈 수 있고
    //만약 2면 0,2, 3
    //만약 3이면  3,5,2
    //만약 4면 2,4,5
    //만약 5면 5, 3, 4

    static void search(int y, int x, int way, int depth) {
        if(visited[y][x]) {
            if (depth == N) {
                count++;
            }
            return ;
        }

        if(depth>=N) {
            return;
        }

        visited[y][x] = true;
        for (int go : canGo[way]) {
            int Y = y+dy[go];
            int X = x+dx[go];
            if(Y>=0&&X>=0&&Y<51&&X<51) {
                search(Y,X,go,depth+1);
            }
        }
        visited[y][x] = false;
    }

    static int N,count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
        visited[25][25] = true;
        search(24,25,0,0);


        System.out.println(count);
    }
}
