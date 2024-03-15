class Solution {
    
    static int i = 0, d;
    static boolean flag = true;
    static char dfs(int depth) {
if(i<0) {
                return 'f';
            }    
        if(depth>=d) {
                   
            return str[i--];
        } 

        char right;
        if(i<0) {
            right = '0';
        }
        else {
            right = dfs(depth+1);
            if(right=='f') {
                return 'f';
            }
        }
        
        char thisNode;
        if(i<0) {
            thisNode = '0';
        }
        else {
            thisNode = str[i--];
        }
        // System.out.println(depth+" this : "+thisNode);

        char left;
        if(i<0) 
            left = '0';
        else {
            left = dfs(depth+1);
            if(left=='f') {
                return 'f';
            }
        }
        // System.out.println(depth+" this : "+left);
        
        if(thisNode=='0' && (right=='1'||left=='1')) {
            // if(i<0)
            
            flag = false;
            return 'f';
        }
        
        return thisNode;
        
    }
    
    static char[] str;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        int index= 0;
        for(long number : numbers) {
            // if(number!=7)
                // continue;
            flag = true;
            str = Long.toBinaryString(number).toCharArray();    
            i=str.length-1;
            
            d = (int) (Math.log(str.length) / Math.log(2));
            if (Math.pow(2, d) < str.length) {
                d++;
            }
            d-=1;
            dfs(0);
            if(flag) {
                answer[index++]=1;
            }
            else {
                answer[index++]=0;
                
            }
        }
        
        
        
        return answer;
    }
}
