import java.io.*;
import java.util.*;


public class Main {
    static class Result {
        int types, count;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(types).append(" ").append(count).append("\n");
            return sb.toString();
        }

        public Result(int types, int count) {
            this.types = types;
            this.count = count;
        }
    }
    static Map<String, Integer> search(String now, StringBuilder sb) {
        HashMap<String, Integer> result = new HashMap<>();
        sb.append(now);
        /*
        * 폴더 탐색해서 더하기
        * */
        for (String folder : folders.get(now)) {
            final Map<String, Integer> search = search(folder, new StringBuilder(sb).append("/"));
            for (Map.Entry<String, Integer> entry : search.entrySet()) {
                result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }
        /*
        * 하위 폴더 + 현재 폴더의 파일들
        * */

        for (String file : files.get(now)) {
            result.put(file, result.getOrDefault(file, 0) + 1);
        }

        /*
        * 통계
        * */
        int types = result.keySet().size();
        int c = result.values().stream().mapToInt(i-> i).sum();

        /*
        * 결과 저장
        * */
        count.put(sb.toString(), new Result(types, c));

        return result;

    }

    static Map<String, Result> count = new HashMap<>();

    /*
    * key: 폴더 명
    * values: 하위 폴더 명
    * */
    static Map<String, List<String>> folders = new HashMap<>();
    /*
     * key : 폴더 명
     * values : 파일들
     * */
    static HashMap<String, List<String>> files = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i= 0;i<N+M;i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            folders.computeIfAbsent(a, k -> new ArrayList<>());
            files.computeIfAbsent(a, k -> new ArrayList<>());

            boolean isFolder = st.nextToken().equals("1");
            if(isFolder) {
                folders.computeIfAbsent(b, k -> new ArrayList<>());
                folders.get(a).add(b);
                files.computeIfAbsent(b, k -> new ArrayList<>());
            }
            else {
                files.get(a).add(b);
            }
        }

        search("main", new StringBuilder());

        int q = Integer.parseInt(br.readLine());
        for(int i= 0;i<q;i++) {
            bw.write(count.get(br.readLine()).toString());
        }
        bw.flush();bw.close();
    }
}
