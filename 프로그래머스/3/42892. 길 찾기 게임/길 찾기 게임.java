import java.util.*;
class Solution {
    
    static class Point {
        int y,x;
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (y != point.y) return false;
            return x == point.x;
        }

        @Override
        public int hashCode() {
            int result = y;
            result = 31 * result + x;
            return result;
        }
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
        
         public String toString() {
            return y +" "+x;
        }
        
    }
    
    static class Child {
        Point left;
        Point right;
        
        public Child(Point left, Point right) {
            this.left = left;
            this.right = right;
        }
        
        public String toString() {
            return "Child:["+left +"] ["+right+"]";
        }
    }
    
    
    static HashMap<Point, Child> map = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> nodes = new HashMap<>();
    
    static Point left(Point point, int leftMin, int leftMax) {
        try {
        if(!(leftMin<=leftMax && leftMax>=0)) {
            return null ;
        }
        Point pick = null;
        
        int y = point.y-1;
            
        a : for(;y>=0;y--) {
            if(!nodes.containsKey(y)) {
                continue;
            }
            ArrayList<Integer> list = nodes.get(y);
            for(int child : list) {
            
                if(child>=leftMin && child <= leftMax) {
                    pick = new Point(y,child);
                    break a;
                }
            }
            
        }
    
        
        if(pick!=null && point.y-1>=0) {
            map.get(point).left = pick;
            
            left(pick, leftMin, pick.x-1);
            right(pick, pick.x+1, leftMax);
        }
        
        return pick;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    
    }
    
    
    static Point right(Point point, int rightMin, int rightMax) {
        
        if(!(rightMin<=rightMax && rightMin<=100000)) {
            return null;
        }
        
        Point pick = null;
        int y = point.y-1;
        
        a: for(;y>=0;y--) {
            if(!nodes.containsKey(y)) {
                continue;
            }
            ArrayList<Integer> list = nodes.get(y);
            
            for(int child : list) {
                if(child>=rightMin && child <= rightMax) {
                    pick = new Point(y,child);
                    break a;
                    
                }
            }
            
        }
        
        // System.out.println(point+" "+pick);
        
        
        if(pick!=null&& point.y-1>=0) {
            map.get(point).right = pick;
            left(pick, rightMin, pick.x-1);
            right(pick, pick.x+1, rightMax);
        }
        return pick;
        
    }
    
    
    
    static ArrayList<Integer> preOrder = new ArrayList<>();
    static ArrayList<Integer> postOrder = new ArrayList<>();
    
    static void preOrder(Point now) {
        
        preOrder.add(points.get(now));
        
        if(map.get(now).left!=null) {
            preOrder(map.get(now).left);
        }
        
        if(map.get(now).right!=null) {
            preOrder(map.get(now).right);
        }
        
        
    }
    
    static void postOrder(Point now) {
        
        if(map.get(now).left!=null) {
            postOrder(map.get(now).left);
        }
        
        if(map.get(now).right!=null) {
            postOrder(map.get(now).right);
        }
        
        postOrder.add(points.get(now));
        
    }
    static HashMap<Point, Integer> points = new HashMap<>();
    public int[][] solution(int[][] nodeinfos) {
        int startY = -1;
        int startX = -1;
        int index = 1;
        for(int[] nodeinfo: nodeinfos) {
            int x = nodeinfo[0];
            int y = nodeinfo[1];
            if(startY<y) {
                startY = y;
                startX = x;
            }
            
            Point p = new Point(y,x);
            map.put(p, new Child(
                null,
                null));
            
            points.put(p,index++);
            if(nodes.containsKey(y)) {
                nodes.get(y).add(x);
            }
            
            else {
                ArrayList list = new ArrayList<>();
                list.add(x);
                nodes.put(y,list);
            }
            
        }
        for(Map.Entry<Integer, ArrayList<Integer>> entry : nodes.entrySet() ) {
            Collections.sort(entry.getValue());
        }
       
        Point start = new Point(startY,startX);

        map.get(start).left = left(start, 0, startX-1);
        map.get(start).right = right(start, startX+1, 100000);
     
        preOrder(start);
        postOrder(start);
        // System.out.println(preOrder);
        // System.out.println(postOrder);
        int[][] answer = new int[2][preOrder.size()];
        
        for(int i =0;i<preOrder.size();i++) {
            answer[0][i] = preOrder.get(i);
            answer[1][i] = postOrder.get(i);
        }
        return answer;
    }
}