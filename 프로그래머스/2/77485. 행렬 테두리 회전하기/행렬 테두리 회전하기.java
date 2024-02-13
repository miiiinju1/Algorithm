import java.util.*;
class Solution {
    static ArrayList<Integer> list = new ArrayList<>();
    
    static int[][] map;
    
    static void rotate(int y1,int x1, int y2, int x2) {
        y1-=1;
        x1-=1;
        y2-=1;
        x2-=1;
        int prev = map[y1][x1];
        int min = prev;
        
        int temp;
        for(int j = x1+1;j<=x2;j++) {
            temp = map[y1][j];
            map[y1][j] = prev;
            prev = temp;
            min = Math.min(temp,min);
        }
        
        for(int i = y1+1;i<=y2;i++) {
            temp = map[i][x2];
            map[i][x2] = prev;
            prev = temp;
            min= Math.min(temp,min);
            
        }
        
        for(int j = x2-1;j>=x1;j--) {
            temp = map[y2][j];
            map[y2][j] = prev;
            prev = temp;
            min = Math.min(temp,min);
            
        }
        
        
        for(int i = y2-1;i>=y1;i--) {
            temp = map[i][x1];
            map[i][x1] = prev;
            prev = temp;
            min = Math.min(temp,min);
            
        }
        
        list.add(min);
    
    }
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        
        int value =1;
        for(int i= 0;i<rows;i++) {
            for(int j= 0;j<columns;j++) {
                map[i][j] = value++;
            }
        }
        
        for(int[] query: queries) {
            rotate(query[0],query[1],query[2],query[3]);
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}