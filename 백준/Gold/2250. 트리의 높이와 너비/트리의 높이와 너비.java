import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int left, right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Node{");
            sb.append("left=").append(left);
            sb.append(", right=").append(right);
            sb.append('}');
            return sb.toString();
        }
    }

    static HashMap<Integer, Node> tree = new HashMap<>();

    static HashMap<Integer, Node> count = new HashMap<>();

    static int count (int now) {

        final Node node = tree.get(now);

        if(node.left==-1 && node.right == -1) {
            count.put(now, new Node(0,0));
            return 1;
        }

        int left = 0;
        if(node.left!=-1) {
            left+=count(node.left);
        }
        int right = 0;
        if(node.right!=-1) {
            right+=count(node.right);
        }
        count.put(now, new Node(left, right));
        return left+right+1;

    }


    static HashMap<Integer, Node> result = new HashMap<>();
    static int dfs(int now, int left, int right, int depth) {
        if(left==right) {
            return left;
        }
        result.computeIfAbsent(depth, k -> new Node(Integer.MAX_VALUE, Integer.MIN_VALUE));
        final Node node = tree.get(now);

        final Node nodeCount = count.get(now);
        int l = nodeCount.left;
        int r = nodeCount.right;


        int resultL = -1;
        if(node.left!=-1) {
            resultL=dfs(node.left, left, left+l-1, depth+1);
        }
        int resultR = -1;
        if(node.right!=-1) {
            resultR=dfs(node.right, left+l+1, right,depth+1 );
        }
        if(resultL==-1 && resultR==-1) {
            return left+l;
        }

        if(resultL!=-1 && resultR != -1) {
            int min = Math.min(resultL, resultR);
            int max = Math.max(resultL, resultR);
            result.get(depth).left = Math.min(min, result.get(depth).left);
            result.get(depth).right = Math.max(max, result.get(depth).right);

        }
        else if(resultL==-1) {
            result.get(depth).left = Math.min(resultR, result.get(depth).left);
            result.get(depth).right = Math.max(resultR, result.get(depth).right);
        }
        else {
            result.get(depth).left = Math.min(resultL, result.get(depth).left);
            result.get(depth).right = Math.max(resultL, result.get(depth).right);
        }


        return left+l;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int root = N*(N+1)/2;
        for(int i = 0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if(left!=-1)
                root-=left;
            if(right!=-1)
                root-=right;
            tree.put(num, new Node(left, right));
        }
        if(N==1) {
            System.out.println("1 1");
            return ;
        }

        count(root);

        dfs(root,1,N,1);

        final Map.Entry<Integer, Node> entry = result.entrySet().stream()
                .min((o1, o2) -> {
                    int o2Value = o2.getValue().right - o2.getValue().left + 1;
                    int o1Value = o1.getValue().right - o1.getValue().left + 1;
                    if (o1Value == o2Value) {
                        return Integer.compare(o1.getKey(), o2.getKey());
                    }
                    return Integer.compare(o2Value, o1Value);
                })
                .get();
        int value = (entry.getValue().right- entry.getValue().left+1);
        if(value==1) {
            System.out.println("1 1");
            return ;
        }
        System.out.println(entry.getKey() + 1 + " " + value);

    }
}
