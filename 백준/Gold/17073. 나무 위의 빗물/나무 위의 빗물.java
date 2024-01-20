
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int count=0;
    static boolean[] visited;

    static void search() {

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            final Integer now = q.poll();

            if(now!=1&&map.get(now).size()==1) {
                count++;
                continue;
            }
            for (Integer i : map.get(now)) {
                if(!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        for(int i= 1;i<=N;i++) {
            map.put(i, new ArrayList<>());
        }


        for(int i= 1;i<N;i++) {
             st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
        }

        search();

        System.out.println((double)W/count);


        
    }
}


//5 0
//4 1
//3 2
//2 3
//1 4
//0 5

//
//
//


//20
//1 19
//  1 18


// 20 /3  6.7