import java.util.*;

class Solution {
    static ArrayList<Integer> bus = new ArrayList<>();
    
    static void setBusTime(int n, int t) {
        int time = 9*60;
        bus.add(time);
        
        for(int i = 1;i<n;i++) {
            time+=t;
            bus.add(time);
        }

    }
    
    static boolean canGo(int mid, int m) {
        int i = 0;
        
        for(Integer busTime : bus) {
            int busCount = 0;
            
            for(;i<timeTable.length;i++) {
                if(timeTable[i]>busTime) {
                    break;
                }
                else if(busCount >= m) {
                    break;
                }
                
                else if(mid<=busTime &&timeTable[i]>mid) {
                    return true;
                }
                else if(busCount<m) {
                    busCount++;
                }
            
            }
            if(busCount<m && mid<=busTime) {
                    return true;
            }
            
            
            
        }
        
        return false;
        
    }
    static int[] timeTable;
    
    public String solution(int n, int t, int m, String[] timeTableString) {
        setBusTime(n,t);
        timeTable = new int[timeTableString.length];
        
        for(int i= 0;i<timeTable.length;i++) {
            String[] input = timeTableString[i].split(":");
            int temp = Integer.parseInt(input[0])*60 + Integer.parseInt(input[1]);
            timeTable[i] = temp;
        }
        Arrays.sort(timeTable);

        
        int lo = 0, hi = 24*60;
       
        while(lo+1<hi) {
            
            int mid = (hi-lo)/2+lo;
            
            if(canGo(mid, m)) {
                lo = mid;
            }
            else {
                hi = mid;
            }
            
            
        }
     
        String answer =  String.format("%02d",lo/60) +":"+String.format("%02d",lo - ((lo/60)*60));
        return answer;
    }
}