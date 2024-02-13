import java.util.*;
class Solution {
    static class Name implements Comparable<Name> {
        String head;
        int number;
        int index;
        public Name(String h, String n, int index) {
            this.head = h;
            this.number = Integer.parseInt(n);
            this.index = index;
        }
        
        @Override
        public int compareTo(Name o) {
            
            if(this.head.equals(o.head)) {
                if(this.number == o.number) {
                    
                    return 0;
                }
                
                return Integer.compare(this.number,o.number);
                
                
            }
            return this.head.compareTo(o.head);
        }
    }
    public String[] solution(String[] files) {
        int index = 0;
        ArrayList<Name> list = new ArrayList<>();
        
        for(String file: files) {
            StringBuilder sb = new StringBuilder(file);
            StringBuilder head =new StringBuilder();
            StringBuilder number= new StringBuilder();
            
            int i = 0;
            for(;i<sb.length();i++) {
                char c = sb.charAt(i);
                
                if(Character.isDigit(c)) {
                    break;
                }
                else {
                    // sb.deleteCharAt(0);
                    head.append(c);
                }

            }
            
            int numberCount = 0;
            for(;i<sb.length();i++) {
                if(numberCount==5) {
                    break;
                }
                char c = sb.charAt(i);
                if(Character.isDigit(c)) {
                     // sb.deleteCharAt(0);
                    number.append(c);
                }
                else {
                    break;
                }
                numberCount++;
            }
            
            list.add(new Name(head.toString().toUpperCase(), number.toString(), index++));
            
        }
        Collections.sort(list);
            
        return list.stream().map(name -> files[name.index]).toArray(String[]::new);
    }
}