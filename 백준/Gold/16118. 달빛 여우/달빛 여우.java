import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Node {
        int next, value;
        int doubleValue;
        int halfValue;

        public Node(int next, int value) {
            this.next = next;
            this.value = value;
            this.doubleValue = value << 1;
            this.halfValue = value >> 1;
        }
    }

    static class Wolf implements Comparable<Wolf> {
        int now;
        int value;
        int turn;

        public Wolf(int now, int value, int turn) {
            this.now = now;
            this.value = value;
            this.turn = turn;
        }

        @Override
        public int compareTo(Wolf o) {
            return Integer.compare(this.value, o.value);
        }
    }

    static class Fox implements Comparable<Fox> {
        int now;
        int value;

        public Fox(int now, int value) {
            this.now = now;
            this.value = value;
        }

        @Override
        public int compareTo(Fox o) {
            return Integer.compare(this.value, o.value);
        }
    }

    static Map<Integer, List<Node>> map = new HashMap<>(4000);

    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();

        int N = reader.nextInt();
        int M = reader.nextInt();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>(100));
        }

        for (int i = 0; i < M; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int d = reader.nextInt() << 1;

            map.get(a).add(new Node(b, d));
            map.get(b).add(new Node(a, d));
        }

        int[] fox = new int[N + 1];
        int[][] wolf = new int[2][N + 1];
        Arrays.fill(fox, Integer.MAX_VALUE);
        Arrays.fill(wolf[0], Integer.MAX_VALUE);
        Arrays.fill(wolf[1], Integer.MAX_VALUE);


        PriorityQueue<Fox> foxPq = new PriorityQueue<>();
        PriorityQueue<Wolf> wolfPq = new PriorityQueue<>();

        foxPq.add(new Fox(1, 0));
        wolfPq.add(new Wolf(1, 0, 0));
//        wolf[0][1] = 0;
        fox[1] = 0;

        while (!foxPq.isEmpty()) {
            final Fox now = foxPq.poll();

            if(fox[now.now] < now.value) {
                continue;
            }
//            fox[now.now] = now.value;
            for (Node node : map.get(now.now)) {
                if(fox[node.next]>now.value+node.value) {

                    fox[node.next] = now.value + node.value;
                    foxPq.add(new Fox(node.next, now.value + node.value));
                }
            }
        }

        while (!wolfPq.isEmpty()) {
            final Wolf now = wolfPq.poll();

            if(wolf[(now.turn+1)%2][now.now] < now.value) {
                continue;
            }

            if (now.turn % 2 == 0) { // 2배 속도
                for (Node node : map.get(now.now)) {
                    if(wolf[0][node.next] > now.value+node.halfValue) {
                        wolf[0][node.next] = now.value+node.halfValue;
                        wolfPq.add(new Wolf(node.next, now.value + node.halfValue, 1));
                    }

                }
            } else { // 반 속도
                for (Node node : map.get(now.now)) {
                    if(wolf[1][node.next] > now.value+node.doubleValue) {
                        wolf[1][node.next] = now.value+node.doubleValue;
                        wolfPq.add(new Wolf(node.next, now.value + node.doubleValue, 0));
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (fox[i] < Math.min(wolf[0][i], wolf[1][i])) {
                sum++;
            }
        }
        System.out.println(sum);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din != null) {
                din.close();
            }
        }
    }
}