
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static class Party {
        int num, count;

        public Party(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;


        List<Party> parties = new ArrayList<>(N);
        for (int i = 1; i <= N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            sum += temp;

            parties.add(new Party(i, temp));

        }

        final int half = sum/2;

        final ArrayList<Party> collect = parties.stream()
                .filter(p -> p.count <= half)
                .sorted((o1, o2) -> Integer.compare(o2.count, o1.count))
                .collect(Collectors.toCollection(ArrayList::new));


        boolean[] value = new boolean[100001];
        boolean[][] dp = new boolean[100001][N+1];
//        dp[0] = true;

        value[0]= true;
//        dp[0][0] = true;
        for (int i = 0; i < collect.size(); i++) {
//            System.out.println(collect.get(i).num);
            for (int j = half; j >= 0; j--) {
                if (value[j]) {
//                    System.out.println(j+" "+collect.get(i).count);
                    value[j + collect.get(i).count] = true;

                    for (int z = 0; z <= N; z++) {
                        dp[j + collect.get(i).count][z] = dp[j][z];
                    }
                    dp[j + collect.get(i).count][collect.get(i).num] = true;
                }
            }
        }
//
//        for(int i = 0;i<20;i++) {
//            System.out.print(dp[i]+" ");
//        }
        int max = 0;
        for(int i= 100000;i>=0;i--) {

            if(value[i]) {
                max=i;
                break;
            }
        }

//        System.out.println("max = " + max);
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i = 0;i<=N;i++) {
//            System.out.println(dp[max][i]+" ");
            if(dp[max][i]) {
                ++count;
                sb.append(i).append(" ");
            }
        }

        System.out.println(count);
        System.out.println(sb);



    }
}
