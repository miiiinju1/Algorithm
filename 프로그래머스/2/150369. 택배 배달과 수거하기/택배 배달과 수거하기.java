class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        
        int i = n-1,j=n-1;
        long result = 0L;
        
        int nowDelivery = 0, nowPickup = 0;
        int maxDeliveryIdx = -1,maxPickupIdx =-1 ;
        while(i>=0||j>=0) {
            for(;i>=0;i--) {
            if(nowDelivery + deliveries[i] >= cap) {
                int diff = nowDelivery + deliveries[i] - cap;
                if(maxDeliveryIdx==-1&&deliveries[i]!=0) {
                    maxDeliveryIdx = i;
                }
                deliveries[i] = diff;
                nowDelivery = cap;
                break;

            }
            else {
                nowDelivery += deliveries[i];
                if(maxDeliveryIdx==-1&&deliveries[i]!=0) {
                    maxDeliveryIdx = i;
                }
                deliveries[i] = 0;
                
            }
            
        }
        
        for(;j>=0;j--) {
            if(nowPickup + pickups[j] >= cap) {
                if(maxPickupIdx==-1&&pickups[j]!=0) {
                    maxPickupIdx = j;
                }
                int diff2 = nowPickup + pickups[j] - cap;
                pickups[j] = diff2;
                nowPickup = cap;
                // System.out.printf("%d %d : %d, %d, %d\n",i,j, result,nowDelivery, nowPickup);
                break;

            }
            else {
                if(maxPickupIdx==-1&&pickups[j]!=0) {
                    maxPickupIdx = j;
                }
                nowPickup += pickups[j];
                pickups[j] = 0;
            }

        }
                
        int index = Math.max(maxDeliveryIdx,maxPickupIdx)+1;



        result += index*2;
        nowDelivery = 0;
        nowPickup = 0;
        // System.out.printf("result : %d %d : %d\n",maxDeliveryIdx,maxPickupIdx, result);

        maxDeliveryIdx=-1;
        maxPickupIdx=-1;
        if(i>=0&&deliveries[i]>0) {
            if(i+1<n)
                i++;
        }
        if(j>=0&&pickups[j]>0) {
            if(j+1<n)
                j++;
        }
    }
            // System.out.printf("%d %d : %d\n",i,j, result);
        
//         for(;j>=0;j--) {
//             if(nowPickup + pickups[j] >= cap) {
//                 if(maxPickupIdx==-1&&pickups[j]!=0) {
//                      maxPickupIdx = j;
//                 }
//                 int diff2 = nowPickup + pickups[j] - cap;
//                 pickups[j] = diff2;
//                 nowPickup = cap;
//             }
//             else {
//                 nowPickup += pickups[j];
//                 if(maxPickupIdx==-1&&pickups[j]!=0) {
//                    maxPickupIdx = j;
//                 }
//                 pickups[j] = 0;
                
//              }                
//         }
//         int index = Math.max(maxDeliveryIdx,maxPickupIdx)+1;

//         result += index*2;
            // System.out.printf("%d %d : %d\n",maxDeliveryIdx,maxPickupIdx, result);
        
        return result;
    }
}

//7 7 10 6 


/*


맨 뒤에서 배달 수 가득 채워서 가고, 

 max(수거 끝 집, 배달 끝 집) 
 
 




*/