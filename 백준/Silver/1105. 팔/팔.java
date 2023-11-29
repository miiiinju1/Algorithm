
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        String L = str[0];
        String R = str[1];

            int count = 0;

            if (R.length() <= L.length())
                {
                int length = Math.min(L.length(), R.length());
                for (int i = 1; i <= length; i++) {
                    int index = length - i;
                    if (L.charAt(index) == '8' && L.charAt(index) == R.charAt(index))
                        count++;
                    else if (R.charAt(index) > L.charAt(index))
                        count = 0;
                }
            }

        System.out.println(count);
        }
    }




