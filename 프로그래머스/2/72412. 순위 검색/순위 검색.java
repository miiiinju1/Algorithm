import java.util.*;


class Solution {
    static int language(String input) {
        if(input.equals("cpp")) {
            return 1000;
        }
        else if(input.equals("java")) {
            return 2000;
        }
        else if(input.equals("-"))
            return 0;
        return 3000;
    }
    
    static int occupation(String input) {
        if(input.equals("backend")) {
            return 100;
        }
        else if (input.equals("-")) {
            return 0;
        }
        return 200;
    }
    
    static int career(String input) {
        if(input.equals("senior")) {
            return 10;
        }
        else if (input.equals("-")) {
            return 0;
        }
        return 20;
    }
    
    static int soulfood(String input) {
        if(input.equals("pizza")) {
            return 1;
        }
        else if (input.equals("-")) {
            return 0;
        }
        return 2;
    }
    
    static void add(String language, String occupation, String career, String soulfood, int grade) {
        int value = 0;
        
        int[] ary = new int[4];
        ary[0] = language(language);
        ary[1] = occupation(occupation);
        ary[2] = career(career);
        ary[3] = soulfood(soulfood);
        sumSet = new HashSet<>();
       
        
        calculateSubsetsSum(ary,0,0);
        
        
        for(int val : sumSet) {
             map.get(val).add(grade);
        }
        
       

    }
    static HashSet<Integer> sumSet;
    
     private static void calculateSubsetsSum(int[] ary, int index, int currentSum) {
        if (index == ary.length) {
            sumSet.add(currentSum); 
            return;
        }
        calculateSubsetsSum(ary, index + 1, currentSum);
        calculateSubsetsSum(ary, index + 1, currentSum + ary[index]);
    }
    
    static int get(String language, String occupation, String career, String soulfood, int grade) {
        int value = 0;
        value += language(language);
        value += occupation(occupation);
        value += career(career);
        value += soulfood(soulfood);
        ArrayList<Integer> list = map.get(value);
        int lo = -1, hi = list.size();
        
        while(lo+1<hi) {
            int mid = (hi-lo)/2+lo;
            
            if(list.get(mid)>=grade) {
                hi = mid;
            }
            else {
                lo = mid;
            }   
        }

        return list.size()-hi;
    }
    
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    public int[] solution(String[] infos, String[] queries) {
        
        int value = 0;
        for(int a=0;a<=3;a++) {
            value+=(a*1000);
            for(int b = 0;b<=2;b++) {
                value+=(b*100);
                for(int c = 0;c<=2;c++) {
                    value+=(c*10);
                    for(int d = 0;d<=2;d++) {
                        value+=d;
                        map.put(value,new ArrayList<>());
                        value-=d;
                    }
                    value-=(c*10);
                }
                value-=(b*100);
            }
            
            value-=(a*1000);
        }
        
        for(String info : infos) {
            String[] command = info.split(" ");
            add(command[0],command[1],command[2],command[3],Integer.parseInt(command[4]));
        }
        
        for(int a=0;a<=3;a++) {
            value+=(a*1000);
            for(int b = 0;b<=2;b++) {
                value+=(b*100);
                for(int c = 0;c<=2;c++) {
                    value+=(c*10);
                    for(int d = 0;d<=2;d++) {
                        value+=d;
                        Collections.sort(map.get(value));
                        value-=d;
                    }
                    value-=(c*10);
                }
                value-=(b*100);
            }
            
            value-=(a*1000);
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for(String query : queries) {
            String[] command = query.split(" and ");
            String[] temp = command[3].split(" ");
            command[3] = temp[0];
            int grade = Integer.parseInt(temp[1]);
            
            result.add(get(command[0],command[1],command[2],command[3],grade));
        
        }
        
        
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}

