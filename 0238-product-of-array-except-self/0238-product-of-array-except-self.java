class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] asc = new int[nums.length];
        int[] desc = new int[nums.length];
        asc[0] = nums[0];
        for(int i = 1;i<nums.length;++i) {
            asc[i] = nums[i] * asc[i-1];
        }
        desc[nums.length-1] = nums[nums.length-1];
        for(int i = nums.length-2;i>=0;--i) {
            desc[i] = nums[i] * desc[i+1];
        }
        int[] result = new int[nums.length];

        for(int i = 0;i<nums.length;++i) {
            
            int a = 1, b = 1;

            if(i-1>=0) {
                a = asc[i-1];
            }
            if(i+1<nums.length) {
                b = desc[i+1];
            }
            result[i] = a*b;
        }
        return result;
    }
}