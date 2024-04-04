import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static class Node {
        int left;
        int right;
        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    static HashMap<Integer, Node> tree = new HashMap<>();
    static int dfs(int now, long k) {

        final Node node = tree.get(now);

        if(node.left==-1 && node.right==-1) {
            return now;
        }
        if(node.left != -1 && node.right != -1) {
            if (k % 2 == 0) { // 짝수번째 이동
                return dfs(node.right, k / 2);
            } else { // 홀수번째 이동
                return dfs(node.left, k / 2 + 1);
            }
        }else if(node.left == -1) { // 왼쪽 자식 노드만 존재하지 않는 경우
            return dfs(node.right, k);
        } else { // 오른쪽 자식 노드만 존재하지 않는 경우
            return dfs(node.left, k);
        }


    }

    static List<Integer> dfs(int now) {
        final Node node = tree.get(now);

        if(node.left==-1 && node.right==-1) {
            return List.of(now);
        }
        else if(node.left==-1) {
            return dfs(node.right);
        }
        else if(node.right==-1) {
            return dfs(node.left);
        }


        final List<Integer> left = dfs(node.left);
        final List<Integer> right = dfs(node.right);
//        System.out.println("left = " + left);
//        System.out.println("right = " + right);

        return concat(left,right);
    }

    static List<Integer> concat(List<Integer> left, List<Integer> right) {
        ArrayList<Integer> result = new ArrayList<>();
        if(left.size() >= right.size()) {
            int j = 0;
            for(int i = 0;i<left.size();i++) {
                if(j==right.size()) {
                    j=0;
                }
                result.add(left.get(i));
                result.add(right.get(j++));
            }
        }
        else {
            int j = 0;
            for(int i = 0;i<right.size();i++) {
                if(j==left.size()) {
                    j=0;
                }
                result.add(left.get(j++));
                result.add(right.get(i));
            }
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        for(int i =1;i<=N;i++) {
            String[] input = br.readLine().split(" ");
            int left = Integer.parseInt(input[0]);
            int right = Integer.parseInt(input[1]);
            tree.put(i, new Node(left, right));

        }
//        final List<Integer> dfs = dfs(1);
        long K = Long.parseLong(br.readLine());

        System.out.println(dfs(1,K));

    }
}

//11
//2 3
//4 5
//6 7
//-1 -1
//-1 -1
//8 9
//-1 -1
//-1 -1
//10 11
//-1 -1
//-1 -1
//1