import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        HashSet<Integer> set1 = new HashSet<>();
//
//        int n = 1;
//        set1.add(n);
//        for(int i = 2;i<Integer.MAX_VALUE;i++) {
//            n+=i;
//            if(n>1000000000) {
//                break;
//            }
//            set1.add(n);
//        }
//
//        HashSet<Integer> set2 = new HashSet<>();
//
//        double sqrt = Math.sqrt(1000000000);
//        for(int i = 2;i<Integer.MAX_VALUE;i++) {
//            if(i>sqrt) {
//                break;
//            }
//            set2.add(i*i-1);
//        }
//
//        set1.retainAll(set2);


        int[] array = new int[]{3, 15, 120, 528, 4095, 4726275, 139128, 20720703, 17955, 609960, 703893960, 160554240};
//        HashSet<Integer> set = new HashSet<>();
//        for(int i = 0;i<array.length;i++) {
//            set.add(array[i]);
//        }

        int index = 0;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken())-1;
            index++;
            if(a==0&&b==-1) {
                break;
            }
            int count = 0;
            for(int i = 0;i<array.length;i++) {
                if(array[i]>=a&&array[i]<b) {
                    count++;
                }
            }
            bw.write("Case " + index + ": " + count + "\n");
        }
        bw.flush();
        bw.close();
    }
}

// x = m^2, x+1개가  1 3 6 10 15인데, 1을 뺀게


// 2^x
// 2^0 = 1+1= 2
//
// 1 3 6 10 15

//2(n-1)

//2n -(-1)
// 1 3 4
//

//[3, 15, 120, 528, 4095, 4726275, 139128, 20720703, 17955, 609960, 703893960, 160554240]