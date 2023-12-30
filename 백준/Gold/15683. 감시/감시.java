
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static void addR (int y, int x) {
        for(int j = x;j<M;j++) {
            if(originalMap[y][j]==-1) break;
            originalMap[y][j]+=1;
        }
    }
    static void minusR(int y, int x) {
        for(int j = x;j<M;j++) {
            if(originalMap[y][j]==-1) break;
            originalMap[y][j]-=1;
        }
    }
    static void addL (int y, int x) {
        for(int j = x;j>=0;j--) {
            if(originalMap[y][j]==-1) break;
            originalMap[y][j]+=1;
        }

    }
    static void minusL(int y, int x) {
        for(int j = x;j>=0;j--) {
            if(originalMap[y][j]==-1) break;
            originalMap[y][j]-=1;
        }
    }

    static void addDown(int y, int x) {
        for(int i = y;i<N;i++) {
            if(originalMap[i][x]==-1) break;
            originalMap[i][x]+=1;
        }

    }
    static void addUp(int y, int x) {
        for(int i = y;i>=0;i--) {
            if(originalMap[i][x]==-1) break;
            originalMap[i][x]+=1;
        }



    }static void minusDown(int y, int x) {
        for(int i = y;i<N;i++) {
            if(originalMap[i][x]==-1) break;
            originalMap[i][x]-=1;
        }

    }static void minusUp(int y, int x) {
        for(int i = y;i>=0;i--) {
            if(originalMap[i][x]==-1) break;
            originalMap[i][x]-=1;
        }
    }


    static class Cctv {
        int y, x, type;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Cctv{");
            sb.append("y=").append(y);
            sb.append(", x=").append(x);
            sb.append(", type=").append(type);
            sb.append('}');
            return sb.toString();
        }

        public Cctv(int y, int x, int type) {
            this.y = y;
            this.x = x;
            this.type = type;
        }

        public void type2(int depth) {
            addR(y, x);
            addL(y,x);
            search(depth + 1);
            minusR(y, x);
            minusL(y, x);

            addUp(y,x);
            addDown(y, x);
            search(depth + 1);
            minusUp(y, x);
            minusDown(y, x);
        }
        public void type3(int depth) {
            addUp(y,x);
            addR(y,x);
            search(depth + 1);
            minusUp(y,x);
            addDown(y,x);
            search(depth + 1);
            minusR(y,x);
            addL(y,x);
            search(depth + 1);
            minusDown(y,x);
            addUp(y,x);
            search(depth + 1);
            minusUp(y,x);
            minusL(y, x);
        }

        public void type4(int depth) {
            addL(y, x);
            addUp(y,x);
            addR(y,x);
            search(depth + 1);
            minusL(y, x);
            addDown(y, x);
            search(depth + 1);
            minusUp(y, x);
            addL(y, x);
            search(depth + 1);
            minusR(y, x);
            addUp(y, x);
            search(depth + 1);
            minusDown(y, x);
            minusL(y, x);
            minusUp(y, x);
        }

        public void type5(int depth) {
            addL(y, x);
            addUp(y,x);
            addR(y,x);
            addDown(y,x);
            search(depth + 1);
            minusDown(y, x);
            minusL(y, x);
            minusUp(y, x);
            minusR(y, x);
        }
        public void type1(int depth){
            addR(y, x);
                search(depth + 1);
            minusR(y, x);
//////////////////////////////
            addDown(y, x);
                search(depth+1);
            minusDown(y, x);
    //////////////////
            addL(y, x);
                search(depth+1);
            minusL(y, x);
                ///////////////////
            addUp(y, x);
                search(depth+1);
            minusUp(y, x);
////////


        }

    }
    static int N,M;

    static int min  = Integer.MAX_VALUE;

    
    static void search(int depth) {
        if (depth == cctvList.size()) {
            
            //count
            int count = 0;

            for(int i= 0;i<N;i++) {
                for(int j= 0;j<M;j++) {
                    if(originalMap[i][j]==0) {
                        count++;
                    }
                }
            }
            min = Math.min(count, min);
return ;
        }

        Cctv Cctv = cctvList.get(depth);
        switch(Cctv.type) {
            case 1:
                Cctv.type1(depth);
                break;
            case 2:
                Cctv.type2(depth);
                break;
            case 3:
                Cctv.type3(depth);
                break;
            case 4:
                Cctv.type4(depth);
                break;
            case 5:
                Cctv.type5(depth);
                break;
        }
        
    }
    
    
    static int[][] originalMap ;
    static ArrayList<Cctv> cctvList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originalMap = new int[N][M];
        
        for(int i = 0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<M;j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
                switch(originalMap[i][j]) {
                    case 1:
                        cctvList.add(new Cctv(i, j, 1));
                        originalMap[i][j] = 0;
                        break;
                    case 2:
                        cctvList.add(new Cctv(i, j, 2));
                        originalMap[i][j] = 0;
                        break;
                    case 3:
                        cctvList.add(new Cctv(i, j, 3));
                        originalMap[i][j] = 0;
                        break;
                    case 4:
                        cctvList.add(new Cctv(i, j, 4));
                        originalMap[i][j] = 0;
                        break;
                    case 5:
                        cctvList.add(new Cctv(i, j, 5));
                        originalMap[i][j] = 0;
                        break;
                    case 6:
                        originalMap[i][j] = -1;
                        break;
                }
            }
        }
//        System.out.println(cctvList.toString());
        search(0);
//
//        for(int i= 0;i<N;i++) {
//            for(int j= 0;j<M;j++) {
//                System.out.print(originalMap[i][j]+" ");
//            }
//            System.out.println();
//        }


        System.out.println(min);
    }
}

//6 6
//1 0 0 0 0 0
//0 2 0 0 0 0
//0 0 3 0 0 0
//0 0 0 4 0 0
//0 0 0 0 5 0
//0 0 0 0 0 6
