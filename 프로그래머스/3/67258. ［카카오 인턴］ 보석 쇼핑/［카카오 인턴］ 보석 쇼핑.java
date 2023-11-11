import java.util.*;
class Solution {
    static HashSet<String> set = new HashSet<>();
    public int[] solution(String[] gems) {
        //투 포인터
        
        for(String gem: gems) {
            set.add(gem);
        }
        
        HashMap<String,Integer> tempMap = new HashMap<>();
        
        int l=0, r=0;
        int ml=0, mr=-1;
        int min = gems.length+2;
        while (true) {
            if(tempMap.keySet().size()<set.size()) {//더 적으면 
                 if(r>=gems.length)
                     break;
                if(tempMap.containsKey(gems[r])) {
                    tempMap.replace(gems[r],tempMap.get(gems[r])+1);
                }
                else {
                    tempMap.put(gems[r],1);
                }
                r++; 
            }
            else  { //충분하면 줄여보기
                if(min>r-l+1) {
                    min =r-l+1;
                    ml = l;
                    mr = r;
                }
                if(tempMap.get(gems[l])==1){
                tempMap.remove(gems[l]);}
                else {
                    tempMap.replace(gems[l],tempMap.get(gems[l])-1);
                }
                l++;
            }
         
        }
        
        int[] answer =new int[2];
            answer[0]=ml+1;
            answer[1] = mr;
        return answer;
    }
}