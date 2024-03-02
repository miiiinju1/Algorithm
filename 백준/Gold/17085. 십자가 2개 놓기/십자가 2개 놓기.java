
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int getArea(int n) {
        return 4 * n + 1;
    }
    static char[][] map;
    static int canMax(int i, int j) {
        if(testMap[i][j] != '#') {
            return -1;
        }
        int maxSize = 7;
        for(int index = i+1;index<=i+maxSize;index++) {
            if(index>=N || testMap[index][j]!='#') {
                maxSize = index - 1 - i;
                break;
            }
        }
        for(int index = i-1;index>=i-maxSize;index--) {
            if(index<0 || testMap[index][j]!='#') {
                maxSize = i - (index + 1);
                break;
            }
        }
        for(int index = j+1;index<=j+maxSize;index++) {
            if(index>=M || testMap[i][index]!='#') {
                maxSize = index - 1 - j;
                break;
            }
        }
        for(int index = j-1;index>=j-maxSize;index--) {
            if(index<0 || testMap[i][index]!='#') {
                maxSize = j - (index + 1);
                break;
            }
        }
        return maxSize;

    }
    static void pick(int n,int i, int j) {
        if(!(i+n<N && i-n>=0 && j+n<M &&j-n>=0)) {
            return ;
        }
        testMap[i][j] = '*';
        for(int index = i+1;index<=i+n;index++) {
            testMap[index][j] = '*';
        }
        for(int index = i-1;index>=i-n;index--) {
            testMap[index][j] = '*';
        }
        for(int index = j+1;index<=j+n;index++) {
            testMap[i][index] = '*';
        }
        for(int index = j-1;index>=j-n;index--) {
            testMap[i][index] = '*';
        }
    }

    static void unpick(int n, int i, int j) {
        testMap[i][j] = map[i][j];
        for(int index = i+1;index<=i+n;index++) {
            testMap[index][j] = map[index][j];
        }
        for(int index = i-1;index>=i-n;index--) {

            testMap[index][j] = map[index][j];
        }
        for(int index = j+1;index<=j+n;index++) {
            testMap[i][index] = map[i][index];
        }
        for(int index = j-1;index>=j-n;index--) {
            testMap[i][index] = map[i][index];
        }
    }


    static int maxSize(int i, int j) {
        if(testMap[i][j] != '#') {
            return -1;
        }
        int maxSize = 7;
        for(int index = i+1;index<=i+maxSize;index++) {
            if(index>=N || testMap[index][j]!='#') {
                maxSize = index - 1 - i;
                break;
            }
        }
        for(int index = i-1;index>=i-maxSize;index--) {
            if(index<0 || testMap[index][j]!='#') {
                maxSize = i - (index + 1);
                break;
            }
        }
        for(int index = j+1;index<=j+maxSize;index++) {
            if(index>=M || testMap[i][index]!='#') {
                maxSize = index - 1 - j;
                break;
            }
        }
        for(int index = j-1;index>=j-maxSize;index--) {
            if(index<0 || testMap[i][index]!='#') {
                maxSize = j - (index + 1);
                break;
            }
        }
        return maxSize;

    }
    static int pickSecond(int n) {
        int max = -1;

        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                max = Math.max(max, getArea(n)*getArea(maxSize(i, j)));
            }
        }
        return max;

    }
    static int totalMax = 0;
    static void pickOne() {
        for(int i= 0;i<N;i++) {
            for(int j= 0;j<M;j++) {
                 if (testMap[i][j] == '#') {
                    int pickSize = canMax(i,j);
                    if (pickSize!=-1) {
                        for (int size = pickSize; size >= 0 ; size--) {
                            pick(size, i, j);
//                        System.out.println("pickSize = " + pickSize);
                            //여기서 하나 골랐으니깐
                            //가장 크게 겹치는거 찾으러가기
//                        System.out.println(i + " " + j);
//                        print();
                            int temp = pickSecond(size);
//                        System.out.println("temp = " + temp);
                            totalMax = Math.max(totalMax, temp);

//                        System.out.println("-----------");
                            unpick(size, i, j);

                        }
                    }
                 }
                
            }
        }
    }
    static char[][] testMap;
    static int N,M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

        //n의 범위는 최대 7까지임 n은 1~7
        map = new char[N][M];
        testMap = new char[N][M];
        for(int i= 0;i<N;i++) {
            final char[] input = br.readLine().toCharArray();
            for(int j= 0;j<M;j++) {
                map[i][j] = input[j];
                testMap[i][j] = map[i][j];
            }
        }

//        System.out.println(canMax(2, 4));
        pickOne();
        System.out.println(totalMax);

    }
}
