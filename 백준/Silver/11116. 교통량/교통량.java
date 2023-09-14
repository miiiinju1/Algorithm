
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0;t<n;t++)
        {
            int m = Integer.parseInt(br.readLine());
            int count = 0;
            TreeSet<Integer> left = new TreeSet<>();
            HashSet<Integer> right = new HashSet<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0;i<m;i++)
            {
                left.add(Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0;i<m;i++)
            {
                right.add(Integer.parseInt(st.nextToken()));
            }
            for(int i = 0;i<m;i++)
            {
                if(left.size()==0)
                    break;
                int temp1=  Collections.min(left);
                left.remove(temp1);
                int temp2 = temp1+500;
                left.remove(temp2);
                int temp3 = temp2+500;
                int temp4 = temp3+500;

                int wrong1 = temp1-500;
                int wrong2 = temp1-1000;

                if(right.contains(temp3))
                {
                    count++;
                    right.remove(temp3);
                    right.remove(temp4);
                }
                else if(right.contains(wrong1))
                {
                    right.remove(wrong1);
                    right.remove(wrong2);
                }
            }
            sb.append(count+"\n");
        }
        System.out.println(sb);
    }
}



