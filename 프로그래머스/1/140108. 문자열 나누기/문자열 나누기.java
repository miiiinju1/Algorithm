class Solution {
    public int solution(String s) {
        char[] str = s.toCharArray();

        int count = 0;
        
        char pivot = str[0];
        int pivotCount = 1;
        int otherCount = 0;
        for(int i= 1;i<str.length;i++) {
            if(pivotCount==otherCount) {
                pivotCount = 0;
                otherCount = 0;
                pivot = str[i];
                count++;
            }
            
            if(pivot == str[i]) {
                pivotCount++;
                
            }
            else {
                otherCount++;
            }
            
        }
        if(pivotCount>0 || otherCount >0) {
            count++;
        }
        return count;
    }
}