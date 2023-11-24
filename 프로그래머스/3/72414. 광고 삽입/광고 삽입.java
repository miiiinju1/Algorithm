class Solution {
    static int[] times;
    static int play_time;
    static int adv_time;
    static long sum = 0L;
    static long max = 0L;
    static int max_index=0;
    public static int convertToSeconds(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second = Integer.parseInt(parts[2]);
        
        return hour * 3600 + minute * 60 + second;
    }
    
    public static void add(int start, int end) {
        times[start]+=1;
        if(end<play_time) {
            times[end]-=1;
        } 
        
    }
    public static String convertToTime(int seconds) {
         int hour = seconds / 3600;
        int min = (seconds % 3600) / 60;
        int sec = seconds % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
        
    }
    
    public String solution(String pt, String at, String[] logs) {
        play_time = convertToSeconds(pt);
        times = new int[play_time+1];
        adv_time = convertToSeconds(at);
        
        for(String log : logs) {
            
            String[] detail = log.split("-");
            int start = convertToSeconds(detail[0]);
            int end = convertToSeconds(detail[1]);
            add(start,end);
        }
        
        for(int i=1;i<play_time;i++) {
            times[i]+=times[i-1];
        }
        for(int i=0;i<adv_time;i++) {
            sum+=times[i];
        }
        
        max=sum;
        
        int limit = play_time-adv_time;
        // if(play_time<=5)
        for(int i=1;i<=limit;i++) {
            // System.out.println(i+" "+sum);
            sum+=times[i+adv_time-1];
            sum-=times[i-1];
            if(sum>max) {
                max = sum;
                max_index= i;
            }
        }
        
        System.out.println(max_index);
        return convertToTime(max_index);
    }
}


// 0 0 1 2 2 -1
//처음은 adv-1까지 더하고
// play-adv
// 0 1 2 3 4 5 
//      - - -
//        - -
//   01   