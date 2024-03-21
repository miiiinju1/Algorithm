import java.util.*;
import java.util.stream.*;

class Child {
    Point left,right;
    public String toString() {
        return left+" "+right;
    }
}

class Point {
    int y, x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    @Override
    public int hashCode() {
        return this.y*31 + this.x;
    }
    @Override
    public boolean equals(Object o) {
        Point p = (Point)o;
        return this.y==p.y && this.x==p.x;
    }
    public String toString() {
        return this.y+" "+this.x;
    }
}

class Solution {
    static HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    static HashMap<Point, Child> map2 = new HashMap<>();
    static List<Integer> heights;
    static HashMap<Point, Integer> compute = new HashMap<>();
    
    public static Point dfs(int yIndex, int left, int right) {
        Point now = null;
        int nextYIndex=-1;
        a: for(int i = yIndex;i<heights.size();i++) {
            int y = heights.get(i);
            for(int x : map.get(y)) {
                if(now == null && x>=left && x<=right) {
                    now = new Point(y,x);
                    nextYIndex=i;
                    break a;
                }
            }
        }
        if(now==null) {
            return null;
        }
        Child c = map2.get(now);
        c.left = dfs(nextYIndex+1, left, now.x-1);
        c.right = dfs(nextYIndex+1, now.x+1, right);

        return now;
    }
    public List<Integer> preOrder = new ArrayList<>();
    public List<Integer> postOrder = new ArrayList<>();
    
    public void preOrder(Point now) {
        if(now==null) {
            return ;
        }
        Child c = map2.get(now);
        
        preOrder.add(compute.get(now));
        preOrder(c.left);
        preOrder(c.right);
        
    }
     public void postOrder(Point now) {
        if(now==null) {
            return ;
        }
        Child c = map2.get(now);
         
        postOrder(c.left);
        postOrder(c.right);
        postOrder.add(compute.get(now));
        
    }
    public int[][] solution(int[][] nodeinfos) {
        
        int index = 1;
        for(int[] nodeinfo : nodeinfos) {
            int y = nodeinfo[1];
            int x = nodeinfo[0];
            
            map.computeIfAbsent(y, k -> new ArrayList<>());
            map.get(y).add(x);
            Point p = new Point(y,x);
            compute.put(p, index++);
            map2.put(p,new Child());
        }
        
        heights = map.keySet().stream()
            .sorted((o1,o2)-> o2.compareTo(o1))
            .collect(Collectors.toList());
        
        
        dfs(0,0,100000);
        Point start = new Point(heights.get(0), map.get(heights.get(0)).get(0));
        preOrder(start);
        postOrder(start);
        
        int[][] answer = {
            preOrder.stream().mapToInt(Integer::intValue).toArray(),
            postOrder.stream().mapToInt(Integer::intValue).toArray(),
        };
        return answer;
    }
}