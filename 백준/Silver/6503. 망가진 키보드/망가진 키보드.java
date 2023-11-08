
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            HashMap<Character,Integer> map = new HashMap<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m;
            if ((m=Integer.parseInt(st.nextToken()))==0) {
                break;
            }

            int tm = m;
            String str = br.readLine();
            if(tm>str.length())
                tm = str.length();
            int lo=0, hi=0;
            int result=0;
            int max = 0;
            while (hi < str.length()&&lo<=hi) {
                if(map.containsKey(str.charAt(hi))) {
                       map.put(str.charAt(hi), map.get(str.charAt(hi++))+ 1);
                }
                else
                    map.put(str.charAt(hi++), 1);
                result++;

                while (map.size() < tm&&hi<str.length()) {
                    if(map.containsKey(str.charAt(hi))) {
                        map.put(str.charAt(hi), map.get(str.charAt(hi++))+ 1);
                    }
                    else
                        map.put(str.charAt(hi++), 1);
                    result++;
                }

                while (map.size() > tm&&lo<str.length()) {
                    if(map.get(str.charAt(lo))==1)
                        map.remove(str.charAt(lo++));
                    else {
                        map.replace(str.charAt(lo), map.get(str.charAt(lo++)) - 1);
                    }
                    result--;
                }

                if(result>max) {
                    max = result;
                }
            }
            bw.write(max+"\n");
        }

        bw.flush();
        bw.close();

    }
}
//5
//This ccc t  e colved by brute force.
//1
//Mississippi
//0