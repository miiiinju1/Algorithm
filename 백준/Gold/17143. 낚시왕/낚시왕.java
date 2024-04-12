import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	
	static class Shark {
		int r, c, s, d, z;

		@Override
		public int hashCode() {
			return Objects.hash(c, r);
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Shark other = (Shark) obj;
			return c == other.c && r == other.r;
		}

	

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r; 
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}
	
	
	//0,1 위아래
	//2,3 왼오
	
	
	//->0 2 위아래
	// 1 3 왼오 
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	static void move(Shark shark) {
		
		if(shark.d%2==0) {
			//위아래 
			
			int distance = shark.s%((R-1)*2);
			
			for(int i= 0;i<distance;i++) {
				
				shark.r = dy[shark.d] + shark.r;
				shark.c = dx[shark.d] + shark.c;
				
				if(!(shark.r>=0&&shark.r<R)) {
					shark.d= (shark.d+2)%4;
					shark.r = dy[shark.d]*2 + shark.r;
					shark.c = dx[shark.d]*2 + shark.c;
				}
			}
			
		}
		else {
			int distance = shark.s%((C-1)*2);
			for(int i= 0;i<distance;i++) {
				shark.r = dy[shark.d] + shark.r;
				shark.c = dx[shark.d] + shark.c;
				
				if(!(shark.c>=0&&shark.c<C)) {
					shark.d= (shark.d+2)%4;
					shark.r = dy[shark.d]*2 + shark.r;
					shark.c = dx[shark.d]*2 + shark.c;
				}
			}
			
			
		}
		
		
	}

	static int R,C;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		 R = Integer.parseInt(st.nextToken());
		 C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		Set<Shark> sharks = new HashSet<>();
		for(int i= 0;i<M;i++) {
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
	
			
			//위 
			if(d==1) {
				d =0;
			}
			//아래 
			else if(d==2) {
				d = 2;
			}
			//왼 
			else if(d==3) {
				d = 1;
			}
			//오 
			else {
				d = 3;
			}
			Shark shark = new Shark(r,c,s,d,z);
			sharks.add(shark);
			
		}
//		
//		Shark te = new Shark(0,0,8,0,0);
//		move(te);
////		System.out.println(te);
		
		int[] fishers = new int[1];
		int sum = 0;
		fishers[0] = 0;
		for(;fishers[0]<C;fishers[0]++) {
			
			//낚시
			Shark fished = sharks.stream()
				.filter(s -> s.c == fishers[0])
				.min((o1,o2) -> Integer.compare(o1.r,o2.r)).orElse(null);
			if(fished!=null) {
				sum+= fished.z;
				sharks.remove(fished);
				
			}
			
			
			
			//상어 움직이고 
			for(Shark s : sharks) {
				move(s);
			}
			
			
			
			//겹치는 거 잡아먹기 
			Map<String, List<Shark>> temp = sharks.stream()
			.collect(Collectors.groupingBy(s -> s.r+" "+s.c));
			
			Queue<Shark> newShark = new ArrayDeque<>();
			
			for(Map.Entry<String, List<Shark>> entry : temp.entrySet()) {
				
				if(entry.getValue().size()==1) {
					newShark.addAll(entry.getValue());
				}
				
				else {
					newShark.add(entry.getValue()
							.stream()
							.min((o1, o2) -> Integer.compare(o2.z, o1.z))
							.get()
							);
					
				}
				
				
			}
			
			

			sharks = new HashSet<>(newShark);
			
			
			
		}
//		System.out.println(sharks);
		System.out.println(sum);
		
		
		
		
	}

}
