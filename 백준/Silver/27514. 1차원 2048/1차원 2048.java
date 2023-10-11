import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<Long, Integer> map;
    static void put(Long val)
    {
        if (!map.containsKey(val)) 
            map.put(val, 1);
        
        else {
            map.remove(val);
            put(2 * val);
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            arr[i]=Long.parseLong(st.nextToken());
        map = new HashMap<>();
        for(int i= 0;i<N;i++)
            put(arr[i]);
        
        System.out.println(Collections.max(map.keySet()));
    }
}
