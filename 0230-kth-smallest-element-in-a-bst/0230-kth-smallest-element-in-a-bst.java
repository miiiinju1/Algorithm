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
    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, new Count(), k);
    }

    static class Count {
        int k;
    }

    public int dfs(TreeNode now, Count count, int k) {
        if(now.left!=null) {
            int res = dfs(now.left, count,k);
            if(res != -1) {
                return res;
            }
        }
        //본인 탐색
        ++count.k;
        if(count.k==k) {
            return now.val;
        }
        if(now.right!=null) {
            int res = dfs(now.right, count,k);
            if(res != -1) {
                return res;
            }
        }
        return -1;
    }
}
