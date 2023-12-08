
import java.io.*;
import java.util.*;

public class Main {
    private static boolean isBiggerCombination(HashMap<Integer, ArrayList<Integer>> map, int currentIndex, int previousIndex, int newNumber) {
        ArrayList<Integer> currentList = new ArrayList<>(map.get(currentIndex));
        Collections.sort(currentList, Collections.reverseOrder());

        ArrayList<Integer> previousList = new ArrayList<>(map.get(previousIndex));
        previousList.add(newNumber);
        Collections.sort(previousList, Collections.reverseOrder());

        // 두 리스트를 문자열로 변환하여 비교
        String currentString = listToString(currentList);
        String previousString = listToString(previousList);

        return previousString.compareTo(currentString) > 0;
    }

    private static String listToString(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num);
        }
        return sb.toString();
    }
    static class Number implements Comparable<Number> {
        int number,price;
        public Number(int n, int p) {
            this.number = n;
            this.price = p;
        }

        @Override
        public int compareTo(Number o) {
            if(this.number == o.number) {
                return this.price - o.price;
            }
            return o.number - this.number;

        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Number{");
            sb.append("number=").append(number);
            sb.append(", price=").append(price);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Number[] ary = new Number[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i= 0;i<N;i++) {
            ary[i] = new Number(i, Integer.parseInt(st.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[M + 1];
        Arrays.sort(ary);
        Arrays.fill(dp, -1);
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        for(int i= 0;i<=M;i++) {
            map.put(i, new ArrayList<>());
        }

        int cant = 0;
        for (int i = 0; i < N; i++) {
            if(ary[i].number!=0) {
                if(ary[i].price<=M&&dp[ary[i].price]==-1) {
                    map.get(ary[i].price).add(ary[i].number);
                    dp[ary[i].price] = 1;
                }
            }
            else {
                if(ary[i].price<=M&&dp[ary[i].price]==-1) {
                    dp[ary[i].price] = 1;
                    map.get(ary[i].price).add(0);
                    cant = ary[i].price;
                }
            }
        }


        for(int i =0;i<=M;i++) {
            for(int j = 0;j<N;j++) {
                if (i - ary[j].price < 0) {
                    continue;
                }

                if (!(i-ary[j].price==cant && ary[j].number==0)) {
                    if (dp[i - ary[j].price] != -1) {
                        int newLength = dp[i - ary[j].price] + 1;
                        if (newLength > dp[i] || (newLength == dp[i] && isBiggerCombination(map, i, i - ary[j].price, ary[j].number))) {
                            dp[i] = newLength;

                            ArrayList<Integer> newList = new ArrayList<>(map.get(i - ary[j].price));
                            newList.add(ary[j].number);
                            Collections.sort(newList, Collections.reverseOrder()); // 내림차순으로 정렬
                            map.put(i, newList);
                        }
                    }

                }
            }




        }

        String maxCombination = "";
        int maxIndex = -1;
        int maxLength = 0;

        for (int i = 1; i <= M; i++) {
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxCombination = ""; // 새로운 최대 길이가 발견되었으므로 maxCombination을 초기화합니다.
            }

            if (dp[i] == maxLength) { // dp[i]가 최대 길이와 같은 경우에만 처리
                ArrayList<Integer> list = map.get(i);
                Collections.sort(list, Collections.reverseOrder()); // 리스트를 내림차순 정렬

                StringBuilder currentCombination = new StringBuilder();
                for (Integer num : list) {
                    currentCombination.append(num);
                }

                String currentString = currentCombination.toString();
                if (currentString.compareTo(maxCombination) > 0) {
                    maxCombination = currentString;
                    maxIndex = i;
                }
            }
        }

        ArrayList<Integer> list = maxIndex != -1 ? map.get(maxIndex) : new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (Integer integer : list) {
            sb.append(integer);
        }
        System.out.println(sb);



    }
}
