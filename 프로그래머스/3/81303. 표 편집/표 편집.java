import java.util.*;
class Node {
    
    int prev,next;
    
    public Node(int p, int n) {
        this.prev = p;
        this.next = n;
    }
    // public String toString() {
    //     return " prev:"+prev+" next:"+next;
    // }
}

class Solution {
    static Stack<Integer> stack = new Stack<>();
    static int end;
    static int start;
    static boolean[] original;
    static int now;
    static Node[] map;
    static void U(int value) {
        for(int i= 0;i<value;i++) {
            if(map[now].prev!=-1)
            now = map[now].prev;
        }
        // System.out.println(value);
    }
     static void D(int value) {
        for(int i= 0;i<value;i++) {
            if(map[now].next!=-1)
                now = map[now].next;
        }
        // System.out.println(value);
        
    }
     static void C() {
         Node node = map[now];
          original[now] = false;
         if(node.prev!=-1&&node.next!=-1){
             stack.add(now);
              map[node.prev].next = node.next;
             now = node.next;
             map[node.next].prev = node.prev;//다음 거 의 prev를 제대로 맞춰주기
         } 
         else {
            if(node.next==-1) { //마지막 행이면
             stack.add(now);
             map[node.prev].next = -1;//이전 행의 다음을 -1로 설정해서 마지막으로
             now = node.prev;
             end = node.prev;
            }
            else if(node.prev==-1) { //첫번째 행이면
             stack.add(now);
              now = node.next;
            start = node.next;
             map[node.next].prev = -1;
           
             }
         }
         
         
    }
     static void Z() {
         int rollback = stack.pop();
         Node node = map[rollback];
          original[rollback] = true;
         
        
         
         //만약 맨 앞에거 지웠으면
         if (node.prev ==-1) {
             map[node.next].prev = rollback;
            
             start = rollback;
         }
         //맨 뒤에거 지웠으면
         else if(node.next==-1) {
             map[node.prev].next = rollback;
             end = rollback;
         }
         else {       //중간 거 지웟을 떈
              map[node.prev].next = rollback;
        //앞의 next를 중간으로
            map[node.next].prev = rollback;
        //뒤의 prev를 중간으로
        //중간의 prev, next를 기존처럼
         }
         
         
         
        
    }
    public String solution(int n, int k, String[] cmds) {
        //고유 값 초기화로 
        original = new boolean[n];
        Arrays.fill(original,true);
        map = new Node[n];
        start = 0;
        now = k;
        end = n-1;
        for(int i= 0;i<n;i++) {
            int prev = i-1;
            int next = i+1;
        
            if(next==n)
            {
               next = -1;
            }
            map[i] = new Node(prev, next);
        }
        
        for(String cmd:cmds) {
            String[] cmdInfo = cmd.split(" ");
            String operator = cmdInfo[0];
            String value="";
            if(cmdInfo.length==2)
               value = cmdInfo[1];
    
            switch(operator) {
                case "U" :
                    U(Integer.parseInt(value));
                    break;
                case "D" :
                    D(Integer.parseInt(value));
                    break;
                case "C" :
                    try{
                    C();}
                    catch(Exception e) {}
                    break;
                case "Z" :
                    Z();
            }
          
        }
        
        
    StringBuilder sb = new StringBuilder();
        for(int i= 0;i<n;i++) {
            if(!original[i]) {
                sb.append("X");
            }
            else
                sb.append("O");
        }
       
      
        
        return sb.toString();
    }
}


/*

0 1 2 3 4 5 6
1 4 4 4 5 6 -1

삭제하려는[i] =map[삭제하려는 기존[i]]
그리고 
now = 삭제하려는[i]; 이게 일반적일 때

마지막 행을 삭제하려고 하면

if(map[i]==-1) {
    마지막가리키는 애= -1
    마지막 값 
}
Z는 스택에 담아두고 계속 복구






*/