import java.util.*;

class Log {
    String uid;
    boolean type;
    public Log(String uid,boolean type ) {
        this.uid = uid;
        this.type= type;
    }
}


class Solution {
    //TRUE 들어옴
    //FALSE 나감
    public String[] solution(String[] records) {
        
        ArrayList<Log> results = new ArrayList<>();
        
        HashMap<String, String> nicknames = new HashMap<>();
        
        for(String record: records) {
            String[] log = record.split(" ");
            
            if(log[0].equals("Leave")) {
                results.add(new Log(log[1], false));
            }
            else {
                if(log[0].equals("Enter")) {
                    results.add(new Log(log[1], true));
                }
                
                if(!nicknames.containsKey(log[1]))
                    nicknames.put(log[1], log[2]);
                else {
                  nicknames.replace(log[1], log[2]);
                }
            }
        }
        
        String[] answer = new String[results.size()];
        
        for(int i= 0;i<results.size();i++) {
            Log result = results.get(i);
            if(result.type) {
                
                answer[i] = String.format("%s님이 들어왔습니다.",nicknames.get(result.uid));
                
            }
            else {
                answer[i] = String.format("%s님이 나갔습니다.",nicknames.get(result.uid));
                
            }
            
            
        }
        return answer;
    }
}


//기록 -> 닉네임 uuid 가지고 있고, come, out 이런 기록

//uuid -> 닉네임