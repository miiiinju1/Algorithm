class Solution {
    static int n;
    static int[][] dice;
    static boolean[] visited;
    static int[] aChoose;
    static int[] bChoose;
    
    static int[] A;
    static int[] B;
    
    static long maxAWin = 0;
    static int[] maxResult;
    
    static void calculation() {
        long winCount=0;
        for(int i = 1;i<601;i++) {
            // A[i]+=A[i-1];
            B[i]+=B[i-1];
        }
        
        for(int i = 1;i<601;i++) {
            winCount += (A[i] * B[i-1]);
        }
        
        if(maxAWin<winCount) {
            maxAWin = winCount;
            for(int i=0;i<n/2;i++) {
                maxResult[i] = aChoose[i];
            }
        }
        
        
        
        
    }
    static void simulation(int diceNum, int sumA, int sumB) {
        if(diceNum==n/2) {
            A[sumA]+=1;
            B[sumB]+=1;
            return;
        }
        for(int i = 0;i<6;i++) {
            simulation(diceNum+1, sumA+dice[aChoose[diceNum]][i], 
                       sumB+dice[bChoose[diceNum]][i]);
        }
    }
    
    static void choose(int now, int count) {
        if(count==n/2) {
            int index = 0;
            for(int i =0;i<n;i++) {
                if(!visited[i]) {
                    bChoose[index++] = i;
                }
            }
            simulation(0,0,0);
            calculation();
            A = new int[601];
            B = new int[601];
            return;
        }
        
        for(int i = now; i<n;i++) {
            if(!visited[i]) {
                visited[i] = true;
                aChoose[count] = i;
                choose(i+1, count+1);
                visited[i] = false;
            }
        }
    }
    public int[] solution(int[][] d) {
        dice = d;
        n = dice.length;
        maxResult = new int[n/2];
        aChoose = new int[n/2];
        bChoose = new int[n/2];
        A = new int[601];
        B = new int[601];
        visited = new boolean[n];
        choose(0,0);
      
        for(int i= 0;i<n/2;i++) {
            maxResult[i]+=1;
        }
        return maxResult;
    }
}