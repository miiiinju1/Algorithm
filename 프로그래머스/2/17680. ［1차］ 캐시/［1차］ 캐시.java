import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        ArrayDeque<String> cache = new ArrayDeque<>();
        int executionTime = 0;
        for(String city0 : cities) {
            String city = city0.toUpperCase();
            
            if(cache.contains(city)) {
                executionTime++;
                cache.remove(city);
                cache.add(city);
            }
            else {
                if(cache.size()>=cacheSize) {
                    cache.poll();
                }
                if(cache.size()<cacheSize)
                    cache.add(city);
                executionTime+=5;
            }
            
        }

        return executionTime;
    }
}