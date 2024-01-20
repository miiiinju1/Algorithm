
import java.io.*;
import java.util.*;

public class Main {
    static class Get{
        TreeSet<Integer> maxHeap = new TreeSet<>(Comparator.reverseOrder());
        TreeSet<Integer> minHeap = new TreeSet<>();

    }

    static HashMap<Integer, Get> map = new HashMap<>();
    static HashMap<Integer, Integer> difficulty = new HashMap<>();
    static int[] maxDifficultCount = new int[101];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void add(int problem, int difficult) {
        if(difficulty.containsKey(problem)) {
            final Integer diff = difficulty.get(problem);
            maxDifficultCount[diff]--;
            difficulty.put(problem,difficult);

            Get get = map.get(diff);
            get.minHeap.remove(problem);
            get.maxHeap.remove(problem);

            get = map.get(difficult);

            get.minHeap.add(problem);
            get.maxHeap.add(problem);

        }
        else {
            maxDifficultCount[difficult]++;
            difficulty.put(problem, difficult);

            Get get = map.get(difficult);

            get.minHeap.add(problem);
            get.maxHeap.add(problem);
        }
    }
    static void solved(int problem) {
        final Integer difficult = difficulty.get(problem);

        difficulty.remove(problem);
        final Get get = map.get(difficult);
        get.minHeap.remove(problem);
        get.maxHeap.remove(problem);

        maxDifficultCount[difficult]--;
    }

    static void recommend(int type) throws IOException {
        if(type==1) {
            int diff = 100;
            while(diff>1&&maxDifficultCount[diff]<=0) {
                diff--;
            }

            final Get get = map.get(diff);
            int problem = get.maxHeap.first();
            bw.write(problem + "\n");
        }
        else {
            int diff = 1;
            while(diff<100&&maxDifficultCount[diff]<=0) {
                diff++;
            }

//            maxDifficultCount[diff]--;
            final Get get = map.get(diff);
            int problem = get.minHeap.first();
//            difficulty.remove(problem);
            bw.write(problem + "\n");


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 1;i<=100;i++) {
            map.put(i, new Get());
        }
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int problem = Integer.parseInt(st.nextToken());
            int difficult = Integer.parseInt(st.nextToken());

            maxDifficultCount[difficult]++;
            difficulty.put(problem, difficult);
            final Get get = map.get(difficult);
            get.maxHeap.add(problem);
            get.minHeap.add(problem);
        }
//        System.out.println("difficulty = " + difficulty);
//        System.out.println("map = " + map);
//        for (int i : maxDifficultCount) {
//            System.out.print(i+" ");
//        }
//        System.out.println();

        int M = Integer.parseInt(br.readLine());

        for(int i= 0;i<M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final String command = st.nextToken();
//            System.out.println("command = " + command);
            if (command.equals("add")) {
                int problem = Integer.parseInt(st.nextToken());
                int difficult = Integer.parseInt(st.nextToken());
                add(problem,difficult);

            } else if (command.equals("solved")) {
                int problem = Integer.parseInt(st.nextToken());
                solved(problem);
            }
            else {
                int type = Integer.parseInt(st.nextToken());
                recommend(type);
            }
        }
        bw.flush();bw.close();
    }
}


//문제번호 -> 난이도

//난이도 -> 문제번호들

//3
//1000 1
//1001 1
//1002 1
//8
//recommend 1
//add 1003 1
//recommend 1
//solved 1003
//recommend 1
//recommend -1
//add 1 1
//recommend -1