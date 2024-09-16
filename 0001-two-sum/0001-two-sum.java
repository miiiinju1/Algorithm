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
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i =0;i<nums.length;i++) {
            int num = nums[i];
            map.putIfAbsent(num, new ArrayList<>());
            map.get(num).add(i);

            int find = target - num;
            // 3, 4, 3이면 찾을 수 있을 것 같은데

            // 3, 4일 때 target = 6이면?

            if(map.containsKey(find)) {
                // value List에 최소한 한 개는 뜻이잖아요?
                var findMap = map.get(find);

                // 이거도 index처리가,,,,
                if(num==find) {
                    if(findMap.size()>1) {
                        return new int[] {i, findMap.get(0)};

                    }
                    continue;
                }
                return new int[]{i, findMap.get(0)};    
            }   
        }



        return null;
    }
}