
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Candidate implements Comparable<Candidate> {
        int point;
        int winner;
        int cost;

        public Candidate(int point, int a, int b, int cost) {
            this.point = point;
            this.winner = Math.min(a, b);
            this.cost = cost;
        }

        @Override
        public int compareTo(Candidate o) {
//            if (this.winner == o.winner) {
//                return Integer.compare(this.cost, o.cost);
//            }
            return Integer.compare(o.winner, this.winner);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        var st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        for(int i= 0;i<n;i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Candidate> pq;

        int sum = 0;
        while (list.size() > 1) {
            pq = new PriorityQueue<>();
            for (int j = 1; j < list.size(); j++) {
                pq.add(new Candidate(j - 1, list.get(j - 1), list.get(j),
                    Math.abs(list.get(j - 1) - list.get(j))));
            }
            Candidate candidate = pq.poll();
            sum += candidate.cost;
            ArrayList<Integer> next = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (i == candidate.point) {
                    next.add(candidate.winner);
                } else if (i != (candidate.point + 1)) {
                    next.add(list.get(i));
                }
            }
            list = next;
        }
        System.out.println(sum);

    }

}
