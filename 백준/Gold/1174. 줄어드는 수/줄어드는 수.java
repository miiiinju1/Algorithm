import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {


    static int[] result = new int[10];

    static PriorityQueue<Long> pq = new PriorityQueue<>();

    static void combination(int index, int start) {
        if(index==10) {
            return ;
        }
        for(int i = start+1;i<10;i++) {
            result[index] = i;
            StringBuilder sb = new StringBuilder();
            for(int j =index;j>=0;j--) {
                sb.append(result[j]);
            }
            pq.add(Long.parseLong(sb.toString()));
            combination(index+1,i);
        }

    }
    static int target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine())-1;
        combination(0,-1);
        List<Long> list = pq.stream().distinct().sorted().collect(Collectors.toList());
        if(list.size()<=target) {
            System.out.println(-1);
        }
        else {
            System.out.println(list.get(target));
        }

    }
}
