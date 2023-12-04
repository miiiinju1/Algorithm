import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Student implements Comparable<Student> {
        int a, b;

        public Student(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Student o) {
            if (o.a == this.a) {
                return this.b-o.b;
            }
            return o.a-this.a;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Student[] ary = new Student[M];
            boolean[] visited =new boolean[N+1];
            int[] indexs = new int[N+1];
            Arrays.fill(indexs, -1);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                ary[i] = new Student(a, b);
            }
            Arrays.sort(ary);

            long sum = 0;

            for(int i = 0;i<ary.length;i++) {
                Student student = ary[i];
                int index = student.b;
                int startIndex= index;
                if(indexs[index]!=-1) {
                    index = indexs[index];
                }

                while(index>student.a&&visited[index]) {
                    index--;
                }

                if(!visited[index]) {
                    visited[index] = true;
                    indexs[startIndex] = index;
                    sum++;
                }

            }
            bw.write(sum + "\n");


        }

        bw.flush();bw.close();
    }
}