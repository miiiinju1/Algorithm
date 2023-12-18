import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Main {


    static int[] result = new int[10];

    static HashSet<Long> set = new HashSet<>();

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
            set.add(Long.parseLong(sb.toString()));
            combination(index+1,i);
        }

    }
    static int target;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        combination(0,-1);
        List<Long> list = set.stream().sorted().collect(Collectors.toList());
                //collect(Collectors.toCollection(ArrayList::new));

        if(list.size()<=target) {
            System.out.println(-1);
        }
        else {
            System.out.println(list.get(target));
        }

    }
}
