class Solution {
    public int solution(int[] a) {
        int count = 0;
        if(a.length == 1) {
            return 1;
        }
        boolean[] visited = new boolean[a.length];
        int now = Integer.MAX_VALUE;

        for(int i = 0;i<a.length;i++) {
            
            if(a[i]<now) {
                if(!visited[i]) {
                    count++;
                    visited[i] = true;
                }
               now = a[i];
            }
            
        }
        now = Integer.MAX_VALUE;
        for(int i = a.length-1;i>=0;i--) {
            
            if(a[i]<now) {
               if(!visited[i]) {
                    count++;
                    visited[i] = true;
                }
               now = a[i];
            }
            
        }
    
        return count;
    }
}
