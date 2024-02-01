
class Solution {
    public int[] solution(int n, long left, long right) {
        
        int start = (int)(left / n) ;
        int startLess = (int)(left % n);
        
        int end = (int)(right /n);
        int endLess = (int)(right % n);
        
        int[] array = new int[(int)(right-left+1)];
        int index = 0;
        try {
        for(int i = start; i<=end;i++) {
            if(i==start) {
                for(int j = startLess;j < n;j++) {
                    if(j<=i) {
                        array[index++] = i+1;
                    }
                    else {
                        array[index++] = j+1;
                    }
                }
            }
            else if (i==end) {
                for(int j = 0;j <= endLess;j++) {
                    if(j<=i) {
                        array[index++] = i+1;
                    }
                    else {
                        array[index++] = j+1;
                    }
                }
            }
            else {
                for(int j = 0;j < n ;j++) {
                    if(j<=i) {
                        array[index++] = i+1;
                    }
                    else {
                        array[index++] = j+1;
                    }
                }
            }
            
        }

        }
        catch(Exception e) {
            
        }
        return array;
    }
}

//   0 1 2 3 4

//0  1 2 3 4 5
//1  2 2 3 4 5
//2  3 3 3 4 5
//3  4 4 4 4 5
//4  5 5 5 5 5



// 6이면 

//start / n 이면 줄 수 

// 2번째 줄이면 2부터 시작

// //