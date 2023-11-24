class Solution {
    static int emoticonCount;
    static int[] 할인율list;
    static int[][] users;
    static int[] emoticons;
    static int maxRegister=0;
    static int maxCost=0;
    static void resultCalculator() {
        int[] calcUser = new int[users.length];
        int register=0, cost = 0;
        
        for(int u =0;u<users.length;u++) {
            
            for(int e=0;e<emoticonCount;e++) {
                int 할인율 = 할인율list[e];
                if(users[u][0]<=할인율) {
                    calcUser[u] += (emoticons[e]*(100-할인율)/100);
                }
                
            }
            
            cost+=calcUser[u];  
        }
  
        for(int u =0;u<users.length;u++) {
        
            if(calcUser[u]>=users[u][1])
            {
                cost-=calcUser[u];
                register++;
            }
            
        }
        if(register>maxRegister) {
            maxRegister = register;
            maxCost = cost;
        }
        else if (register==maxRegister&&cost>maxCost) {
            maxCost = cost;
        }
            
            
            
            
    }
    
    static void combination(int index) {
        if(index == emoticonCount)
        {
            resultCalculator();
            return ;
        }
        for(int i= 10;i<50;i+=10) {
            할인율list[index]=i;
            combination(index+1);
        }
    }
    
    public int[] solution(int[][] u, int[] e) {
        users =u;
        emoticons = e;
        emoticonCount = emoticons.length;
        할인율list = new int[emoticonCount];
        
        
        combination(0);
        
        
        /*
        
        7000원짜리 25%
        
        9000짜리 할인 X
        
        각 퍼센트를 
        완전 탐색이네
        
        
        
        */
        
        
        int[] answer = new int[2];
        
        answer[0] = maxRegister;
        answer[1] = maxCost;
        
        
        
        return answer;
    }
}