class Solution {
    public int[] solution(int n, int s) {
        //차이가 가장 적어야 곱이 커짐
        //ex 3, 5 보다 44가 크고
        //ex 2 5 보다 3 4가 큼
        if(n>s) {
            int[] answer = {-1};
            return answer;
        }
        
        //sum을 n만큼 균등하게 나누기
        int[] answer = new int[n];
        
        //만약 5 , 4면
        // 몫만큼 다 더하고
        int 몫 = s / n;
        
        for(int i= 0;i<n;i++) {
            answer[i] = 몫;
        }
        
        //나머지를 뒤에서부터 1씩 뿌리기
        int 나머지 = s%n;
        for(int i = n-1;i>=0;i--) {
            if(나머지 <= 0) {
                break;
            }
            answer[i] += 1;
            나머지--;
        }
        
        return answer;
    }
}