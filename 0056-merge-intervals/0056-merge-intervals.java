class Solution {
    public int[][] merge(int[][] intervals) {
        int[] ary = new  int[20001];
        boolean [] visited = new boolean[20001];

        for(int[] interval: intervals) {
            int start = interval[0]*2;
            int end = interval[1]*2+1;

            ary[start]+=1;
            if(end<ary.length) {
                ary[end]-=1;
            }


        }
                List<int[]> result = new ArrayList<>();

        for(int i= 1;i<20001;i++) {
            ary[i] += ary[i-1];
        }


        for(int i= 0;i<20001;i++) {
            if(ary[i] != 0 && !visited[i]) {
                int start = i/2;
                visited[i] = true;
                int end = 10000;
                for(int j = i+1;j<20001;j++) {
                    visited[j] = true;
                    if(ary[j] ==0) {
                        end = (j-1)/2;
                        break;
                    }
                }
                result.add(new int[]{start,end});
            }
        }
        int[][] ret = new int[result.size()][2];
        for(int i= 0;i<result.size();i++) {
            ret[i][0] = result.get(i)[0];
            ret[i][1] = result.get(i)[1];

        }
        return ret;
    }
}