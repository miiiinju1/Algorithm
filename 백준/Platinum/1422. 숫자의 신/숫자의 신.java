
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    static boolean[] visited;
    static int[] res;
    static int[] maxRes;

    static ArrayList<String> result;
    static void dfs(int nowIndex, int count) {
//        System.out.println("count+\" \" = " + count+" ");
//        for(int i= 0;i<res.length;i++) {
//
//            System.out.print(res[i]+" ");
//        }
//        System.out.println();

        if(count==result.size()) {
//            boolean flag = false;
            for(int i = 0;i<res.length;i++) {
                if(maxRes[i]>res[i]) {
                    return;
                }
                else if(maxRes[i]<res[i]){
                    break;
                }
            }
//            if(!flag) {
            for (int j = 0; j < res.length; j++) {
                maxRes[j] = res[j];
            }
//            }
            return;
        }
        a : for (int i = 0; i < result.size(); i++) {
            if(!visited[i]) {
                final String str = result.get(i);
                int length = str.length();

                for (int j = 0; j < length; j++) {
                   if (res[nowIndex + j] < str.charAt(j) - '0') {
                       for(int z = nowIndex+length;z<res.length;z++) {
                           res[z] = 0;
                       }
                       for (int z = 0; z < length; z++) {
                           res[nowIndex + z] = str.charAt(z)-'0';
                       }
                       visited[i] = true;

                       dfs(nowIndex + length, count + 1);
                       visited[i] = false;
                        continue a;
                    }
                }


            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> count = new HashMap<>();

        int max = 0;
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());
            max = Math.max(num, max);
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        result = count.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(entry -> IntStream.range(0, entry.getValue())
                        .mapToObj(i -> entry.getKey().toString()))
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = result.size(); i < N; i++) {
            result.add(String.valueOf(max));
        }


        int length = 0;
        for(int i = result.size()-1;i>=0;i--) {
            length += result.get(i).length();

//            bw.write(result.get(i));
        }
        visited = new boolean[result.size()];
        res = new int[length];
        maxRes = new int[length];

        List<String> sum = new ArrayList<>();

        sum.add(result.get(0));
        a:for (int i = 1; i < result.size(); i++) {
//            System.out.println("sum = " + sum);
            for (int j = 0; j < sum.size(); j++) {
//
//                final long a = Long.parseLong(result.get(i) + sum.get(j));
//                final long b = Long.parseLong(sum.get(j) + result.get(i));

//                System.out.println("a+\" \"+b = " + a+" "+b);

                if ((result.get(i) + sum.get(j)).compareTo(sum.get(j) + result.get(i)) > 0) {

                    sum.add(j, result.get(i));
                    continue a;
                }
            }
            sum.add(result.get(i));
        }

//        dfs(0, 0);

        for(int i =0;i<sum.size();i++) {
            bw.write(sum.get(i) );
        }
        bw.flush();bw.close();
    }
}
