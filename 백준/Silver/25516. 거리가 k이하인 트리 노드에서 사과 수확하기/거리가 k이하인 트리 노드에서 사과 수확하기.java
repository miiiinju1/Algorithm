
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] tree;
    static int[] apple;
    static int sum = 0;
    static int k;
    static void search(int start,int depth) {

        if(depth>k)
            return ;
        sum+=apple[start];
        apple[start]=0;
        for(Integer i : tree[start]) {
            search(i, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        apple = new int[n];
        tree = new List[n];

        for(int i=0;i<n;i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 1;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[p].add(c);
        }

        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<n;i++){
            apple[i] = Integer.parseInt(st.nextToken());
        }

        search(0,0);
        System.out.println(sum);

    }
}
