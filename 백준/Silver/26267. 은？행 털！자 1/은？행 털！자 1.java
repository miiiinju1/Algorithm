import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        HashMap<Long, Long> map = new HashMap<>();
        for(int i = 0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            Long X = Long.parseLong(st.nextToken());
            Long T = Long.parseLong(st.nextToken());
            Long C = Long.parseLong(st.nextToken());
            if(map.containsKey(X-T))
                map.replace(X-T,map.get(X-T)+C);
            else
                map.put(X-T,C);
        }
        System.out.println(Collections.max(map.values()));


    }
}
