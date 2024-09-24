
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Map<Character, Set<Character>> res = new HashMap<>();
    static Map<Character, Set<Character>> map = new HashMap<>();
    static List<char[]> result;
    static boolean[][] visited;

    // -> 현재 이 풀이는 cycle을 탐지하지 못 함, 왜냐면 a -> b , b -> a가 있다고 할 때, 일단 하나로 진입을 못 해버림

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        
//        int[] entryCount = new int[123];

        visited = new boolean[123][123];

//        Arrays.fill(entryCount, -1);
        
        for (int i = 0; i < 26; ++i) {
            res.put((char) ('a' + i), new HashSet<>());
            res.put((char) ('A' + i), new HashSet<>());
            map.put((char) ('a' + i), new HashSet<>());
            map.put((char) ('A' + i), new HashSet<>());
//            entryCount['a' + i] = 0;
//            entryCount['A' + i] = 0;
        }
        // 122 - 65  + 1개

        // 123- 65 58
        // 1 10까지라고 한다면 10개임

        // 최소화하려면

        for (int i = 0; i < N; ++i) {
            var st = new StringTokenizer(br.readLine(), " => ");
            char from = st.nextToken().charAt(0);
            char to = st.nextToken().charAt(0);

            if(from==to) continue;

//            ++entryCount[to];
            map.get(from).add(to);
        }

        Deque<Character> q = new ArrayDeque<>();

//        for(int i = 0;i<entryCount.length;++i) {
//            if(entryCount[i] == 0) {
//                char c = (char) i;
//                q.add(c);
//            }
//        }

        result = new ArrayList<>();
        for (Character c : map.keySet()) {
            visited[c][c] = true;
            dfs(c, c);
        }

//        while(!q.isEmpty()) {
//            Character now = q.poll();
//
//            // 도착하면 res.get(now)부터 now로 오는 쌍 다 넣어놓기
//            for (Character c : res.get(now)) {
//                result.add(new char[]{c, now});
//            }
//
//            for (Character next : map.get(now)) {
//                --entryCount[next];
//                if (entryCount[next] == 0) {
//                    q.add(next);
//                    // now -> next로 다 옮겨줘야함
//
//                    Set<Character> from = res.get(now);
//                    Set<Character> to = res.get(next);
//                    to.addAll(from);
//                    to.add(now);
//                }
//
//            }
//
//
//        }

        result.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Character.compare(o1[1], o2[1]);
            }
            return Character.compare(o1[0], o2[0]);

        });

        bw.write(result.size() + "\n");
        for (char[] chars : result) {
            bw.write(chars[0] + " => " + chars[1] + "\n");
        }
        bw.flush();bw.close();


    }

    static void dfs(Character now, char start) {

        for (Character next : map.get(now)) {
            if(!visited[start][next]) {
                visited[start][next] = true;
                result.add(new char[]{start, next});
                dfs(next, start);
            }
        }
    }


    // 그냥 노드 별로

    // dfs 접근한다고 생각.

    //  A -> B -> C -> D
    /*


   A -> B, A-> C, A-> D이렇게 가야할텐데


   A -> B로 갈 때 A를 같이 파라미터로 넘기면서

    B -> C로 갈 때, A, B로 같이 넘기면

    1번만 탐색하면서 모든 쌍을 다 넣을 수 있음 그리고

     A -> B -> C
        /
     D

     이렇게 간다고 할 때, A -> B를 방문처리 해야됨 아니면, D -> B로 못 오게 됨



    근데 그것보다도 위상정렬하면 편할듯?

    진입 차수 0인 거 큐에 다 넣고 탐색하다가 
    
    이게 진입 차수 세고 있다가, 0이 되면 넣는거였던거같은데
    
    
    




     */



}
