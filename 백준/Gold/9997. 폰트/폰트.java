import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    static int[] strCount(String str) {
        int[] ary = new int[26];
        for(int i= 0;i<str.length();i++) {
            ary[str.charAt(i)-'a']+=1;
        }
        return ary;
    }

    static int[][] strings;
    static int N;
    static int count = 0;

    static boolean check(int[] countMap) {
        for(int i= 0;i<26;i++) {
            if(countMap[i]<=0) {
                return false;
            }
        }
        return true;
    }

    static void search(int depth, int[] countMap) {
        if(depth == N) {
            //만약 알파벳 전체 다 나왔으면
            if(check(countMap)) {
                count+=1;
            }
            return;
        }

        // 그냥 넘기기
        search(depth+1, countMap);

        // 현재 알파벳 더해서 넘기기
        search(depth + 1, addMap(countMap, depth));
    }


    static int[] addMap(int[] map1, int depth) {
        int[] map = new int[26];

        for(int i= 0;i<26;i++) {
            map[i] = map1[i]+strings[depth][i];
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         N = Integer.parseInt(br.readLine());
        strings = new int[N][26];
        for(int i= 0;i<N;i++) {
            strings[i] = strCount(br.readLine());
        }


        search(0, new int[26]);
        System.out.println(count);


    }
}
