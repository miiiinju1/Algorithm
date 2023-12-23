import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

    static long count = 0;


    static void search(int now, long depth) {
//        if(visited[now]) {
//            return ;
//        }
        if(!visited[now]&&now!=1&&map.get(now).size()==1) {
            count+=depth;
        }
//        System.out.println("now = " + now);
        visited[now] = true;

        for (Integer next : map.get(now)) {
            if(!visited[next]) {
                search(next, depth + 1);
                visited[next]= true;
            }
        }
    }
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();

        for(int n = 1;n<=N;n++) {
            map.put(n, new ArrayList<>());
            set.add(n);
        }

        visited = new boolean[N + 1];
        visited[0] = true;



        for(int i = 1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        search(1,0);
//        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//
//        System.out.println(count);

        if(count%2==1) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }

    }
}

//개수가

// 성원 - 형석 - 성원 -형석
//
// 짝수

//1 - 2 -3
 ///    \
//        4

//
//    3 - 2
//  /
//1 - 4 - 6 - 5
//  \   \
//    8   7


//6
//1 2
//1 3
//1 4
//1 5

//1 - 3 - 2
//     \
//      4


//4
//3 2
//1 3
//3 4