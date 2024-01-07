import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Tree implements Comparable<Tree>{
        int height, d;

        public void setD(int d) {
            this.d = d;
        }

        public Tree(int height, int d) {
            this.height = height;
            this.d = d;
        }

        @Override
        public int compareTo(Tree o) {
            if(o.d==this.d) {
                return Integer.compare(o.height, this.height);
            }
            return Integer.compare(o.d, this.d);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Tree[] trees = new Tree[n];
        for(int i= 0;i<n;i++) {
            trees[i] = new Tree(Integer.parseInt(st.nextToken()), 0);
        }
        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<n;i++) {
            trees[i].setD(Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(trees);

        long sum = 0;

        for (int i = 0; i < n; i++) {

            sum += (trees[i].height + (trees[i].d * (n - i - 1)));
        }
        System.out.println(sum);

    }
}

