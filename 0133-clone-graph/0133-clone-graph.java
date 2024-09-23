/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
     public Node cloneGraph(Node node) {
        // System.out.println(node);

        if(node==null) {
            return null;
        }


        boolean[][] visited = new boolean[101][101];

        Node newRoot = new Node(node.val);


        Node[] nodes = new Node[101];
        nodes[1] = newRoot;
        dfs(node, newRoot, visited, nodes);

        return newRoot;
    }

    // 여기 들어오기 전에, 자식을 now로 ㄴ허기 전에, 같은 자리에 target에도 복사를 한 후 만들거같은데
    private void dfs(Node now, Node target, boolean[][] visited, Node[] nodes) {

        for(Node child : now.neighbors) {

            if(!visited[child.val][now.val]|| !visited[now.val][child.val]) {

                visited[child.val][now.val] = true;
                visited[now.val][child.val] = true;

                if(nodes[child.val]==null) {
                    nodes[child.val] = new Node(child.val);
                }
                Node newNode = nodes[child.val];
                // System.out.println(newNode.val+"->"+target.val);
                // System.out.println(target.val+"->"+newNode.val);

                newNode.neighbors.add(target);
                target.neighbors.add(newNode);

                dfs(child, newNode, visited, nodes);
            }
            
        }
    }

}

// 1 2 4
// 2 1 3
// 3 2 4
// 4 1 3

