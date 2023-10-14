
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Meat implements Comparable<Meat> {
        long weight, price;

        public Meat(long w, long price) {
            this.weight = w;
            this.price = price;
        }

        @Override
        public int compareTo(Meat o) {
            if(this.price<o.price)
                return -1;
            else if(this.price>o.price)
                return 1;
            else
            {
                return Long.compare(o.weight, this.weight);
            }

        }

    }
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            long M = Long.parseLong(st.nextToken());
            Meat[] meats = new Meat[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                meats[i] = new Meat(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            }

            Arrays.sort(meats);

            long weight = 0;

            int i = 0;
            long now= 0, maxSum = 0;
            for (; i < N; i++) {
                if (weight >= M)
                    break;
                weight += meats[i].weight;

                if (meats[i].price != now) {
                    now = meats[i].price;
                    maxSum = meats[i].price;
                } else if (meats[i].price == now) {
                    maxSum += meats[i].price;
                }
          
            }

            if (weight >= M){
                for(;i<N;i++) {
                    if(meats[i].price>now) {
                        if(maxSum>meats[i].price)
                            maxSum = meats[i].price;
                        break;
                    }
                }

                System.out.println(maxSum);
            }
            else
                System.out.println(-1);
        }


}
