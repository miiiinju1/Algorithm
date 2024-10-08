
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> inputs = new ArrayList<>();

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int[] input = new int[k];
            for (int j = 0; j < k; ++j) {
                input[j] = Integer.parseInt(st.nextToken());
            }
            inputs.add(input);
        }

        st = new StringTokenizer(br.readLine());
        boolean[] results = new boolean[N + 1];
        boolean[] cant = new boolean[N + 1];
        boolean[] candidate = new boolean[N + 1];

        for (int i = 1; i <= N; ++i) {
            results[i] = st.nextToken().equals("1");
//            System.out.println("results[i] = " + results[i]);

        }
        for (int i = 1; i <= N; ++i) {
            candidate[i] = results[i];
            cant[i] = (!results[i]);
        }

        for (int j = M - 1; j >= 0; --j) {
//        for (int j = 0; j < M; ++j) {
            int[] input = inputs.get(j);

            boolean flag = true;
            for (int i : input) {
                if (!candidate[i]) {
                    flag = false;
                    break;
                }
            }
            // 가능
            if (flag) {
                for (int i : input) {
                    if (!cant[i]) {
                        candidate[i] = true;
                    }
                }
            } else {
                //불가
                for (int i : input) {
                    if (candidate[i]) {
                        candidate[i] = false;
                    }
                    cant[i] = true;
                }
            }
            // 만약 전체가 1이 아니면
            // 절대 시작점에 0이 될 수 없음
            // 만약 전체가 1이라면, 0이 안 되는 애들 제외하고 후보에 들어감
        }

        boolean[] validate = new boolean[N + 1];

//        System.out.println(Arrays.toString(candidate));
//
        for(int i= 0;i<=N;++i) {
            validate[i] = candidate[i];
        }

        for (int[] input : inputs) {

            boolean flag = false;
            for (int i : input) {
                if (validate[i]) {
                    flag = true;
                }
            }

            if (flag) {
                for (int i : input) {
                    validate[i] = true;
                }
            }
        }

        for (int i = 1; i <= N; ++i) {
            if (validate[i] != results[i]) {
                System.out.println("NO");
                return;
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write("YES\n");
        for (int i = 1; i <= N; ++i) {

            int val = candidate[i] ? 1 : 0;
            bw.write(val + " ");

        }
        bw.flush();bw.close();


    }

}
