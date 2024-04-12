import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Santa {
		
		int num, y, x;
		int score = 0;
		int stunTill = -1;
		
		boolean isAlive = true;
		
		

		@Override
		public String toString() {
			return "Santa [num=" + num + ", y=" + y + ", x=" + x + ", score=" + score + ", stunTill=" + stunTill
					+ ", isAlive=" + isAlive + "]";
		}



		public Santa(int num, int y, int x) {
			this.num = num;
			this.y = y;
			this.x = x;
		}
		
	}
	
	static class Rudolf {
		public Rudolf(int y, int x) {
			this.y = y;
			this.x = x;
		}

		int y, x;
	}

	
	static int rudolfMove(Rudolf rudolf) {
		
		int min = Integer.MAX_VALUE;
		Santa minSanta = null;
		
		
		for(Santa santa: santas) {
			// 산타 살아있는 것만 검사 
			if(!santa.isAlive)
				continue;
			
			int distance = distance(rudolf.y,rudolf.x,santa.y,santa.x);
			if(distance<min) {
				min = distance;
				minSanta = santa;
			}
			//거리가 같은 경우 
			else if(distance==min) {
				
				//y 좌표가 더 큰 산타로 교체 
				if(minSanta.y<santa.y) {
					minSanta = santa;
					
				}
				//y좌표 같은 경우 
				else if(minSanta.y==santa.y) {
					//x좌표가 큰 걸로 교체  
					if(minSanta.x<santa.x) {
						minSanta = santa;
					}
				}
				
			}
			
			
		}
		
//		System.out.println("rudolfMove"+ minSanta);
		
		int minDirection = -1;
		int minValue = Integer.MAX_VALUE;
		
		for(int i= 0;i<8;i++) {
			int y = rudolfDY[i] + rudolf.y;
			int x = rudolfDX[i] + rudolf.x;
			
			// 좌표 밖으로는 못 가 뭐 갈 일 없겠지만 
			if(!(y>=0&&x>=0&&y<N&&x<N))
				continue;
			
			int distance = distance(y,x,minSanta.y,minSanta.x);
			if(minValue>distance) {
				
				minValue = distance;
				minDirection = i;
				
			}
		}
		
		return minDirection;
		
		
		
	}
	//산타용 
	static int minDirection(int fromY, int fromX, int rudolfY, int rudolfX) {
		
		int minDirection = -1;
		
		// 움직일 수 있는 칸이 있더라도 만약 루돌프로부터 가까워질 수 있는 방법이 없다면 산타는 움직이지 않습니다.
		int minValue = distance(fromY, fromX, rudolfY, rudolfX);
//		System.out.println(minValue);
		d: for(int i= 0;i<4;i++) {
			int y = santaDY[i] + fromY;
			int x = santaDX[i] + fromX;
			
			//격자판 밖으로 나가면 움직이지 않음 
			if(!(y>=0&&x>=0&&y<N&&x<N))
				continue;
			
			
			for(Santa santa : santas) {
				if(!santa.isAlive)
					continue;
				
				//이미 산타가 있는 자리로는 못 감
				if(santa.y==y && santa.x==x) {
					continue d;
				}
			}
			
			int distance = distance(y,x,rudolfY,rudolfX);
//			System.out.println("candidate"+distance);
			//상우하좌 우선순위대로 일단 바뀌면 같아도 안 변하게 
			if(minValue>distance) {
				minValue = distance;
				minDirection = i;
			}
		}
//		System.out.println(fromY+" "+fromX+":"+minDirection+" "+minValue);
		
		return minDirection;
	}
	
	//3 산타의 움직임 
	static void santaMove(Rudolf rudolf, int nowTurn) {
		

		for(Santa santa: santas) {
			// 산타 살아있는 것만 검사 
			if(!santa.isAlive)
				continue;
			
			// stunTill (nowTurn이 stunTill까지 못 움직임 
			if(santa.stunTill>=nowTurn) {
				continue;
			}
			
			
			int minDirection = minDirection(santa.y, santa.x, rudolf.y, rudolf.x);
			
			if(minDirection==-1) {
				continue;
			}
			
			santa.y += santaDY[minDirection];
			santa.x += santaDX[minDirection];
			
//			System.out.println("santaMove"+santa+"minDirection:"+minDirection);
			// 산타로 인해 만약 충돌이 발생하면 
			
			if(santa.y==rudolf.y && santa.x==rudolf.x) {
				collisionBySanta(rudolf, santa, minDirection, nowTurn);
			}
			
//			System.out.println(santa);
		}
		
	}
	
	
	static boolean isValid(int y, int x) {
		return y>=0&&x>=0&&y<N&&x<N;
	}
	
	// 4 충돌  산타 -> 루돌프
	static void collisionBySanta(Rudolf rudolf, Santa santa, int santaDirection, int nowTurn) {
		
		santa.score+=D;
		
		// santa가 이동한 자리에서 D만큼 물러나기
		
		
		int reverseDirection = (santaDirection+2)%4;
		
		int y = santa.y+santaDY[reverseDirection]*D;
		int x = santa.x+santaDX[reverseDirection]*D;
		
		if(!isValid(y,x)) {
			santa.isAlive = false;
			return ;
		}
		
		
		santa.stunTill = nowTurn+1;
		
		
		// 상호작용 일어나는지 검사 
		for(Santa s : santas) {
			
			if(!s.isAlive) continue;
			
			
			if(s.y == y && s.x == x) {
				interaction(santa, s, -1, reverseDirection);
				break;
			}
			
		}
		
		santa.y = y;
		santa.x = x;
		
	}
	// 4 충돌  루돌프  -> 산타
	static void collisionByRudolf(Rudolf rudolf, Santa santa, int rudolfDirection, int nowTurn) {
		
		santa.score+=C;
		int y = santa.y+rudolfDY[rudolfDirection]*C;
		int x = santa.x+rudolfDX[rudolfDirection]*C;
		
		if(!isValid(y,x)) {
			santa.isAlive = false;
			return ;
		}
		
		
		santa.stunTill = nowTurn+1;
		for(Santa s : santas) {
			
			if(!s.isAlive) continue;
			
			if(s.y == y && s.x == x) {
				
				
				interaction(santa, s, rudolfDirection,-1);
				break;
			}
			
		}
		santa.y = y;
		santa.x = x;
	}
	
	// 5. 상호작용 
	static void interaction(Santa from, Santa to, int rudolfDirection, int santaDirection) {
		
		//루돌프가 민 경우 
		if(rudolfDirection!=-1) {
			int y = to.y+rudolfDY[rudolfDirection];
			int x = to.x+rudolfDX[rudolfDirection];
			
			
			if(!isValid(y,x)) {
				to.isAlive = false;
				return ;
			}
			
			for(Santa s: santas) {
				if(!s.isAlive) continue;
				if(y == s.y && x == s.x) {
					interaction(to, s, rudolfDirection,-1);
					break;
				}
			}
			to.y = y;
			to.x = x;
		}
		
		//산타가 민 경우 
		else {
			
			int y = to.y+santaDY[santaDirection];
			int x = to.x+santaDX[santaDirection];
			
//			System.out.println(from+"interaction:"+y+" "+x);
			if(!isValid(y,x)) {
				to.isAlive = false;
				return ;
			}
			
			
			for(Santa s: santas) {
				if(!s.isAlive) continue;
				
				if(y == s.y && x == s.x) {
					interaction(to, s, -1,santaDirection);
					break;
				}
			}
			to.y = y;
			to.x = x;
			
			
		}
	}
	
	
	
	
	static int distance(int y, int x, int y2, int x2) {
		return (y-y2)*(y-y2)+(x-x2)*(x-x2);
	}
	static int[] rudolfDY = {-1,-1,-1,0,1,1,1,0};
	static int[] rudolfDX = {-1,0,1,1,1,0,-1,-1};
	static int[] santaDY = {-1,0,1,0};
	static int[] santaDX = {0,1,0,-1};
	
	static int N,M,P,C,D;
	static List<Santa> santas;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 P = Integer.parseInt(st.nextToken());
		 C = Integer.parseInt(st.nextToken());
		 D = Integer.parseInt(st.nextToken());
		
		
		

		st = new StringTokenizer(br.readLine());
		
		int ruY = Integer.parseInt(st.nextToken())-1;
		int ruX = Integer.parseInt(st.nextToken())-1;
		
		Rudolf rudolf = new Rudolf(ruY, ruX);
		
		santas = new ArrayList<>();
		for(int i = 0;i<P;i++) {
			st = new StringTokenizer(br.readLine());
			int santaNum = Integer.parseInt(st.nextToken());
			int santaY = Integer.parseInt(st.nextToken())-1;
		
			int santaX = Integer.parseInt(st.nextToken())-1;
			
			santas.add(new Santa(santaNum, santaY, santaX));
			
			
		}
		
		// 산타 1번부터 차례대로 움직이게 정렬하기 
		Collections.sort(santas, (o1, o2) -> Integer.compare(o1.num,o2.num));
		
