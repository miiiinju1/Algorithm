import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

        int time = 0; 
        int answer = 0; 
        int i = 0; 
        int count = 0;
        while (count < jobs.length) {
            while (i < jobs.length && jobs[i][0] <= time) {
                pq.add(jobs[i++]);
            }
            if (pq.isEmpty()) {
                time = jobs[i][0];
            } else {
                int[] job = pq.poll();
                time += job[1];
                answer += time - job[0];
                count++;
            }
        }

        return answer / jobs.length;
    }
}

/*
0 4
1 3

4 7-1 = 6
10 


3 7 = 10


3 7 10

4 + 

0 5

1 3


5 + 7 = 12

3 + 9 = 12








0 3
1 9
2 6


0 3
  3  9
       18
       
       1 5
       1 5






*/