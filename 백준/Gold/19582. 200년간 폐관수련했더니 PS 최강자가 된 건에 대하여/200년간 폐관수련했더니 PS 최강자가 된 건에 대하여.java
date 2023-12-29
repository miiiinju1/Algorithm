import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int indexCheck = N;
        long prizeSumStore = 0;
        long maxValueStore = -1;


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long limitNum = Long.parseLong(st.nextToken());
            long prize = Long.parseLong(st.nextToken());
            
            // 상한 초과
            if (prizeSumStore > limitNum) {
                indexCheck = i + 1;

                if (maxValueStore > prize) {
                    if (maxValueStore >= prizeSumStore - limitNum) {
                        prizeSumStore = prizeSumStore - maxValueStore + prize;
                    }
                }
                break;
            }
            // 넘지 않는 경우
            prizeSumStore += prize;
            maxValueStore = Math.max(maxValueStore, prize);
        }

        for (int i = indexCheck; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long limitNum = Long.parseLong(st.nextToken());
            long prize = Long.parseLong(st.nextToken());

            if (prizeSumStore > limitNum) {
                System.out.println("Zzz");
                return;
            } else {
                prizeSumStore += prize;
            }
        }

        System.out.println("Kkeo-eok");

    }
}

//9
//1 1
//2 2
//4 3
//7 4
//11 5
//14 27
//16 1
//17 1
//18 1


//6
//1 3
//4 5
//9 5
//7 4
//13 2
//15 3

//6
//1 3
//4 5
//9 5
//8 100
//13 2
//15 3

//4
//10000 6000
//6000 2000
//3000 3000
//5000 10000