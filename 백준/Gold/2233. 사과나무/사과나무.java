import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Integer, List<Integer>> map = new HashMap<>();
    
    static int nowNode = 1;

    static char[] shape;
    static int shapeIndex = 0;
    static int[][] indexes;

    static Set<Integer> rottenIndex = new HashSet<>();
    static Set<Integer> rotten = new HashSet<>();
    static void makeTree(int now) {

        while (true) {
            int i = shapeIndex++;
            if(i>=shape.length) return;

            if (shape[i] == '0') {
                int child = nowNode++;
                if(child>=indexes.length) return;

                if(rottenIndex.contains(i)) {
                    rottenIndex.remove(i);
                    rotten.add(child);
                }
                indexes[child][0] = i+1;
                map.get(now).add(child);
                makeTree(child);
            }
            else {
                if(rottenIndex.contains(i)) {
                    rottenIndex.remove(i);
                    rotten.add(now);
                }
                indexes[now][1] = i+1;
                return ;
            }

        }
        
    }

    static boolean[] visited;
    static int result = 0;
    static int dfs(int now) {
        int temp = 0;
        if (rotten.contains(now)) {
            temp+=1;
        }


        for (Integer next : map.get(now)) {
            if(!visited[next]) {
                visited[next] = true;
                temp += dfs(next);
                if(temp>=2) {
                    if(result==0)
                        result = now;
                    return 2;
                }
            }
        }
        return temp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        indexes = new int[N + 1][2];
        visited = new boolean[N + 1];

        shape = br.readLine().toCharArray();
        for (int i = 0; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        final String[] s = br.readLine().split(" ");

        rottenIndex.add(Integer.parseInt(s[0])-1);
        if(s.length==2)
         rottenIndex.add(Integer.parseInt(s[1])-1);

        // 이 과정 중 rotten도 저장함

        makeTree(0);



        if(rotten.size()==1) {
            for (Integer i : rotten) {
                System.out.print(indexes[i][0] +" "+ indexes[i][1]);
            }
            return;
        }


//        System.out.println("rotten = " + rotten);
        dfs(0);
//        System.out.println("result = " + result);
//        if(result == 0)
//            result = 1;
        System.out.print(indexes[result][0] + " " + indexes[result][1]);






    }
}

//

