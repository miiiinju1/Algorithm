
import java.io.*;
import java.util.ArrayList;

public class Main {

    static class Operator{
        int index;
        char op;

        public Operator(int i, char op) {
            this.index=i;
            this.op =op;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Operator[] operator = new Operator[N];


        String str = br.readLine();

        int index=0;
        for(int i=0;i<str.length();i++)
        {
            if (str.charAt(i) >= 65)
                operator[index++] = new Operator( i,str.charAt(i));

        }
        int beforeIndex=0;

        ArrayList<Long> list = new ArrayList<>();
        for(int i= 0;i<N;i++) {
            if(beforeIndex!=operator[i].index) {
                String substr = str.substring(beforeIndex, operator[i].index);
                if (!substr.equals(""))
                    list.add(Long.parseLong(substr));
            }
            beforeIndex = operator[i].index+1;
        }


        long sum = list.get(0);
        int j= 1,count =0;
        for(int i= 0;i<N;i++)
        {
            char op= operator[i].op;
            switch(op){

                case 'S':
                    if(j>=list.size())
                        break;
                    sum-=list.get(j++);
                    break;
                case 'M':
                    if(j>=list.size())
                        break;
                    sum *= list.get(j++);
                    break;

                case 'U':
                    if(j>=list.size())
                        break;

                    if(sum<0)
                    {
                        sum*=-1;
                        sum /= list.get(j++);
                        sum*=-1;
                    }
                    else
                        sum /= list.get(j++);
                    break;

                case 'P':
                    if(j>=list.size())
                        break;
                    sum += list.get(j++);
                    break;
                case 'C':
                    count++;
                    bw.write(sum + " ");
                    break;
            }
        }

        if (count == 0)
            bw.write("NO OUTPUT");

        bw.flush();
        bw.close();
    }
}
