import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = 1;
        while(true) {
            final char[] array = br.readLine().toCharArray();

            Stack<Character> stack = new Stack<>();

            int count =0 ;
            for(int i= 0;i<array.length;i++) {
                if(stack.isEmpty() && array[i]=='}') {
                    count++;
                    stack.add('{');
                }
                else if(!stack.isEmpty() && array[i] == '}') {
                    stack.pop();

                }

                if(array[i]=='{'){
                    stack.add('{');
                }

            }
            if(array[0]=='-' )
                break;

            count+=stack.size()/2;
            sb.append(testCase++).append(". ").append(count).append("\n");

        }
        //dfs로 들어가면서 안정적이면 그냥 탈출
        System.out.println(sb);
        // } }
        //{{{{{{}}  }}
    }
}
