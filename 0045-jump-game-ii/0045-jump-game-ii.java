class Solution {
    public int jump(int[] nums) {
        // 먼저 dp 배열을 1차원으로 만든다
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // dp는, 처음에 Integer.MAX_VALUE로 초기화해두고, dp는 해당 지점에 도달할 수 있는 최소 점프 수를 저장하게 하는데
        dp[0] = 0;

        for(int i= 0;i<nums.length;++i) {
            if(dp[i] == Integer.MAX_VALUE) continue;

            int J = nums[i];// 최대 점프가능 횟수일테니
            for(int j= i;j<=Math.min(i+J, nums.length-1);++j) {
                dp[j] = Math.min(dp[j], dp[i]+1);
            }
        }

        // 각 지점 i에 도착하면 nums[i], 즉 j만큼 점프 횟수를 추가해서 넣어보고, 각 최소 접프로 도달할 수 있으면 갱신하는 방식으로 풀 것 같습니다
        return dp[nums.length-1];
    } 
}