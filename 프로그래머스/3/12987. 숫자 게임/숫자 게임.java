import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        //A배열 정렬하고
        Arrays.sort(A);        
        //B배열 정렬해서
        Arrays.sort(B);
        
        int iA = 0;
        int iB = 0;
        
        int count = 0;
        while(iA<A.length && iB<B.length) {
        //B보다 A가 더 작으면 둘다 index 증가
            if(B[iB]>A[iA]) {
                iB++;
                iA++;
                count++;
            }
            else {
            //A가 B보다 같거나 크면 B만 index증가
                iB++;

            }
        }
        //둘 중에 하나라도 최대 index가면 끝
        return count;        
    }
}