class Solution {
    static int[] weak;
    static int[] dist;
    
    static int[] selected;
    static boolean[] visited;
    static boolean[] weakVisited;
    static int n;
//     static boolean check() {
//         for(int start = 0; start < weak.length; start++) {
//             // 각 시작점에 대해 확인
//             for(int i = 0; i < selected.length; i++) {
//                 int end = weak[start] + selected[i]; // 현재 친구가 커버할 수 있는 최대 거리
//                 int covered = 1; // 시작점도 커버하므로 1부터 시작

//                 for(int next = start + 1; next < weak.length; next++) { // 다음 약한 지점부터 확인
//                     int nextPosition = weak[next];
//                     if(nextPosition > n) { // 원형이므로 n을 넘어가면 0부터 다시 시작
//                         nextPosition -= n;
//                     }
//                     if(end >= nextPosition) { // 현재 친구가 커버 가능
//                         covered++;
//                     } else {
//                         if(i + 1 < selected.length) { // 다음 친구로 변경
//                             end = nextPosition + selected[i + 1];
//                             i++; // 다음 친구 선택
//                             covered++;
//                         } else {
//                             break; // 더 이상 친구가 없으면 종료
//                         }
//                     }
//                 }

//                 if(covered >= weak.length) {
//                     return true; // 모든 약한 지점을 커버할 수 있으면 true 반환
//                 }
//             }
//         }
//         return false; // 모든 시작점에서 커버할 수 없으면 false 반환
//     }
    static boolean check() {
        for(int w = 0;w<weak.length;w++) {
            weakVisited = new boolean[weak.length];
            int wIndex = w;
            weakVisited[wIndex] = true;
            int i = 0;
            int count = 1;
            int start = weak[wIndex++];
            int end = start + selected[i++];
          
            while(count<weak.length) {
                if(wIndex==weak.length) {
                    wIndex = 0;
                }
                // System.out.println(start+" "+end+"count : "+count) ;
               
                
                if(!weakVisited[wIndex]) {
                    if(end<n) {
                        if(start<=weak[wIndex]) {
                            if(end>=weak[wIndex]) {
                               count++;  
                                weakVisited[wIndex] = true;
                            }
                            //만약 end가 삐져나가면
                            else {
                                if(i<selected.length) {
                                    while(weakVisited[wIndex]) {
                                        wIndex++;
                                        if(wIndex==weak.length) {
                                            wIndex=0;
                                        }
                                    }
                                    weakVisited[wIndex] = true;
                                    count++;
                                    start = weak[wIndex];
                                    end = start + selected[i++];
                                }
                                else {
                                    break;
                                }
                            }
                        }
                        //start가 삐져나갔는데
                        else {
                            if(i<selected.length) {
                                 while(weakVisited[wIndex]) {
                                        wIndex++;
                                        if(wIndex==weak.length) {
                                            wIndex=0;
                                        }
                                    }
                                    weakVisited[wIndex] = true;
                                    count++;
                                    start = weak[wIndex];
                                end = start + selected[i++];
                            }
                            else {
                                break;
                            }
                        }
                    }
                    else if(end>=n) {
                        if(start<=weak[wIndex]) {
                            if(end>=weak[wIndex]) {
                                count++;
                                weakVisited[wIndex] = true;
                            }
                            else {
                                if(i<selected.length) {
                                    while(weakVisited[wIndex]) {
                                        wIndex++;
                                        if(wIndex==weak.length) {
                                            wIndex=0;
                                        }
                                    }
                                    weakVisited[wIndex] = true;
                                    count++;
                                    start = weak[wIndex];
                                    end = start + selected[i++];
                                }
                                else {
                                     break;
                                }
                            }
                        }
                        else if(start>weak[wIndex]) {
                            if(end%n>=weak[wIndex]) {
                                 count++;
                                weakVisited[wIndex] = true;
                            }
                            else {
                                if(i<selected.length) {
                                   while(weakVisited[wIndex]) {
                                        wIndex++;
                                        if(wIndex==weak.length) {
                                            wIndex=0;
                                        }
                                    }
                                    weakVisited[wIndex] = true;
                                    count++;
                                    start = weak[wIndex];
                                    end = start + selected[i++];
                                }
                                 else {
                                    break;
                                }
                            }
                        }
                        
                        
                    }
                    
                }
                wIndex++;
                
            }
            // if(selected.length==3) {
            //     for(int t = 0;t<selected.length;t++) {
            //         System.out.print(selected[t]+" ");
            //     }
            //     System.out.println("---");
            //     System.out.println(w);
            //     System.out.println(count);
            //     for(int z= 0;z<weak.length;z++) {
            //         System.out.print(weakVisited[z]+" ");
            //     }
            //     System.out.println();
            //     System.out.println(start+" "+end);
            // }
            if(count==weak.length) {
                
                return true;
            }
        }
        
        return false;
        
    }
    static boolean permutation(int depth, int length) {
       
        if(depth==length) {
            if(check()) {
                return true;
            }
            return false;
        }
        if(depth==0) {
            selected = new int[length];
            visited = new boolean[dist.length];
        }
        
        for(int i = 0;i<dist.length;i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected[depth] = dist[i];
                if(permutation(depth+1, length))
                    return true;
                visited[i] = false;
            }
        }
        
        return false;
    }
    

    
    public int solution(int nn, int[] w, int[] d) {
        weak = w;
        dist = d;
        n = nn;
        
        for(int len = 1;len<=dist.length;len++) {
            if(permutation(0,len)) {
                return len;
            }
        }
       
        
        return -1;
    }
}