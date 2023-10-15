
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];
        int min = 51;
        for(int i=0;i<6;i++)
        {
            dice[i] = Integer.parseInt(st.nextToken());
            if (min > dice[i]) {
                min = dice[i];
            }
        }

        //2면 붙어있는 경우는
        //AC, AE, AD, AB, BF, FD, FC, FE, ED, EC, DB, BC
        int twoSideMin = 101;
        for(int i= 1;i<5;i++)
        {
            if(twoSideMin>dice[i])
                twoSideMin=dice[i];
        }
        int AFMin = -1;
        if(dice[0]>dice[5])//A랑 F 비교
        {
            AFMin = 5;
            twoSideMin += dice[5];
        }
        else {
            AFMin = 0;
            twoSideMin += dice[0];
        }
        int temp;
        if(dice[3]>dice[2])//D랑 C 비교

            temp = dice[2];
        else
            temp = dice[3];
        if(dice[1]>dice[4])//B랑 E 비교
            temp+=dice[4];
        else
            temp+=dice[1];

        if(twoSideMin>temp)
            twoSideMin = temp;



        //3면 붙어있는 경우
        //AEC, ABC, ABD, AED, FBC, FBD, FED, FEC
        int BC = dice[1]+dice[2];
        int EC = dice[2]+dice[4];
        int BD = dice[1]+dice[3];
        int ED = dice[3]+dice[4];
        int threeSideMin = dice[AFMin] + Math.min(BC, Math.min(EC, Math.min(BD, ED)));
        //(N-2*N-2) + (N-1)(N-2)*4, 중간 모아둔거 -> 최소값으로 도배
        //(N-2)*4 위에 중간에 2면만 보이는 것들
        //4 3면이 보이는 것들

        //(N-1)*4 2면 보이는 것들

        long sum =0;

        if(N!=1) {
            sum += (((N - 2) * (N - 2) + (N - 1) * (N - 2) * 4) * min);
            sum += ((N - 2) * 4 * twoSideMin);
            sum += (threeSideMin * 4);

            sum += ((N - 1) * 4 * twoSideMin);
        }
        else {
            int max = 0;
            for(int i= 0;i<6;i++)
            {
                sum += dice[i];
                if(dice[i]>max)
                    max = dice[i];
            }
            sum-=max;
        }
        System.out.println(sum);
    }
}
