class Solution {
    static int[] weak;
    static int[] dist;
    
    static int[] selected;
    static boolean[] visited;
    static boolean[] weakVisited;
    static int n;

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