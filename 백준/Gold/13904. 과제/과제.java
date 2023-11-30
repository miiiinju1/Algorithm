
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Assignment implements Comparable<Assignment> {
        int due;
        int weight;

        public Assignment(int due, int weight) {
            this.due = due;
            this.weight = weight;
        }

        @Override
        public int compareTo(Assignment other) {
            return other.weight - this.weight; // 점수가 높은 과제 순으로 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 과제 개수

        List<Assignment> assignmentList = new ArrayList<>();
        boolean[] occupied = new boolean[1001]; // 각 날짜에 과제가 잡혔는지 여부
        int ans = 0; // 총 점수

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int due = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            assignmentList.add(new Assignment(due, weight));
        }

        Collections.sort(assignmentList); // 과제를 점수가 높은 순으로 정렬


        for(Assignment assignment : assignmentList) {
            for(int day = assignment.due;day>0;day--) {
                if(!occupied[day]) {
                    occupied[day]= true;
                    ans += assignment.weight;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}
