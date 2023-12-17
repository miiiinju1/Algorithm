import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] map;
    static int N;
    static int count=0;

    static boolean isSafe(int row, int col) {
        // 세로 방향 검사
        for (int i = 0; i < row; i++) {
            if (map[i][col] == 1) {
                return false;
            }
        }

        // 대각선 검사 (왼쪽 위 방향)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (map[i][j] == 1) {
                return false;
            }
        }

        // 대각선 검사 (오른쪽 위 방향)
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (map[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    static void dfs( int index) {
//        if(index ==0) {
//            set = new HashSet<>();
//        }
        if(index==N) {
            count++;
            return ;
        }
        for(int i = 0;i<N;i++) {
//            if(before==-1||(before+1<i||before-1>i)) {
//                if (!set.contains(i)) {
            if (isSafe(index, i)) {
                    map[index][i] = 1;
//                    set.add(i);
                    dfs(index + 1);
                    map[index][i] = 0;
//                    set.remove(i);

            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dfs(0);
        System.out.println(count);
    }
}

// 0 1 1
// 1 1 0
// 1 0 1