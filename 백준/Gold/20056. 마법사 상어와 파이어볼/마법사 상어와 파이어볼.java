import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball{
        int r,c,m,s,d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Fireball> list = new ArrayList<>();
        for(int i= 0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Fireball(
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));


        }

        for(int k = 0;k<K;k++) {
            move(list);
            list = new ArrayList<>(process(list));
        }


        System.out.println(list.stream()
                .mapToInt(i -> i.m)
                .sum());
    }

    static List<Fireball> process(List<Fireball> list) {

        final Map<String, List<Fireball>> collect = list.stream()
                .collect(Collectors.groupingBy(fb -> fb.r + " " + fb.c));

        ArrayList<Fireball> result = new ArrayList<>();

        for (Map.Entry<String, List<Fireball>> entry : collect.entrySet()) {


            if (entry.getValue().size() > 1) {
                int sumM = 0;
                int sumS = 0;

                final List<Fireball> value = entry.getValue();
                int r = value.get(0).r;
                int c = value.get(0).c;

                boolean flag = true;
                for (int i = 1; i < value.size(); i++) {
                    if (!flag) {
                        break;
                    }
                    if (value.get(i).d % 2 == value.get(i - 1).d % 2) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }

                for (int i = 0; i < value.size(); i++) {
                    Fireball fireball = value.get(i);
                    sumM += fireball.m;
                    sumS += fireball.s;
                }

                if (sumM / 5 == 0) {
                    continue;
                }

                int s = sumS / value.size();

                if (flag) {
                    for (int d = 0; d < 8; d += 2) {
                        result.add(new Fireball(r, c, sumM / 5, s, d));
                    }
                } else {
                    for (int d = 1; d < 8; d += 2) {
                        result.add(new Fireball(r, c, sumM / 5, s, d));
                    }
                }
            }


            else {
                result.addAll(entry.getValue());
            }

        }

            return result;
    }

    static void move(List<Fireball> list) {

        for (Fireball fireball : list) {

            int y = dy[fireball.d] * fireball.s + fireball.r;
            int x = dx[fireball.d] * fireball.s + fireball.c;

            if(y>=N) {
                y %= N;
            }
            else if (y<0) {
                y=y%N;
                if(y<0) {
                    y+=N;
                }
            }

            if(x>=N) {
                x %= N;
            }
            else if (x<0) {
                x%=N;
                if(x<0) 
                    x+=N;
            }

            fireball.r = y;
            fireball.c = x;
        }

    }
}
