import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int MIN = 0;
    static final int MAX = 1;
    static final int BRONZE = 0;
    static final int SILVER = 1;
    static final int GOLD = 2;
    static final int PLATINUM = 3;
    static final int DIAMOND = 4;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] ary = new int[2][5];
        ary[MIN][SILVER] = Integer.parseInt(st.nextToken());
        ary[MIN][GOLD] = Integer.parseInt(st.nextToken());
        ary[MIN][PLATINUM] = Integer.parseInt(st.nextToken());
        ary[MIN][DIAMOND] = Integer.parseInt(st.nextToken());
        
        ary[MAX][BRONZE] = ary[MIN][SILVER]-1;
        ary[MAX][SILVER] = ary[MIN][GOLD] - 1;
        ary[MAX][GOLD] = ary[MIN][PLATINUM] -1;
        ary[MAX][PLATINUM] = ary[MIN][DIAMOND]-1;
        ary[MAX][DIAMOND] = ary[MIN][DIAMOND];

        final char[] array = br.readLine().toCharArray();
        long sum = 0;
        int before = 0;
        int temp = -1;
        for (char c : array) {
            switch (c) {
                case 'B':
                    temp = ary[MAX][BRONZE]-before;
                    sum+=temp;
                    before = temp;
                    break;
                case 'S':
                    temp = ary[MAX][SILVER]-before;
                    sum+=temp;
                    before = temp;
                    break;
                case 'G':
                    temp = ary[MAX][GOLD]-before;
                    sum+=temp;
                    before = temp;
                    break;
                case 'P':
                    temp = ary[MAX][PLATINUM]-before;
                    sum+=temp;
                    before = temp;
                    break;
                case 'D':
                    sum += ary[MAX][DIAMOND];
                    before = 0;
                    break;

            }

        }
        System.out.println(sum);
    }
}

