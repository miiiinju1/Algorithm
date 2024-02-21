import java.io.*;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            TreeMap<Long, Integer> map = new TreeMap<>();

            int k = Integer.parseInt(br.readLine());
            for(int i = 0; i < k; i++) {
                String[] input = br.readLine().split(" ");
                if(input[0].equals("I")) {
                    Long num = Long.parseLong(input[1]);
                    map.put(num, map.getOrDefault(num,0)+1);
                }
                else {
                    if(map.isEmpty()) {
                        continue;
                    }
                    if(input[1].equals("-1")) {
                        long key = map.firstKey();

                        if(map.get(key)==1) {
                            map.remove(key);
                        }
                        else {
                            map.replace(key,map.get(key)-1);
                        }
                    }
                    else {
                        long key = map.lastKey();

                        if(map.get(key)==1) {
                            map.remove(key);
                        }
                        else {
                            map.replace(key,map.get(key)-1);
                        }

                    }

                }

            }
            if(map.isEmpty()) {
                bw.write("EMPTY\n");
            }
            else {
                bw.write(map.lastKey() + " " + map.firstKey() + "\n");
            }

        }
        bw.flush();
        bw.close();

    }
}
