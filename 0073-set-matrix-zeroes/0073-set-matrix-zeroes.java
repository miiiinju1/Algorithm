import java.util.*;
class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        
        boolean[] markCol = new boolean[m];
        boolean[] markRow = new boolean[n];

        // Set<Integer> markCol = new HashSet<>();
        // Set<Integer> markRow = new HashSet<>();

        for(int i = 0;i<m;++i) {

            for(int j= 0;j<n;++j) {
                if(matrix[i][j]==0) {
                    // markRow.add(j);
                    // markCol.add(i);
                    markRow[j] = true;
                    markCol[i] = true;
                }    
            }
        }

        for(int i = 0;i<m;++i) {
            for(int j = 0;j<n;++j) {
                // if(markCol.contains(i) || markRow.contains(j) {
                if(markCol[i] || markRow[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}