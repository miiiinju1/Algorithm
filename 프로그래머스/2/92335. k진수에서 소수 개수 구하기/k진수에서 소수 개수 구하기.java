class Solution {
    public boolean 소수 (String str) {
        long number = Long.parseLong(str);
        if(number==1)
            return false;
        long sqrtNumber = (long)Math.sqrt(number);
        for(int i = 2;i<=sqrtNumber;i++) {
            if(number%i==0) {
                return false;
            }
        }
        return true;
    }
    public int solution(int n, int k) { 
        String[] strArray = Integer.toString(n,k).split("0");
        int answer=0;
        for(String str : strArray) {
            if(!str.equals("")&&소수(str)) {
                answer++;
            }
        }
        
        return answer;
    }
}