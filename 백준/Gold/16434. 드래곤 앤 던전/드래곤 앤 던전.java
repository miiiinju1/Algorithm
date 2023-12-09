import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean Check(long mid) {
        long maxH = mid;
        long nowH = mid;
        long nowA = initH;
        for (int i = 0; i < N; i++) {
            Room room = dungeon[i];
            //몬스터
            if(room.t==1) {
                //용사 공격력이 몬스터 생명력보다 높은 경우
                   //넘어감
                //공격력이 몬스터 생명력보다 적으면
                if(nowA<room.h){
                    long count = room.h / nowA -1;
                    if(room.h%nowA!=0) {
                        count++;
                    }
                    nowH -= (count*room.a);
                    if (nowH <= 0) {
                        return false;
                    }
                }
            }
            //힐
            else {
                nowA+=room.a;
                nowH += room.h;
                if(nowH>maxH) {
                    nowH = maxH;
                }
            }
        }
        return true;
    }
    static class Room {
        int t, a, h; // 방 타입, 공격력, 생명력
        public Room(int t, int a, int h) {
            this.t = t;
            this.a = a;
            this.h = h;
        }
    }
    static Room[] dungeon;
    static int N, initH;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        initH = Integer.parseInt(st.nextToken());
        
        dungeon = new Room[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dungeon[i] = new Room(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        long lo = 0, hi = Long.MAX_VALUE;
        while(lo+1<hi) {
            long mid = (hi-lo)/2 + lo;
            if (Check(mid)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
        System.out.println(hi);
    }
}

