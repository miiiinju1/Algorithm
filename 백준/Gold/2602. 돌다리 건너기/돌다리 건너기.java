
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String str = br.readLine();

        char[][] map = new char[2][0];
        map[0] = br.readLine().toCharArray();
        map[1] = br.readLine().toCharArray();

        long[][][] dp = new long[str.length()][2][map[0].length+1];

        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] == str.charAt(0)) {
                dp[0][0][i] = 1;
            }
            if (map[1][i] == str.charAt(0)) {
                dp[0][1][i] = 1;
            }
        }

        for (int i = 1; i < map[0].length; i++) {
            for (int j = 1; j < str.length(); j++) {
                if (map[1][i] == str.charAt(j)) {
                    dp[j][1][i] += dp[j - 1][0][i-1];
                }
                if (map[0][i] == str.charAt(j)) {
                    dp[j][0][i] += dp[j - 1][1][i-1];
                }
            }
            for (int j = 0; j < str.length(); j++) {
                dp[j][0][i] += dp[j][0][i-1];
                dp[j][1][i] += dp[j][1][i-1];
            }
        }

//        System.out.println(dp[str.length()-1][0][map[0].length]);
//        System.out.println(dp[str.length()-1][1][map[0].length]);
        System.out.println(dp[str.length()-1][0][map[0].length-1]+dp[str.length()-1][1][map[0].length-1]);


    }
}
//RRRRRRRRRR
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
//RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR