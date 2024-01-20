
import java.io.*;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
    static HashMap<Integer, Integer> difficulty = new HashMap<>();
    static int[] maxDifficultCount = new int[101];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static void add(int problem, int difficult) {
        if(difficulty.containsKey(problem)) {
            final Integer diff = difficulty.get(problem);
            maxDifficultCount[diff]--;
            difficulty.put(problem,difficult);
            map.get(diff).remove(problem);
            map.get(difficulty).add(problem);

        }
        else {
            maxDifficultCount[difficult]++;
            difficulty.put(problem, difficult);
            map.get(difficult).add(problem);
        }
    }
    static void solved(int problem) {
        final Integer difficult = difficulty.get(problem);

        difficulty.remove(problem);
//        System.out.println("problem = " + problem);
//        System.out.println("difficult = " + difficult);
        map.get(difficult).remove(problem);

        maxDifficultCount[difficult]--;
    }

    static void recommend(int type) throws IOException {
        if(type==1) {
            int diff = 100;
            while(diff>1&&maxDifficultCount[diff]<=0) {
                diff--;
            }
//            maxDifficultCount[diff]--;

//            System.out.println("diff = " + diff);
            final Integer problem = map.get(diff).stream().max(Integer::compareTo).get();
//            difficulty.remove(problem);
            bw.write(problem + "\n");
        }
        else {
            int diff = 1;
            while(diff<100&&maxDifficultCount[diff]<=0) {
                diff++;
            }

//            maxDifficultCount[diff]--;
            final Integer problem = map.get(diff).peek();
//            difficulty.remove(problem);
            bw.write(problem + "\n");


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i = 1;i<=100;i++) {
            map.put(i, new PriorityQueue<>());
        }
        for(int i= 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int problem = Integer.parseInt(st.nextToken());
            int difficult = Integer.parseInt(st.nextToken());

            maxDifficultCount[difficult]++;
            difficulty.put(problem, difficult);
            map.get(difficult).add(problem);
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