import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static int[][] rotate(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][] result = new int[col][row];
		for(int i= 0;i<row;i++) {
			for(int j= 0;j<col;j++) {
				result[j][row-1-i] = matrix[i][j];
				
			}
		}
			
	
		return result;
	}
	
	public static int[][] partitalRotate(int[][] matrix, 
										int startY, 
										int startX, 
										int size) {
		
		int row = matrix.length;
		int col = matrix[0].length;
		
		int[][] result = new int[row][col];
		
		for(int i= 0;i<row;i++) {
			for(int j= 0;j<col;j++) {
				result[i][j] = matrix[i][j];
			}
		}
		
		for(int i= startY;i<startY+size;i++) {
			for(int j= startX;j<startX+size;j++) {
				result[j][col-1-i] = matrix[i][j];
			}
		}
		
		
		
		
		
		
		return result;
	}
	

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		
				
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] res = rotateResult(map,R);
		
		for(int i= 0;i<res.length;i++) {
			for(int j= 0;j<res[0].length;j++) {
				bw.write(res[i][j]+" ");
			}
			bw.write("\n");
		}
		
		
		bw.flush();
		bw.close();
		
		
		
	
	}
	
	public static int[][] rotateResult(int[][] matrix, int R) {
		int row = matrix.length;
		int col = matrix[0].length;
		
	
		int[][] result;
		result = new int[row][col];
		for(int i= 0;i<row;i++) {
			for(int j= 0;j<col;j++) {
				result[i][j] = matrix[i][j];
			}
		}
		
		
		int d=0;
		while(true) {
			int t = (((row-(2*d))+(col-(2*d)))*2-4);
			if(t<=0) 
				break;
			int r = R%t;
			
			while(r>0) {
				int i=d,j=d;
				int temp = result[i][j];
				for(;j<col-1-d;j++) {
					result[i][j] = result[i][j+1];
				}
				
				for(;i<row-1-d;i++) {
					result[i][j] = result[i+1][j];
				}
				
				for(;j>d;j--) {
					result[i][j] = result[i][j-1];
				}
				
				for(;i>d;i--) {
					result[i][j] = result[i-1][j];
				}
				result[++i][j] = temp;
				
				
				--r;
			}
			++d;
			if(d>=row/2 || d>=col/2) {
				break;
			}
		}
		
		
		return result;
		
		
		
	}
	


}


