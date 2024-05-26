
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Process {
        char name;
        int value;

        public Process(char name, int value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Process{");
            sb.append("name=").append(name);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }

    static int[] visited = new int[26];
    static void dfs(char now, int value) {
//        System.out.println("now = " + now);

        for (Character process : map.get(now)) {

//            System.out.println("process = " + process);
            if (visited[(process - 'A')] < value + values[process-'A']) {
                visited[process - 'A'] = value + values[process - 'A'];
                dfs(process, value + values[process - 'A']);
            }

        }

    }

    static int[] values;
    static Map<Character, List<Character>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Set<Character> start = new HashSet<>();
//        Arrays.fill(visited, Integer.MAX_VALUE);
        for(int i = 0;i<26;i++) {
            start.add((char)('A' + i));
            map.put((char) ('A' + i), new ArrayList<>());
        }

       values = new int[26];
        String str;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            StringTokenizer st = new StringTokenizer(str);

            if (st.countTokens() == 0) {
                break;
            }

            char name = st.nextToken().charAt(0);
            int value = Integer.parseInt(st.nextToken());

            values[name-'A'] = value;
            if(!st.hasMoreTokens()) {
                continue;
            }

            String before = st.nextToken();

            for(int i = 0;i<before.length();i++) {
                start.remove(before.charAt(i));
                map.get(name).add(before.charAt(i));
            }


        }
//        System.out.println(map);
        for (Character s : start) {

            if (values[s - 'A'] != Integer.MAX_VALUE && visited[s - 'A'] < values[s - 'A']) {

                visited[s - 'A'] = values[s - 'A'];
                dfs(s, values[s - 'A']);
            }


        }

        int max = 0;
        for(int i= 0;i<26;i++) {
            max = Math.max(max, visited[i]);
        }
        System.out.println(max);






    }
}
