class Solution {
    static int[] rion = new int[11];
    
    static private int[] checkArr ( int[]a, int[] b ) {

        for(int i = a.length-1; i >=0 ; i--) {
            if(a[i] == b[i]) 
                continue;
            
            if(a[i] < b[i]) return b;
                break;
        }
        return a;
    }
    static int max = 0;
    static int[] answer = new int[11];
    static void combination(int n, int count,int index) {
        if(count==n) {
            int temp = test();
            if(temp>max) {
                max = temp;
                for(int i=0;i<11;i++) {
                    answer[i] = rion[i];
                }
            }
            else if(temp==max) {
                max = temp;
                int[] ans = checkArr(answer,rion);
                for(int i=0;i<11;i++) {
                    answer[i] = ans[i];
                }
            }
            // if(temp > max) {
            //     max = temp;
            //     for(int j= 0;j<11;j++) {
            //         answer[j] = rion[j];
            //     }
            // }
            // else if(temp == max) {
            // a:  for(int i= 10;i>=0;i--) {
            //         if(answer[i] < rion[i]) {
            //             for(int j= 0 ; j<11 ; j++) {
            //                 answer[j] = rion[j];
            //             }
            //             break a;
            //         }
            //     }
            // }
            return ;
        }
        
        for(int i = index;i<11;i++) {
            int lim = Math.min(n-count, apeach[i]+1);
            rion[i] +=lim;
            combination(n,count+lim,i+1);
            rion[i] -=lim;
            combination(n,count,i+1);
        }
        
    }
    
    static int[] apeach;
    
    static int test() {
        int rionSum = 0;
        int apeachSum = 0;
        
        for(int i= 0;i<11;i++) {
            if(rion[i]>apeach[i]) {
                if(rion[i]!=0)
                    rionSum+=(10-i);
            }
            else {
                if(apeach[i]!=0)
                    apeachSum+=(10-i);
            }
            // System.out.print(rion[i]+" ");
        }
        
        return rionSum-apeachSum;
    }
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        
        combination(n,0,0);
        if(max==0) {
            int[] ret = {-1};
            return ret;
        }
            
        return answer;
    }
}


