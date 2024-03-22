class Solution {
    public String[] solution(String[] s) {
        String findStr = "110"; 
        
        StringBuilder[] count = new StringBuilder[s.length];
        for(int i= 0;i<s.length;i++){
            int lastIndex = 0;
            count[i] = new StringBuilder();
            StringBuilder temp = new StringBuilder(); 

            for(char ch : s[i].toCharArray()) {
                temp.append(ch);
                if(temp.length() >= 3 && temp.substring(temp.length() - 3).equals(findStr)) {
                    temp.setLength(temp.length() - 3); // 마지막 3문자 제거
                    count[i].append(findStr);
                }
            }
            s[i] = temp.toString();
          
        }
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            StringBuilder sb = new StringBuilder(s[i]);
            
            int zeroIndex = sb.lastIndexOf("0") + 1; // 모든 "0"이 끝나는 지점 찾기
            String prefix = sb.substring(0, zeroIndex);
            String suffix = sb.substring(zeroIndex);
            StringBuilder result = new StringBuilder();
            result.append(prefix);
            result.append(count[i]);
            result.append(suffix);

            answer[i] = result.toString();
        }
//      
        
        return answer;
        
    }
}