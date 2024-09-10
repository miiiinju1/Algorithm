import java.util.*;

class Solution {
    public int removeElement(int[] nums, int val) {

        List<Integer> result = new ArrayList<>(nums.length);

        for(int num: nums) {
            if(num!=val) {
                result.add(num);
            }
        }

        for(int i= 0;i<result.size();i++) {
            nums[i] = result.get(i);
        }

        return result.size();

        
    }
}