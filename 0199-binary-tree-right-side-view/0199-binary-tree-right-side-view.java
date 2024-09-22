/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();//k = depth, v = 처음으로 나온 Node
        if(root!= null)
        dfs(root, 0, map);

        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
                
    }

    public void dfs(TreeNode now, int depth, Map<Integer, Integer> map) {
        // 오른쪽 탐색
        if(now.right!=null) {
            dfs(now.right, depth+1, map);
        }

        map.putIfAbsent(depth, now.val);
        // 본인을 map에 넣어볼 겁니다

        // 왼쪽 탐색
        if(now.left!=null) {
            dfs(now.left, depth+1, map);
        }
    }

}