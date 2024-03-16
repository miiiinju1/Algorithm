import java.util.*;

class Solution {
    
    static class Node {
        List<Integer> in = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        
    }
    static HashMap<Integer, Node> map = new HashMap<>();
    
    static boolean[] visited = new boolean[1000001];
    
    
    static void dfs(int now, HashSet<Integer> visiting) {
        
        if(!map.containsKey(now))
            return;
        
        Node nowNode = map.get(now);
        if(!visited[now]) {
            
            
            if(nowNode.out.size()>2) {
                answer[0] = now;
                return;
            }
         
            
            
            if(nowNode.out.size()==1) {
                //일단 도넛 의심
                int next = nowNode.out.get(0);

                if(!visiting.contains(next)) {
                    visiting.add(next);
                    dfs(next, visiting);
                    return ;
                }
                else {
                    //도넛
                    doughnut(next);
                    return ;    
                }


            }
            else if(nowNode.out.isEmpty()) {
                //막대
                stick(now);
                return ;
            }
            else if(nowNode.out.size()>=2) {
                if(nowNode.in.size()>=2) {
                //얘는 확실한 8자
                    
                    
                    eight(now);
                    return ;
                }

                else {
                    answer[0] = now;
                    //근본 X 놈
                    return;
                }
            }

        }
        
    }
    
    
    static void search(int now, int limit) {
        if(map.get(now).out.size()>=limit) {
            answer[0] = now;
            return;
        }
        
        if(map.get(now).in.isEmpty()) {
            if(answer[0]==0) {
                answer[0]=now;
            }
        }
        for(Integer prev : map.get(now).in) {
            if(!visited[prev]) {
                visited[prev] = true;
                search(prev,limit); 
            }            
        }
        
    }
    
    static void eight(int start) {
        Node node = map.get(start);
        visited[start] = true;
        search(start, 3);
        answer[3]++;
        
    }
    
    static void doughnut(int start) {
        Node node = map.get(start);
        visited[start] = true;
        search(start,2);
        answer[1]++;
    }
    
    static void stick(int start) {
        Node node = map.get(start);
        visited[start] = true;
        search(start,2);
        answer[2]++;
    }
    static int[] answer = {0,0,0,0};
    public int[] solution(int[][] edges) {

        int max = 0;
        for(int[] edge: edges) {
            
            int a = edge[0];
            int b = edge[1];
            
            map.computeIfAbsent(a, k -> new Node());
            map.computeIfAbsent(b, k -> new Node());
            max = Math.max(a,Math.max(b,max));
            map.get(a).out.add(b);
            map.get(b).in.add(a);
        }
        
//         for(int i= 1;i<=max;i++) {
//             if(!visited[i]) {
//                 dfs(i,new HashSet<>());
//             }
//         }
       
        int total = 0;
        for(int i= 1;i<=max;i++) {
            if(!map.containsKey(i))
                continue;
            Node node = map.get(i);
            if(node.out.isEmpty()) {
                answer[2]++;
            }
            else if(node.out.size()==2) {
                if(node.in.size()>0) 
                    answer[3]++;
                else {
                  answer[0] = i;  
                  total = node.out.size();
                }
            }
            else if (node.out.size()>2) {
                answer[0] = i;
                total = node.out.size();
            }
            
        }
        
        answer[1] = total - (answer[2]+answer[3]);
        return answer;
    }
}