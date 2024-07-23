
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int max = 0;

    // 100 개 점,  * 시작 점 * 평균 넓이  = 10000 * 평균 넓이 25*25 = 2500
    static int check(int y, int x, int endY, int endX) {
        int count = 0;
        for (int[] ints : ary) {
            if(ints[0] >= y && ints[0]<=endY) {
                if(ints[1]>=x && ints[1]<=endX) {
                    count++;
                }
            }
        }
        return count;
    }

    static int[][] ary;
    static int N,I;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken())/2;
        int M = Integer.parseInt(st.nextToken());
        ary = new int[M][2];


        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ary[i][0] = y;
            ary[i][1] = x;
        }

        for(int i = 0;i<M;i++) {
            search(ary[i][0], ary[i][1]);
        }

        System.out.println(max);
    }
    static void search(int y, int x) {
        for(int a = 1;a<I;a++) {
            int b = I - a;
            // a가 높이, b가 너비
            for(int i = 0;i<=a;i++) {
                for(int j = 0;j<=b;j++) {

                    if (y - i > 0 && x - j > 0 && y - i + a <= N && x - j + b <= N) {
                        int check = check(y - i, x - j, y - i + a, x - j + b);
                        max = Math.max(max, check);
                    }
                }
            }

        }
    }
}

// 0 -1 -2 -3

// -3 -2 -1 0
//만야 길이 3이면