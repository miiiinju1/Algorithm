import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ary = new int[366];

        for(int i= 0;i<N;i++) {
            String[] input = br.readLine().split(" ");
            int start = Integer.parseInt(input[0]);
            int end = Integer.parseInt(input[1]);
            ary[start]+=1;
            if(end<365) {
                ary[end+1]-=1;
            }
        }
        for(int i= 2;i<366;i++) {
            ary[i]+=ary[i-1];
        }

        int width = 0;
        int height = 0;
        long sum = 0;
        for(int i= 1;i<366;i++) {
            if(ary[i]>0) {
                width++;
                height = Math.max(height, ary[i]);
            }
            else {
                if(width!=0) {
                    sum += (width*height);
                }
                width = 0;
                height = 0;

            }
        }
        if(width!=0) {
            sum += (width*height);
        }
        System.out.println(sum);
    }
}
