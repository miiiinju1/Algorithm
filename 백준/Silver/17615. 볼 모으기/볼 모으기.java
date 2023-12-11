import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[] ary;
    static int search(char start) {
        int startIndex=0;
        int count =0;
        for(int i =0;i<ary.length;i++) {
            if(ary[i]==start) {
                if(startIndex!=i)
                    count++;
                startIndex++;
            }
        }
        int minCount=count;
        count=0;
        startIndex=ary.length-1;
        for(int i = startIndex;i>=0;i--) {
            if(ary[i]==start) {
                if(startIndex!=i) {
                    count++;
                }
                startIndex--;
            }
        }
        return Math.min(minCount, count);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        ary = br.readLine().toCharArray();
        System.out.println(Math.min(search('R'), search('B')));


    }
}
