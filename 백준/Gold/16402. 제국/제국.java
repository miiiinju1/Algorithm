
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static Map<String, String> parent = new HashMap<>();

    static String find(String v) {
        if (parent.get(v).equals(v)) {
            return v;
        }
        parent.replace(v, find(parent.get(v)));
        return parent.get(v);
    }

    static void union(String a, String b, int w) {
        String fa = find(a);
        String fb = find(b);

        if (!fa.equals(fb)) {

            if(w == 1) {
                parent.replace(fb, fa);
            }
            else {
                parent.replace(fa, fb);
            }
        }
        else {
            if(fa.equals(a)) {
                if(w==2) {
                    parent.replace(fa, b);
                    parent.replace(b, b);
                }
            }
            else if(fb.equals(b)) {
                if(w==1) {
                    parent.replace(fb, a);
                    parent.replace(a, a);
                }
            }
        }
    }

    static String getKingdomName(String str) {
        StringTokenizer st = new StringTokenizer(str);
        st.nextToken();
        st.nextToken();
        return st.nextToken();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            String name = getKingdomName(br.readLine());
            parent.put(name, name);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            String nameA = getKingdomName(st.nextToken());
            String nameB = getKingdomName(st.nextToken());

            int w = Integer.parseInt(st.nextToken());
            union(nameA, nameB, w);
        }

//        System.out.println(parent);

        StringBuilder sb = new StringBuilder();

        final List<String> list = parent.entrySet().stream()
                .filter(entry -> entry.getKey().equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());

        for (String str : list) {
            sb.append("Kingdom of ").append(str).append("\n");

        }

        System.out.println(list.size());
        System.out.println(sb);


    }
}
