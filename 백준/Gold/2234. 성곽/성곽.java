import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {1,0,-1,0};
    static int[] dx = {0,1,0,-1};
    static class Point{
        int y,x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static class Room{
        int num, size;

        public Room(int num, int size) {
            this.num = num;
            this.size = size;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] map = new String[M][N];
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j= 0;j<N;j++) {
                map[i][j] = Integer.toBinaryString(Integer.parseInt(st.nextToken()));
                if(map[i][j].length()<4) {
                    int len = map[i][j].length();
                    for(int k= 0;k<4-len;k++) {
                        map[i][j] = "0"+map[i][j];
                    }

                }
            }
        }
        
        int[][] visited = new int[M][N];
        Room[][] rooms = new Room[M][N];

        int count = 0;

        for(int i= 0;i<M;i++) {
            Arrays.fill(visited[i], -1);
        }




        ArrayList<Integer> roomList = new ArrayList<>();

        int index = -1;
        for(int i= 0;i<M;i++) {
            for(int j= 0;j<N;j++) {
                if(visited[i][j]==-1) {
                    index++;
                    visited[i][j] = index;
                    count++;
                    Point start = new Point(i,j);
                    ArrayDeque<Point> q = new ArrayDeque<>();
                    q.add(start);
                    while(!q.isEmpty()) {
                        Point now = q.poll();


                        for(int k= 0;k<4;k++) {
                            if(map[now.y][now.x].charAt(k)=='1') {
                                continue;
                            }
                            int y = now.y+dy[k];
                            int x = now.x+dx[k];

                            if(y>=0&&x>=0&&y<M&&x<N&&visited[y][x]!=index) {
                                count++;
                                visited[y][x] = index;
                                q.add(new Point(y, x));
                            }
                        }
                    }

                    for(int y = 0;y<M;y++) {
                        for(int x = 0;x<N;x++) {
                            if(visited[y][x]== index) {
                                rooms[y][x] = new Room(index, count);
                            }
                        }

                    }
                    roomList.add(count);
                    count = 0;


                }

            }

        }

        System.out.println(roomList.size());

        int sum = 0;
        for (Integer i : roomList) {
            sum = Math.max(i, sum);
        }
        System.out.println(sum);
        int max = 0;
       for(int i= 0;i<M;i++) {
                for(int j= 0;j<N;j++) {
                    for(int k= 0;k<4;k++) {
                      int y= i+dy[k];
                      int x= j+dx[k];
                      if(y>=0&&x>=0&&y<M&&x<N&&rooms[y][x].num!=rooms[i][j].num) {
                          max = Math.max(max, rooms[y][x].size+rooms[i][j].size);
                      }
                    }
                }
            }
        System.out.println(max);
    }
}
