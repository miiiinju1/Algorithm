import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, String> parent;
    static HashMap<String, Integer> size;

    static String find(String v) {
        if (v.equals(parent.get(v))) {
            return v;
        }
        else {
            parent.replace(v, find(parent.get(v)));
            return parent.get(v);
        }
    }

    static void union(String a, String b) throws IOException {
        String findA = find(a);
        String findB = find(b);

        if (!findA.equals(findB)) {
            if(size.get(findA)>size.get(findB)) {
                String temp = findA;
                findA = findB;
                findB = temp;
            }
            parent.replace(findA,findB);//A의 부모를 B로
            size.replace(findB, size.get(findB)+size.get(findA));


        }
        bw.write(size.get(findB)+"\n");
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0;tc<T;tc++) {
            int F = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            size = new HashMap<>();
            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!parent.containsKey(a)) {
                    parent.put(a, a);
                    size.put(a, 1);
                }
                if (!size.containsKey(b)) {
                    parent.put(b, b);
                    size.put(b, 1);
                }
                union(a, b);
            }

        }
        bw.flush();bw.close();
    }
}
