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
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
    }
    //[3,1,5,0,2,4,6]

/*


           3
        1    5
      0. 2. 4. 6

3 -2147483648 2147483647
1 -2147483648 2
0 -2147483648 0
2 2 2
5 4 2147483647
4 4 4
6 6 2147483647


 */

    public boolean dfs(TreeNode now, long left, long right) {


        System.out.println(now.val+" "+left+" "+right);
        // left 1, right3 인 상황에서 left탐색을 한다면 1,1
        if(left == right) {
            if(now.val != left) {
                return false;
            }
        }
        if(now.val >= left && now.val <= right) {
            boolean l = true;
            if(now.left != null) {
                l = dfs(now.left, left, (long)now.val -1);
            }

            boolean r = true;
            if(now.right != null) {
                r = dfs(now.right, (long)now.val+1, right);
            }

            return l&&r;
        }
        
        return false;
    }
}
