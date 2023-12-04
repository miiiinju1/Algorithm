import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i= 0;i<P;i++) {
            int a = Integer.parseInt(br.readLine());
            map.put(a, map.getOrDefault(a, 0) + 1);
            list.add(a);
        }

        boolean[] visited = new boolean[G+1];
        int[] indexs = new int[G+1];
        Arrays.fill(indexs, -1);
        long sum = 0;
        for(int i= 0;i<list.size();i++) {
            int index = list.get(i);
            int startIndex = index;

            if(indexs[index]!=-1) {
                index = indexs[index];
            }
            while(index>1&&visited[index]) {
                index--;
            }

            if(!visited[index]) {
                visited[index] = true;
                indexs[startIndex] = index;
                
                sum++;
            } else {
                break;
            }

        }
//        for (boolean b : visited) {
//            System.out.print(b + " ");
//        }
//        System.out.println();
        System.out.println(sum);

        //1 3
        //2 4
    }



}
