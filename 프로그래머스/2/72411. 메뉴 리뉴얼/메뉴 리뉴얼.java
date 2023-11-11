import java.util.*;
class Solution {
    static HashMap<String,Integer> map;
    
    
    //재귀 조합
    static void search(StringBuilder temp,String str, int start,int len,int count)  {

        if(len==count) {
            String result = temp.toString().chars()    
                            .sorted()
                            .collect(StringBuilder::new,
                                    StringBuilder::appendCodePoint,
                                    StringBuilder::append)
                            .toString();
            if(map.containsKey(result)) {
                map.replace(result,map.get(result)+1);
            
            }
            else {
                map.put(result,1);
            }
        }
        for(int i = start;i<str.length();i++) {
            StringBuilder sb =new StringBuilder(temp);
            sb.append(str.charAt(i));
            search(sb,str,i+1,len,count+1);
        }
        return ;
        
    }
    static void combination(String str, int len) {
        
       
        for(int i = 0;i<str.length();i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(str.charAt(i));
            search(sb,str,i+1,len,1);
             
        }
        
        
    }
    static ArrayList<String> list = new ArrayList<>();
    
    static void resultCalc() {
        
        ArrayList<String> temp = new ArrayList<>();
        int max =2;
        for(String str : map.keySet()) {
            if(map.get(str)>max) {
                max = map.get(str);
                temp = new ArrayList<>();
                temp.add(str);
            }
            else if(map.get(str)==max) {
                temp.add(str);
            }
        }
        
        System.out.println("map : "+map+"temp : "+temp);
        for(String str: temp) {
            list.add(str);
        }
        
    }
    
    public String[] solution(String[] orders, int[] course) {
        
       
        for(int i : course) { 
            map = new HashMap<>();
            for(String order: orders) {
                
                combination(order,i);
                
            }
            resultCalc(); 
        }
        Collections.sort(list);
        
        String[] answer = new String[list.size()];
        for(int i =0;i<answer.length;i++) {
            
          answer[i] = list.get(i);
        }
        return answer;
    }
}

//A4, B2 C6 D3 E3 F2 G2

//모든 조합 구해서 HashMap에 count하면서 넣어두고
//최대 55개 * 10

//