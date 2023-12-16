
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        int start = Integer.parseInt(S.substring(0,2))*60+Integer.parseInt(S.substring(3));
        int end = Integer.parseInt(E.substring(0,2))*60+Integer.parseInt(E.substring(3));
        int q = Integer.parseInt(Q.substring(0,2))*60+Integer.parseInt(Q.substring(3));
        String str = br.readLine();
        HashSet<String> startPeople = new HashSet<>();
        HashSet<String> participant = new HashSet<>();
        try {
            while (true) {
                if (str == null) {
                    break;
                }
                st = new StringTokenizer(str);
                if (!st.hasMoreTokens()) {
                    br.close();
                    break;
                }

                String temp = st.nextToken();
                String name = st.nextToken();
                int timeStamp = Integer.parseInt(temp.substring(0, 2)) * 60 + Integer.parseInt(temp.substring(3));
                if (timeStamp <= start) {
                    startPeople.add(name);
                } else {
                    if (timeStamp >= end && timeStamp <= q && startPeople.contains(name)) {
                        participant.add(name);
                    }
                }
                str = br.readLine();
                if (str.isEmpty()) {
                    break;
                }
                //eof를 감지해서 끝내야함
            }
        }
        catch (NullPointerException e) {
            
        }
        System.out.println(participant.size());
        
    }

}

