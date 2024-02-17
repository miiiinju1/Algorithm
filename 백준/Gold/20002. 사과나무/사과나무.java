
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static int[][] map;

    static int sum(int startY, int startX, int K) {
        int Y = startY+K-1;
        int X = startX+K-1;
        if(Y>=N||X>=N) {
            return Integer.MIN_VALUE;
        }
        int sum = 0;
        sum+= map[Y][X];
        if(startY>0 && startX>0) {
            sum += map[startY-1][startX-1];
        }
        if(startY>0) {
            sum -= map[startY-1][X];
        }
        if(startX>0) {
            sum -= map[Y][startX-1];
        }

        return sum;

    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i= 0;i<N;i++) {
            for(int j= 1;j<N;j++) {
                map[i][j] += map[i][j-1];
            }
        }

        for(int j= 0;j<N;j++) {
            for (int i = 1; i < N; i++) {
                map[i][j] += map[i-1][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int size = 1;size<=N;size++) {
            for(int i= 0;i<N;i++) {
                for(int j= 0;j<N;j++) {
                    int result = sum(i,j,size);
                    if(result == Integer.MIN_VALUE) {
                        break;
                    }
                    else {
                        max = Math.max(max, result);
                    }
                }
            }
        }
        System.out.println(max);






    }

}


//1 1 1 1
//1 1 1 1

//1 2 3 4
//5 6 7 8
//9 10 11 12
//13 14 15 16

//11 - 10 - 7 -+ 1


//1 2 3 4
//2 4 6 8
//3 6 9 12
//4 8 12 16


// 9 - 3- 3 + 1

//

//4
//1 1 1 1
//1 1 1 1
//1 1 1 1
//1 1 1 1