
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static void search(int[] lines) {
        // 맨 윗줄 lines[0]에 대해서 모든 경우 체킹
        bruteForce(0, lines.length, lines, 0);

    }

    static void bruteForce(int nowIndex, int length, int[] lines, int count) {
        if (nowIndex == length) {
            // 확인해보기

            check(lines, count);

            return;
        }

        int[] temp1 = Arrays.copyOf(lines, lines.length);

        bruteForce(nowIndex + 1, length, temp1, count);

        int[] temp2 = Arrays.copyOf(lines, lines.length);
        //nowIndex에 대해 flip 하기


        // nowIndex에 대해서 nowIndex -1
        if (nowIndex - 1 >= 0) {
            temp2[0] ^= (1 << nowIndex - 1);
        }
        temp2[0] ^= (1 << nowIndex);
        if (nowIndex + 1 < N) {
            temp2[0] ^= (1 << nowIndex + 1);
        }
        temp2[1] ^= (1 << nowIndex);

        bruteForce(nowIndex + 1, length, temp2, count + 1);

    }

    static int min = Integer.MAX_VALUE;

    static void check(int[] lines, int count) {

        for (int i = 1; i < N; ++i) {

            //i-1줄에 대해서, 1이면
            for (int j = 0; j < N; ++j) {
                if ((lines[i - 1] & (1 << j)) > 0) {
                    ++count;
                    // 본인을 0으로 만들면서 좌, 우, 아래
                    if (j - 1 >= 0) {
                        lines[i] ^= (1 << j - 1);
                    }
                    lines[i] ^= (1 << j);
                    if (j + 1 < N) {
                        lines[i] ^= (1 << j + 1);
                    }
                    if(i+1<N) {
                        lines[i + 1] ^= (1 << j);
                    }
                }
            }

        }

        if (lines[N - 1] == 0) {

            min = Math.min(count, min);
        }
    }

    static int N;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] lines = new int[N];
        for (int i = 0; i < N; ++i) {
            var st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; ++j) {
                if (st.nextToken().equals("1")) {
                    lines[i] |= 1 << (N - j - 1);
                }
            }


        }
        search(lines);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);



        /*

        N이 18이하기 때문에 굉장히 작음, 하지만

        18 * 18개의 경우의 수를 반복하려면 너무 많아짐 따라서 이걸 최적화해야하는데

        344 ^ x번, X

        // 2 번 누르면 다시 돌아오니깐


         각각은 누르냐, 안 누르냐로 결정됨


         따라서 2 ^ 18*18을 풀면됨

          2^32만해도 4억인데


         // 2^10 =




            두 줄을 기준으로

            윗 줄이 어떨 때
            아랫 줄을 어떻게 해야한다의 기준치를 세워두고


            윗줄부터 0으로 가득 채우면 아래 줄이 어떻게 되는지를 판단해서,


            일단 메모리 아끼려면 비트마스킹 필수고



            윗 줄이 어떨 때, 윗 줄을 0000으로 만들면 아랫줄이 어떻게 되는지에 대해서 반복


            마지막 줄까지 와서는 윗줄이 000일 때, 아랫 줄을 00000까지 되는ㄱ ㅓ


         */
    }

}


/*

1 1
1 1

0 0
0 1

1 1
0 0

0 1
1 1


0 0
0 0



0 1
1 0


 */