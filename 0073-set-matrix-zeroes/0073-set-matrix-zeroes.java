class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] markCol = new boolean[m];
        boolean[] markRow = new boolean[n];

        for(int i = 0;i<m;++i) {

            for(int j= 0;j<n;++j) {
                if(matrix[i][j]==0) {
                    markRow[j] = true;
                    markCol[i] = true;
                }    
            }
        }

        for(int i = 0;i<m;++i) {
            for(int j = 0;j<n;++j) {
                if(markCol[i] || markRow[j]) {
                    matrix[i][j] = 0;
                }
            }
        }

    }
}