class Solution {
    public int jump(int[] nums) {
        if(nums.length==1) {

            return 0;


        }
         int[] ary = new int[nums.length];
        for(int i= 0;i<nums.length;++i) {
            int max = Math.min(nums[i]+i, nums.length);
            ary[i] = max;
        }
        //        farest? = [2,4,3,4,4];

        // 첫 지점부터 가면서 maxIndex를 갱신시켜가면서 canGoFar이란 값이 nums.length-1이 될 때까지 반복한다?


        
        int count = 1;
        int canGoFar = Math.min(nums.length-1, ary[0]);
        if(canGoFar == nums.length-1) {
            return count;
        }
        int i = 1;

        while(true) {
            int nowGoFar = canGoFar;
            for(;i<=nowGoFar;++i) {
                canGoFar = Math.max(canGoFar, Math.min(nums.length-1,nums[i] + i));
            }
            ++count;

            if(canGoFar == nums.length-1) {
                break;
            }
        }

        return count;

    }
}