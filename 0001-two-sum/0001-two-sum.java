import java.util.*;
class Solution {
    static class Node {
        int value, index;

        public String toString() {
        
            return value+" "+index;
        }

        public Node (int value, int index) {
            this.index = index;
            this.value = value;
        }

    }
    public int[] twoSum(int[] nums, int target) {
         // Node배열로 만든 뒤에
        Node[] nodes = new Node[nums.length];

        for(int i = 0;i<nums.length;i++) {
            nodes[i] = new Node(nums[i], i);
        }
        // value를 기준으로 정렬하고
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.value, o2.value));
        // 2 5 7 9

        // 2를 골랐을 때

        // 5 7 9를 대상으로 이분탐색을 진행할텐데

        // lo = 0, hi = 2에 있을텐데, mid = 1
        // 7이 6보다 크니깐 hi = 1

        // lo = 0, hi = 1

        // hi = 1 ary[hi] = 7 찾는 값이 아닌데도 있는고

        // target = 8
        // 6을 찾아야하잖아요?
        // 하나씩 집어서 i +1 ~ hi까지 이분탐색
        for(int i= 0;i<nodes.length-1;++i) {
            Node from = nodes[i];
            int to = target - from.value;

            // 이분 탐색을 i+1, hi까지 하게 되는데

            int lo = i, hi = nodes.length-1;

            // 이분탐색 lo, hi에서 지금은 hi를 줄이는 탐색 같은 값이 5 5 5 가 있으면 가장 작은 5를 찾는  이럴 때는 lo가 찾으려는 값 index의 -1이 되어야한다고 배웠거든요

            // 0~ 5
            // lo = -1 , hi = 5둬야한다고 들었어요
            // lo -1, hi = 0;
            // 0으로 도출



            // 0~5 lo가 증가

            // lo = 0, hi = 6

            // lo 5, hi = 6
            // lo가 6을 가리키겠죠,
            // 길이인덱스가 5까지일때

            //i가 4일 때, hi= 
            // lo = 5, hi = 5

            while(lo+1<hi) {
                int mid = (hi-lo)/2 + lo;

                if(nodes[mid].value >= to) {
                    hi = mid;
                } else {
                    lo = mid;
                }
                // nodes[mid]가 target보다 크면 hi를 줄이고, 
                
                // 작으면 lo를 증가시켜서 값을 찾아야하는데
            }

            // 없을 수도 있으니깐
            if(nodes[hi].value == to) {
                return new int[]{from.index, nodes[hi].index};
            }
        
        }




        return null;
    }
}