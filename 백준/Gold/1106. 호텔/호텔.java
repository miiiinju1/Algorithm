
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        // 9원 3명
        /*

        9원 3ㅕㅁㅇ


        18원 6명

        만약 3이라면


        암튼 9원이 답임


        해당 원으로 늘릴 수 있는 최대 고객을 dp로 관리

        N은 최대 20, 가장 많이 쓸 수 있는 돈은 얼마?

        100 * 1000 = 100,000

        dp[100,001];
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_001];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int originalCost = Integer.parseInt(st.nextToken());
            int originalValue = Integer.parseInt(st.nextToken());

            int cost = originalCost;
            int value = originalValue;
            int valueSum = 0;
            // value를 두 배씩 해서 C까지
            for (; ; ) {
//                System.out.println("cost + \" \" + value = " + cost + " " + value);
                for (int j = 100_000; j >= 0; --j) {
                    if (dp[j] != -1) {
                        int next = j + cost;
                        if (next < 100_001) {
                            dp[next] = Math.max(dp[next], dp[j] + value);
                        }
                    }
                }
                valueSum += value;
                cost *= 2;
                value *= 2;
                if (valueSum + value >= C) {
                    int count = ((C - valueSum) / originalValue);

                    if (count == 0 && (C - valueSum) % originalValue == 0) {
                        break;
                    }
                    cost = originalCost * (count + 1);

                    value = originalValue * (count + 1);

//                    System.out.println("ddcost + \" \" + value = " + cost + " " + value);
                    for (int j = 100_000; j >= 0; --j) {
                        if (dp[j] != -1) {
                            int next = j + cost;
                            if (next < 100_001) {
                                dp[next] = Math.max(dp[next], dp[j] + value);
                            }
                        }
                    }
                    break;
                }
            }
        }

//        for(int i = 0;i<1000;++i) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println("baekjoon1106_호텔.main");
        for (int i = 0; i < 100_001; ++i) {

            if (dp[i] >= C) {
                System.out.println(i);
                return;
            }
        }

    }

}

//5 3
//1 5
//6 5
//2 1