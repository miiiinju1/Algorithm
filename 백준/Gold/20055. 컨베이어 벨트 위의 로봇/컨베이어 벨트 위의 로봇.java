import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int durability;

        boolean box;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("{");
            sb.append("=").append(durability);
            sb.append(", =").append(box);
            sb.append('}');
            return sb.toString();
        }

        public Node(int durability, boolean box) {
            this.durability = durability;
            this.box = box;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Node[] belt = new Node[2*N];

        st = new StringTokenizer(br.readLine());
        for(int i= 0;i<2*N;i++) {
            belt[i] = new Node(Integer.parseInt(st.nextToken()),false);
        }

        int phase = 0;
        int temp =0;
        while(K>temp) {
            rotate(belt);

            move(belt);



            if(belt[0].durability>0 && !belt[0].box) {
                belt[0].box = true;
                belt[0].durability--;
            }

            temp = 0;
            for(int i = 0;i<2*N;i++) {
                if(belt[i].durability==0) {
                    temp++;
                }
            }
            phase++;
         }
        System.out.println(phase);

    }

    static void move(Node[] belt) {
        int N = belt.length/2;
        for(int i = N-1;i>=0;i--) {
            if(i==N-1) {
                belt[i].box = false;
                continue;
            }
            if(belt[i].box && !belt[i+1].box && belt[i+1].durability>0) {
                belt[i].box = false;
                belt[i+1].box = true;
                belt[i+1].durability--;
            }
        }
    }

    static void rotate(Node[] belt) {
        Node last = belt[belt.length - 1];
        for (int i = belt.length - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = last;

        if(belt[belt.length/2].box)
            belt[belt.length/2].box = false;
    }
}

