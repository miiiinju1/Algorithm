
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int y, x,exit,num;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", exit=").append(exit);
            sb.append(", num=").append(num);
            sb.append('}');
            return sb.toString();
        }

        public Node(int y, int x, int exit, int num) {
            this.y = y;
            this.x = x;
            this.exit = exit;
            this.num = num;

        }
    }


    static boolean isValid(int y, int x) {
        return x>=0&&y<R&&x<C;
    }
    static boolean canGoUnder(int y, int x, int k) {

        if(!isValid(y,x)) {
            return false;
        }

        for(int d = 0;d<4;d++) {
            int Y = dy[d]+y;
            int X = dx[d]+x;

            if(!isValid(Y,X)) {
                return false;
            }
        }


        if(y>=0) {
            if(map[y][x]!=k) {
                if(map[y][x]!=0) {
                    return false;
                }
            }
        }

        for(int d = 0;d<4;d++) {
            int Y = dy[d]+y;
            int X = dx[d]+x;
            if(Y>=0) {
                if(map[Y][X]!=k) {
                    if(map[Y][X]!=0) {
                        return false;
                    }
                }
            }
        }

        return true;

    }
    static int[][] map;

    static void marking(int y, int x,int k) {

        if(y>=0 && isValid(y,x)) {
            map[y][x] = k;
        }

        for(int d =0;d<4;d++) {
            int Y = dy[d]+y;
            int X = dx[d]+x;
            if(Y>=0&&isValid(Y,X)) {
                map[Y][X] = k;
            }
        }
    }

    static boolean isGoingOutside(int y, int x) {

        if(y<0) {
            return true;
        }

        for(int d =0;d<4;d++) {
            int Y = dy[d]+y;
            int X = dx[d]+x;
            if(Y<0 || !isValid(Y,X)) {
                return true;
            }
        }
        return false;

    }

    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static HashMap<Integer, Node> hashMap = new HashMap<>();
    static int R, C, K;
    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        map = new int[R][C];
        int sum = 0;
        for(int k=1;k<=K;k++) {

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());


            int testY= -2;
            int testX = x;
            while (true) {

                if (canGoUnder(testY+1, testX, k)) {
                    testY +=1;
                }
                else if(canGoUnder(testY, testX-1, k) && canGoUnder(testY+1, testX -1, k)) {
                        testY+=1;
                        testX-=1;
                        d-=1;
                        if(d<0)
                            d+=4;
                }
                else if(canGoUnder(testY, testX+1, k) && canGoUnder(testY+1, testX +1, k)) {
                        testY+=1;
                        testX+=1;
                        d = (d+1)%4;
                }
                else {
                    break;
                }

            }

            if(!isGoingOutside(testY, testX)) {
                Node node = new Node(testY, testX, d, k);

                marking(testY,testX,k);

                hashMap.put(k, node);

                sum+= getGrade(node);
            }
            else {

                map= new int[R][C];
                hashMap = new HashMap<>();
            }
        }
        System.out.println(sum);

    }

    static void Print() {
        for(int i= 0;i<R;i++) {
            System.out.println();
            for(int j= 0;j<C;j++) {
                System.out.printf("%2d ",map[i][j]);
            }
        }
    }
    static int getGrade(Node node) {

        boolean[] visited = new boolean[K+1];
        ArrayDeque<Node> q = new ArrayDeque<>();

        visited[node.num] = true;
        q.add(node);
        int max = 0;
        while(!q.isEmpty()) {
            Node now = q.poll();

            max = Math.max(now.y+1, max);

            int Y = now.y+dy[now.exit];
            int X = now.x+dx[now.exit];

            if(Y>=0 && isValid(Y,X)) {

                for(int d = 0;d<4;d++) {
                    int y = Y + dy[d];
                    int x = X + dx[d];


                    if(y>=0 && isValid(y,x)) {

                        if(map[y][x] != now.num && map[y][x] != 0 && !visited[map[y][x]]) {
                            q.add(hashMap.get(map[y][x]));
                            visited[map[y][x]] = true;
                        }

                    }

                }


            }


        }
        return max+1;

    }
}
