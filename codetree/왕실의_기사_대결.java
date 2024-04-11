import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int r, c, maxR, maxC, health;

		public Node(int r, int c, int maxR, int maxC, int health) {
			this.r = r;
			this.c = c;
			this.maxR = maxR;
			this.maxC = maxC;
			this.health = health;
		}

		
	}
	
	
	static int[] dy = {-1,0,1, 0}; 
	static int[] dx = { 0,1,0,-1}; 
	public static boolean isValid(int y, int x) {
		return y>=0 && x>=0 && y<L && x<L;
	}
	public static int UP = 0;
	public static int RIGHT = 1;
	public static int DOWN = 2;
	public static int LEFT = 3;
	

	static Set<Integer> pushList = new HashSet<>();
	public static boolean isPushable(Node node, int d, int nowNum) {
		
		pushList.add(nowNum);
		if(d==UP) {
			int y = node.r + dy[d];
			for(int x = node.c;x<node.maxC;x++) {
				//밀 수 있는데 
				
				if(isValid(y,x)&&map[y][x]!=2) {
					//다른 게 있으면 
					if(now[y][x]!=0) {
						if(!isPushable(nodeMap.get(now[y][x]), d, now[y][x])) {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
		}
		else if(d==RIGHT) {
			int x = node.maxC-1 + dx[d];
			for(int y = node.r;y<node.maxR; y++) {
				if(isValid(y,x)&&map[y][x]!=2) {
					//다른 게 있으면 
					if(now[y][x]!=0) {
						if(!isPushable(nodeMap.get(now[y][x]), d, now[y][x])) {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
			
		}
		else if(d==DOWN) {
			int y = node.maxR -1 + dy[d];
			for(int x = node.c;x<node.maxC;x++) {
				//밀 수 있는데 
				if(isValid(y,x)&&map[y][x]!=2) {
					//다른 게 있으면 
					if(now[y][x]!=0) {
						if(!isPushable(nodeMap.get(now[y][x]), d, now[y][x])) {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
		}
		else {
			int x = node.c + dx[d];
			for(int y = node.r;y<node.maxR; y++) {
				if(isValid(y,x)&&map[y][x]!=2) {
					//다른 게 있으면 
					if(now[y][x]!=0) {
						if(!isPushable(nodeMap.get(now[y][x]), d, now[y][x])) {
							return false;
						}
					}
				}
				else {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	static void push(Node node, int d, int nowNum, boolean root) {
		for(int i=node.r;i<node.maxR;i++) {
			for(int j=node.c;j<node.maxC;j++) {
				if(now[i][j] == nowNum)
					now[i][j] = 0;
			}
		}
		
		node.r += dy[d];
		node.maxR += dy[d];
		node.c += dx[d];
		node.maxC += dx[d];
	
		for(int i=node.r;i<node.maxR;i++) {
			for(int j=node.c;j<node.maxC;j++) {
				now[i][j] = nowNum;
			}
		}
		
		if(!root) {
			for(int i=node.r;i<node.maxR;i++) {
				for(int j=node.c;j<node.maxC;j++) {
					if(map[i][j]==1) {
						--node.health;
					}
				}
			}
			
			if(node.health<=0) {
				nodeMap.remove(nowNum);
				for(int i=node.r;i<node.maxR;i++) {
					for(int j=node.c;j<node.maxC;j++) {
						now[i][j] = 0;
					}
				}
			}
		}
		
	
		
	}
	
	
	static int L,N,Q;
	
	static HashMap<Integer, Node> nodeMap = new HashMap<>();
	static int[][] map;
	static int[][] now;
	
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		int[] initialHealth = new int[N+1];
		map = new int[L][L];
		
		now = new int[L][L];
		
		for(int i = 0;i<L;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0;j<L;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i= 1;i<=N;i++) {
			st= new StringTokenizer(br.readLine());
			
			
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			initialHealth[i] = k;
			Node node = new Node(r,c,r+h,c+w,k);
			
			for(int y = r;y<r+h;y++) {
				for(int x = c;x<c+w;x++) {
					now[y][x] = i;
				}
			}
			nodeMap.put(i, node);
		}
		
		
		
		for(int i = 0;i<Q;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if(!nodeMap.containsKey(num)) {
				continue;
			}
			
			pushList = new HashSet<>();			
			if(isPushable(nodeMap.get(num), d, num)) {
				
				push(nodeMap.get(num), d,num, true);
				
				pushList.remove(num);
				for(Integer n : pushList) {
					if(!nodeMap.containsKey(n)) 
						continue;
					push(nodeMap.get(n), d, n, false);
					
				}
				
			
			}
		
		
		}
		
		if(nodeMap.isEmpty()) {
			System.out.println(0);
			return;
		}
		System.out.println(nodeMap.entrySet().stream()
		.mapToInt(entry -> initialHealth[entry.getKey()]-entry.getValue().health)
		.sum());
		

	}

}
