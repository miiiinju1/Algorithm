import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static HashSet<String> visited = new HashSet<>();
    static void findA(String now) {
        if(map.get(now).isEmpty()) {
             visited.add(now);
            return ;
        }
        for (String s : map.get(now)) {
                findA(s);
            }
        }

    static boolean findB(String now) {
        if(map.get(now).isEmpty()) {
            if(visited.contains(now)) {
                return true;
            }
        }
        for (String s : map.get(now)) {
            if(findB(s)) {
                return true;
            }
        }
        return false ;
    }

    static HashMap<String, ArrayList<String>> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i= 1;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String A = st.nextToken();
            String B = st.nextToken();

            if(!map.containsKey(B)) {
                map.put(B, new ArrayList<>());
            }
            if(!map.containsKey(A)) {
                map.put(A, new ArrayList<>());
            }
            map.get(B).add(A);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());

        String A = st.nextToken();
        String B = st.nextToken();
        findA(A);
        if(findB(B)) {
            System.out.println(1);
            return ;
        }
        System.out.println(0);

    }
}

/*

자식 -> 부모 방향으로 탐색할 수 있게 하면 될듯?
*
*
*
*   integer - number - object
*                     /
*               string
*
*
*
*    number - object
*             /
*       string
*
*
*
* */