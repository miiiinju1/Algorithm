import java.util.*;
import java.time.*;
import java.time.format.*;

class Record {
    LocalTime time;
    String number;
    
    public int hashCode() {
        return number.hashCode();
    }
    public boolean equals(Object o) {
        return this.number.equals(((Record)o).number);
    }
    
}
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String,LocalTime> map = new HashMap<>();
        TreeMap<String,Long> result = new TreeMap<>();
        for(String record : records) {
            String[] detail = record.split(" ");
            //시간
            LocalTime time = LocalTime.parse(detail[0], DateTimeFormatter.ofPattern("HH:mm"));

            //차량번호
            String number = detail[1];
            //입,출 여부
            if(detail[2].equals("IN")) {
                map.put(number,time);
            }
            //빠질 때
            else {
                LocalTime in = map.remove(number);
                
                Long minute = Duration.between(in,time).toMinutes();
                if(result.containsKey(number)) {
                    result.replace(number,result.get(number)+minute); 
                }  
                else {
                    result.put(number,minute);
                }
            }
            
        }
        System.out.println(result);
        //남은 차
        //23:59
        LocalTime outTime = LocalTime.parse("23:59",DateTimeFormatter.ofPattern("HH:mm"));
        for(String number : map.keySet()) {
            
            Long minute = Duration.between(map.get(number),outTime).toMinutes();
            
             if(result.containsKey(number)) {
                    result.replace(number,result.get(number)+minute); 
                }  
                else {
                    result.put(number,minute);
                }
            
        }
        ArrayList<Integer> resultList = new ArrayList<>();
        for(String number: result.keySet()) {
            Long minutes=result.get(number); //시간
            
            if(minutes<=fees[0])
                resultList.add(fees[1]);
            else {
                int sum = fees[1];
                System.out.print(minutes+" ");
                
                sum += ((minutes-fees[0])/fees[2])*fees[3];
                if((minutes-fees[0])%fees[2]!=0) 
                    sum+=fees[3];
                
                resultList.add(sum);
                
            }
        }
        
        int[] answer = new int[resultList.size()];
        for(int i = 0;i<answer.length;i++) {
            answer[i] = resultList.get(i);
        }
        
        return answer;
    }
}