import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =null;

        while(true)
        {
            st = new StringTokenizer(br.readLine());
            int N =Integer.parseInt(st.nextToken());
            if(N==0)
                break;

            PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
            while(st.hasMoreTokens())
                pq.add(st.nextToken());

            StringBuilder num1 = new StringBuilder();
            StringBuilder num2 = new StringBuilder();

            for(int i =0;i<N;i++)
            {
                if(pq.isEmpty()||pq.peek().equals("0"))
                    break;
                num1.insert(0, pq.poll());
                if(pq.isEmpty()||pq.peek().equals("0"))
                    break;
                num2.insert(0,pq.poll());
            }


            if(!pq.isEmpty())
            {
                if(num1.length()>num2.length())
                    num2.insert(1,pq.poll());
                else if(num1.length()<num2.length())
                    num1.insert(1,pq.poll());
                while(true)
                {
                    if(pq.isEmpty())
                        break;
                    num2.insert(1,pq.poll());
                    if(pq.isEmpty())
                        break;
                    num1.insert(1,pq.poll());
                }




            }
            System.out.println((Integer.parseInt(num1.toString()))+Integer.parseInt(num2.toString()));


        }

    }
}
