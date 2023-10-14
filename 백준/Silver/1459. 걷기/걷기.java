
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken());
        long S = Long.parseLong(st.nextToken());
        //1 2 3
        //4 5 6 
        //7 8 9
        
        //위, 오른쪽, 대각선, 2칸 위, 2칸 오른쪽  (1,2), (2,1)이동
        
        //4, 8로 가는 비용 W
        //1, 9로 가는 비용 Math.min(2W,2S)
        //2, 6으로 가는 비용 Math.min(W+S, 3W) 
        //5로 가는 비용 Math.min(2W, S)
        //3으로 가는 비용 Math.min(2W, S)
        
        //위 오른쪽 W
        //대각선 Math.min(2W,S) 직선이 2배 이상 크면 대각선으로
        //2칸 위 Math.min(2W,2S) W랑 S중에 작은 거
        
        //(1,2)랑 (2,1)도 직선이 2배 이상 크면   지속하기
        
        if(2*W>S) //대각선 가는 게 이득이면
        {
            if(W<=S) //2칸 이동할 때 직선만 생각 2W 
            {
                //대각선 많이 가고 직선만

                if(X>=Y) {
                    long diagonal  = S*Y;
                    long additional = (X-Y)*W;
                    System.out.println(diagonal+additional);
                }
                else {
                    long diagonal  = S*X;
                    long additional = (Y-X)*W;
                    System.out.println(diagonal+additional);


                }
            }
            else //2칸 이동은 대각선 2개 2S
            {
                //대각선 많이 가고 2개단위로 짜르고 남은거 직선
                if(X<=Y)
                {
                    long diagonal = S*X;
                    long temp = Y-X;

                    long additional = (temp/2)*S*2;
                    if (temp % 2 != 0)
                        additional += W;
                    System.out.println(diagonal+additional);
                }
                else{
                    long diagonal = S * Y;
                    long temp = X - Y;
                    long additional = (temp / 2) * 2*S;
                    if (temp % 2 != 0)
                        additional += W;
                    System.out.println(diagonal + additional);
                }
            }
        }
        else//직선 많이 가는 게 이득
        {
            
            //걍 직선으로만 다 가기;
            System.out.println((X + Y) * W);

        }
        
        //직선2배가 대각선보다 크면 대각선 많이 직선 최소화
        //직선2배가 대각선보다 작으면 직선으로만 이동 대각선 최소화
        //한 줄로 두 칸 이동은 W랑 S중에 작은거
        
        
        //직선 7 대각선 11
        //한 줄로 두칸 직선 2개
        //대각선 
        
        //18*11 + 7*7
        //198 49 247
        
        
        
    }
}
