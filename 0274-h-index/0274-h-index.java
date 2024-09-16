class Solution {
    public int hIndex(int[] citations) {
        int[] count = new int[1001];

        for(int citation: citations) {
            ++count[citation];
        }
        for(int i= 999;i>=0;--i) {
            count[i] += count[i+1];
        }

        for(int i = 1000;i>=0;--i) {
            if(i <= count[i]) {
                return i;
            }
        }
        return 0;
    }
}