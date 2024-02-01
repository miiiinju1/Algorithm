import java.util.*;
import java.util.stream.*;
class Solution {
    public int[] solution(int n, long left, long right) {
        return LongStream.rangeClosed(left,right)
            .mapToInt(value -> (int) (Math.max(value / n, value % n) +1))
            .toArray();
        
    }
}
