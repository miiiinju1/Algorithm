import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static class Node implements  Comparable<Node> {
        char c;
        int value;
        int count;

        public Node(char c, int value, int count) {
            this.c = c;
            this.value = value;
            this.count = count;
        }


        @Override
        public int compareTo(Node o) {
            if( o.value*o.count==this.value*this.count)
                return o.value-this.value;
            return o.value*o.count-this.value*this.count;

        }

      
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] ary = new String[N];
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            ary[i] = temp;
            int length = temp.length();
            for (int j = 1; j <= length; j++) {
                int index = length - j;
                char c = temp.charAt(index);
                int add = 1;
                for(int x = 0;x<j;x++) {
                    add*=10;
                }
                map.put(c, map.getOrDefault(c, 0) + (add));
            }

        }
        ArrayList<Character> list = new ArrayList<>();
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> list.add(entry.getKey()));

        for (int j = 0; j < list.size(); j++) {
            char c = list.get(j);
            for (int i = 0; i < N; i++) {
                ary[i] = ary[i].replaceAll(String.valueOf(c),(9-j)+"");
            }
        }
        int sum = 0;
        
        for (String s : ary) {
            sum += Integer.parseInt(s);
        }
        System.out.println(sum);

    }
}
