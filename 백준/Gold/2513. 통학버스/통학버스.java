
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Apartment  {
        int index, count;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Apartment{");
            sb.append("index=").append(index);
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        public Apartment(int index, int count) {
            this.index = index;
            this.count = count;
        }


    }
    static List<Apartment> left = new ArrayList<>();
    static List<Apartment> right = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            if(index > S)
                right.add(new Apartment(index, count));
            else {
                left.add(new Apartment(index, count));
            }
        }

        Collections.sort(left, (o1, o2) -> Integer.compare(o1.index, o2.index));
        Collections.sort(right, (o1, o2) -> Integer.compare(o2.index, o1.index));

        long sum = 0;


        int index = 0;
        while (index < left.size()) {

            int temp = 0;
            sum += (S - left.get(index).index) * 2L;

            while (true) {
                if (index >=left.size()) {
                    break;
                }
                if (temp >= K) {
                    break;
                }
                Apartment apt = left.get(index);
                if (temp + apt.count <= K) {
                    index++;
                    temp += apt.count;
                } else {
                    apt.count = (temp + apt.count-K);
                    temp = K;
                }
//

            }
        }
        index = 0;
        while (index < right.size()) {

            int temp = 0;
            sum += (right.get(index).index-S) * 2L;

            while (true) {
                if (index >= right.size()) {
                    break;
                }
                Apartment apt = right.get(index);
//                temp += apt.count;
                if (temp + apt.count <= K) {
                    index++;
                    temp += apt.count;
                } else {
                    apt.count = (temp + apt.count-K);
                    temp = K;
                }
                if (temp >= K) {
                    break;
                }

            }

        }

        System.out.println(sum);




    }
}