//	
//		System.out.println(santas);
//		santaMove(rudolf,0);
//		System.out.println(santas);
//		
		for(int turn = 1;turn<=M;turn++) {
			
			int rudolfDirection = rudolfMove(rudolf);
			rudolf.y+=rudolfDY[rudolfDirection];
			rudolf.x+=rudolfDX[rudolfDirection];
			
			
			// 루돌프에 의한 충돌 검사 
			for(Santa santa: santas) {
				if(santa.isAlive&&santa.y==rudolf.y && santa.x == rudolf.x) {
					collisionByRudolf(rudolf, santa, rudolfDirection, -1);
					santa.stunTill=turn+1;
				}
			}
			
			
			santaMove(rudolf, turn);
			
			int aliveCount = 0;
			for(Santa santa: santas) {
				if(santa.isAlive) 
					aliveCount++;
			}
			
			if(aliveCount==0) {
//				System.out.println("isDone");
				break;
			}
			
			for(Santa santa: santas) {
				if(santa.isAlive) 
					++santa.score;
			}
			
//			System.out.println();
//			System.out.println("[TURN "+turn+"]"+santas);
//			System.out.println("Rudolf : "+rudolf.y+" "+rudolf.x);
			
			
//			System.out.println();
			
		}
		StringBuilder sb = new StringBuilder();
		
		for(Santa santa: santas) {
			sb.append(santa.score).append(" ");
		}
		System.out.println(sb);
		
		
	}

}
