import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static char[][] map ;

    static Character[] nominate;
    static Character[] select;
    static int max=0;

    static int check() {
        HashSet<Character> set = new HashSet<>(List.of('a', 'n', 't', 'i', 'c'));
        set.addAll(Arrays.asList(select));

        int count = 0;
        a:for(int i= 0;i<N;i++) {
            for(int j=0;j<map[i].length;j++) {
                if(!set.contains(map[i][j])) {
                    continue a;
                }
            }
            count++;
        }
        return count;

    }
    static void combination(int startIndex, int index) {
        if(startIndex==0) {
            select = new Character[K];
        }
        if(index== K) {
            int count = check();
            if(max<count) {
                max = count;
            }
            return ;
        }
        for(int i = startIndex;i<nominate.length;i++) {
            select[index] = nominate[i];
            combination(i + 1, index + 1);
        }
    }
    static int N,K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][7];
        if(K<5) {
            System.out.println(0);
            return ;
        }

        HashSet<Character> set = new HashSet<>();
        for(int i= 0;i<N;i++) {
            String str = br.readLine();
            map[i] = str.substring(4, str.length() - 4).toCharArray();
            for (char c : map[i]) {
                set.add(c);
            }
        }
        List.of('a', 'n', 't', 'i', 'c').forEach(set::remove);

        nominate = set.toArray(Character[]::new);
        K = Math.min(K-5, nominate.length);
        combination(0, 0);

        System.out.println(max);

    }
}
