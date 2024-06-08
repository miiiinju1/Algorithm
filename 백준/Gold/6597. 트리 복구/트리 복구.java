import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<Character, Node> tree = new HashMap<>();

    static class Node {
        Character left, right;

        public Node(Character left, Character right) {
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

    static int index;
    static void dfs(char parent, int parentIndex, int left, int right) {

        // parent에 D가 들어왔어
        if(index== preOrder.length()) {
            return;
        }

        final char child = preOrder.charAt(index);
        final int childIndex = inOrder.indexOf(child);

        //D의 index = 3
        //B의 인덱스는 부모 index보다 작으니깐 왼쪽
        if (childIndex < right && childIndex > left) {

            if (childIndex < parentIndex) {
                tree.get(parent).left = child;
                index++;
            }
            else if (childIndex > parentIndex ) {
                tree.get(parent).right = child;
                index++;
            }
            dfs(child, childIndex, left, childIndex);
            dfs(child, childIndex, childIndex, right);
        }

        //B의

    }

    static String preOrder,inOrder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        while((input = br.readLine())!= null) {
            if(input.isEmpty())
                break;
            tree = new HashMap<>();

            for(int i= 0;i<26;i++) {
                tree.put((char)('A' + i), new Node(null, null));
            }

            final String[] split = input.split(" ");
            preOrder = split[0];
            inOrder = split[1];


            index = 1;
            dfs(preOrder.charAt(0), inOrder.indexOf(preOrder.charAt(0)), -1, inOrder.indexOf(preOrder.charAt(0)));
            dfs(preOrder.charAt(0), inOrder.indexOf(preOrder.charAt(0)), inOrder.indexOf(preOrder.charAt(0)), preOrder.length());

            StringBuilder sb = new StringBuilder();
            postOrder(preOrder.charAt(0), sb);
            bw.write(sb.toString());
            bw.newLine();

        }
        bw.flush();bw.close();
    }

    private static void postOrder(char now, StringBuilder sb) {

        if(tree.get(now).left!=null) {
            postOrder(tree.get(now).left, sb);
        }
        if(tree.get(now).right!=null) {
            postOrder(tree.get(now).right, sb);
        }
        sb.append(now);
    }
}
