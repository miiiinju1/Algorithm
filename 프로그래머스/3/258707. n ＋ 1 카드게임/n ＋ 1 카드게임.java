import java.util.*;

class Pair {
    int p1, p2;
    public Pair(int p1,int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }
    public String toString() {
        return p1+" "+p2;
    }
}

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n+1;

        Queue<Pair> q = new LinkedList<>();
        
        HashSet<Integer> now = new HashSet<>();
        
        HashSet<Integer> candidate = new HashSet<>();
        
        int targetIndex = n/3;
        int nowIndex = 0;
        for(;nowIndex<targetIndex;nowIndex++) {
            now.add(cards[nowIndex]);
        }
        // System.out.println(now);
        
        for(Integer i : now) {
            if(now.contains(target-i) && i>target-i) {
                q.add(new Pair(i,target-i));
            }
        }
        
        targetIndex+=2;

        System.out.println(q);
        int round = 1;
        
        while(true) {
            if(targetIndex>n) {
                break;
            }
            
            if(q.isEmpty()) {
                //기존 애랑 되는 놈이면 일단 넣어
                for(;nowIndex<targetIndex;nowIndex++) {
                    if(now.contains(target-cards[nowIndex]) && coin>0) {
                        now.add(cards[nowIndex]);
                        now.add(target-cards[nowIndex]);
                        q.add(new Pair(cards[nowIndex],target-cards[nowIndex]));
                        coin-=1;
                    }
                    else {
                        candidate.add(cards[nowIndex]);
                    }
                }
                
                if(q.isEmpty()) {
                //Staging안에서 조합 찾아야함
                    if(coin<2) {
                        break;
                    }
                    else {
                        //조합 있으면 coin-=2하고 끝
                        boolean success = false;
                        for(Integer i : candidate) {
                            if(candidate.contains(target-i) && coin >= 2 && i>target-i) {
                                candidate.remove(i);
                                candidate.remove(target-i);
                                coin-=2;
                                success = true;  
                                break;
                            }
                        }
                        if(!success) 
                            break;
                    }
                }
                else {
                    Pair poll = q.poll();
                    now.remove(poll.p1);
                    now.remove(poll.p2);
                }                
            }
            
            else {
                //만약 버릴 수 있으면
                for(;nowIndex<targetIndex;nowIndex++) {
                    System.out.println(cards[nowIndex]);
                    if(coin>0 && now.contains(target-cards[nowIndex]) ) {
                        now.add(cards[nowIndex]);
                        now.add(target-cards[nowIndex]);
                        q.add(new Pair(cards[nowIndex],target-cards[nowIndex]));
                        coin-=1;
                    }
                    else {
                        candidate.add(cards[nowIndex]);
                    }
                }
            
                Pair poll = q.poll();
                now.remove(poll.p1);
                now.remove(poll.p2);
                
                
            }
            
            round++;targetIndex+=2;
        }
        
        return round;
    }
}