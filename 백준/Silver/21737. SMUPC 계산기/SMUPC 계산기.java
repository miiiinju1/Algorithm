
import java.io.*;

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
        int strLen = str.length();
        int index=0;
        for(int i=0;i<strLen;i++)
        {
            if (str.charAt(i) >= 65)
                operator[index++] = new Operator( i,str.charAt(i));
        }
        int beforeIndex=0;
        Long[] list = new Long[1000000];

        index = 0;
        for(int i= 0;i<N;i++) {
            int diff =operator[i].index-beforeIndex;
            if (diff > 0) {
                long value = 0;
                for (int j = beforeIndex; j < operator[i].index; j++) {
                    char currentChar = str.charAt(j);
                    value = value * 10 + (currentChar - '0');
                }
                list[index++] = value;
            }
            beforeIndex = operator[i].index+1;
        }


        long sum = list[0];
        int listLen = index;
        int j= 1,count =0;
        for(int i= 0;i<N;i++)
        {
            char op= operator[i].op;
            switch(op){

                case 'S':
                    if(j>=listLen)
                        break;
                    sum-= list[j++];
                    break;
                case 'M':
                    if(j>=listLen)
                        break;
                    sum *= list[j++];
                    break;

                case 'U':
                    if(j>=listLen)
                        break;

                    if(sum<0)
                    {
                        sum*=-1;
                        sum /= list[j++];
                        sum*=-1;
                    }
                    else
                        sum /= list[j++];
                    break;

                case 'P':
                    if(j>=listLen)
                        break;
                    sum += list[j++];
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
