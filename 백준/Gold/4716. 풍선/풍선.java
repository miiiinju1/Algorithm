import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Team implements Comparable<Team>{
        int K, dA, dB;
        int diff;

        public Team(int k, int dA, int dB) {
            K = k;
            this.dA = dA;
            this.dB = dB;
            diff = Math.abs(dA - dB);
        }

        @Override
        public int compareTo(Team o) {
            return Integer.compare(o.diff, this.diff);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(N==0&&A==0&&B==0) {
                break;
            }
            PriorityQueue<Team> teams = new PriorityQueue<>();
            for(int n = 0;n<N;n++) {

                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int dA = Integer.parseInt(st.nextToken());
                int dB = Integer.parseInt(st.nextToken());

                teams.add(new Team(K, dA, dB));

            }
            long sum = 0;
            while(!teams.isEmpty()) {
                final Team team = teams.poll();
                if(team.dA>team.dB) {
                    //A가 더 멀면

                    //B부터 넣어보고
                    if(B-team.K>=0) {
                        B-=team.K;
                        sum += (team.K * team.dB);
                        team.K=0;
                    }
                    else {
                        sum+=(B*team.dB);
                        team.K-=B;
                        B=0;
                    }
                    //B가 터져서 안 되면 A로
                    if(A-team.K>=0) {
                        A-=team.K;
                        sum += (team.K * team.dA);
                        team.K=0;
                    }
                }
                else {
                    if(A-team.K>=0) {
                        A-=team.K;
                        sum += (team.K * team.dA);
                        team.K=0;
                    }
                    else {
                        sum+=(A*team.dA);
                        team.K-=A;
                        A=0;
                    }

                    if(B-team.K>=0) {
                        B-=team.K;
                        sum += (team.K * team.dB);
                        team.K=0;
                    }
                }
            }

            
            bw.write(sum + "\n");








        }
        bw.flush();bw.close();
    }
}

//3 20 60
//20 20 10
//20 10 30
//20 40 10
//0 0 0
