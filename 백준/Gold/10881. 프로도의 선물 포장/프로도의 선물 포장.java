import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Rectangle {
        int width, height;


        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int area() {
            return width * height;
        }
        public void swap() {
            int temp = this.width;
            this .width = this.height;
            this.height = temp;
        }
    }

    public static int Calculate(Rectangle A, Rectangle B, Rectangle C) {
        int result1 = 0;
        Rectangle AB = new Rectangle(Math.max(A.width, B.width), A.height + B.height);
            //case 1
             int area1 = new Rectangle(AB.width + C.width, Math.max(AB.height, C.height)).area();
            //case 2
          int area2 = new Rectangle(Math.max(AB.width, C.width), AB.height + C.height).area();

            result1 = Math.min(area1, area2);

        AB = new Rectangle(A.width + B.width, Math.max(A.height, B.height));



        int result2 = 0;
         area1 = new Rectangle(AB.width + C.width, Math.max(AB.height, C.height)).area();
            //case 2
         area2 = new Rectangle(Math.max(AB.width, C.width), AB.height + C.height).area();
            int area = Math.min(area1, area2);
            result2 = area;

        return Math.min(result1, result2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Rectangle A = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            Rectangle B = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            Rectangle C = new Rectangle(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            int min = Integer.MAX_VALUE;


            min =Math.min(min, Calculate(A, B, C));
            min = Math.min(min, Calculate(A, C, B));
            min =  Math.min(min, Calculate(B, A, C));
            min =    Math.min(min, Calculate(B, C, A));
            min =    Math.min(min, Calculate(C, A, B));
            min =    Math.min(min, Calculate(C, B, A));
           //A
            A.swap();
            min =Math.min(min, Calculate(A, B, C));
            min = Math.min(min, Calculate(A, C, B));
            min =  Math.min(min, Calculate(B, A, C));
            min =    Math.min(min, Calculate(B, C, A));
            min =    Math.min(min, Calculate(C, A, B));
            min =    Math.min(min, Calculate(C, B, A));
            A.swap();
            //B

            B.swap();
            min =Math.min(min, Calculate(A, B, C));
            min = Math.min(min, Calculate(A, C, B));
            min =  Math.min(min, Calculate(B, A, C));
            min =    Math.min(min, Calculate(B, C, A));
            min =    Math.min(min, Calculate(C, A, B));
            min =    Math.min(min, Calculate(C, B, A));
            B.swap();


            //C

            C.swap();
            min =Math.min(min, Calculate(A, B, C));
            min = Math.min(min, Calculate(A, C, B));
            min =  Math.min(min, Calculate(B, A, C));
            min =    Math.min(min, Calculate(B, C, A));
            min =    Math.min(min, Calculate(C, A, B));
            min =    Math.min(min, Calculate(C, B, A));
            C.swap();
            


            bw.write(min + "\n");




        }
        bw.flush();
        bw.close();

    }
}


//A B C가 있을 때

//세로 겹치기
//ABC, AB C, AC B, A BC, A B C