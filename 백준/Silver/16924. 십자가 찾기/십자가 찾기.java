import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static class Answer {
        int y, x, size;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append(y);
            sb.append(" ").append(x);
            sb.append(" ").append(size);;
            return sb.toString();
        }

        public Answer(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }

    static void draw (int y, int x) {
        if(map[y][x]!='*') {
            return;
        }
        int up= 0, down = 0, left = 0, right = 0;
        int i = y-1, j = x;
        while(i>=0&&map[i][j]=='*') {
            i--;
        }

        up = y-(i+1);

        i = y+1;j = x;
        while(i<N&&map[i][j]=='*') {
            i++;
        }
        down = (i-1)-y;

        i = y; j = x-1;
        while(j>=0&&map[i][j]=='*') {
            j--;
        }
        left = x-(j+1);

        i = y; j = x+1;

        while(j<M&&map[i][j]=='*') {
            j++;
        }
        right = (j-1)-(x);
//        System.out.println("y = " + y);
//        System.out.println("x = " + x);
//        System.out.println("left = " + left);
//        System.out.println("right = " + right);
//        System.out.println("up = " + up);
//        System.out.println("down = " + down);
//        System.out.println("=-----------");
        int min = Math.min(Math.min(left, right), Math.min(up, down));

        if(min == 0 ) {
            return ;
        }

        for(int z = 0;z<=min;z++) {
            visited[y+z][x]= true;
            visited[y-z][x]= true;
            visited[y][x+z]= true;
            visited[y][x-z]= true;
        }
        answer++;
        ans.add(new Answer(y+1,x+1,min));
    }
    static int answer = 0;
    static ArrayList<Answer> ans = new ArrayList<>(
    );
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i= 0;i<N;i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                if(map[i][j]=='*')
                draw(i,j);
            }
        }

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
//                System.out.print(visited[i][j]+" ");
                if(map[i][j]=='*'&&!visited[i][j]) {
                    System.out.println(-1);
                    return ;
                }

            }
//            System.out.println();
        }
        System.out.println(answer);

        for (Answer an : ans) {
            bw.write(an+"\n");
        }
        bw.flush();
bw.close();
    }
}
//5 7
//..*.*..
//.*****.
//***.***
//.*****.
//..*.*..