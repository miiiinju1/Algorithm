import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> manP = new ArrayList<>();
        ArrayList<Integer> womanP = new ArrayList<>();
        ArrayList<Integer> manN = new ArrayList<>();
        ArrayList<Integer> womanN = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp>0) {
                manP.add(temp);
            }
            else {
                manN.add(Math.abs(temp));
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp>0) {
                womanP.add(temp);
            }
            else {
                womanN.add(Math.abs(temp));
            }
        }
        Collections.sort(manP);
        Collections.sort(manN);
        Collections.sort(womanP);
        Collections.sort(womanN);

        int l = 0, r = 0;
        int count = 0;
        while (l < manP.size() && r < womanN.size()) {
            if(manP.get(l)<womanN.get(r)) {
                l++;
                r++;
                count++;
            }
            else r++;
            
        }
        l=0;r=0;
        while (l < manN.size() && r < womanP.size()) {
            if(manN.get(l)>womanP.get(r)) {
                l++;
                r++;
                count++;
            }
            else l++;
        }

        System.out.println(count);
    }
}
