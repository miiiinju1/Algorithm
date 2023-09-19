import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;



public class Main {
    static int[] tempMap = new int[26];
    static int[] map = new int[26];
    public static boolean equals()
    {
        for(int i =0;i<26;i++)
        {
            if(!(map[i]==tempMap[i]))
                return false;
        }
        System.out.println("YES");
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String encrypted = null;
        String str = null;
        for (int t = 0; t < T; t++) {
            for(int i= 0;i<26;i++)
            {
                tempMap[i]=0;
                map[i] = 0;
            }
            encrypted = br.readLine();
            str = br.readLine();
            int strLen = str.length();
            int encLen = encrypted.length();
            int window = encLen - strLen;
            for(int i =0;i<strLen;i++)
            {
                map[str.charAt(i)-'a']+=1;
                tempMap[encrypted.charAt(i)-'a']+=1;
            }

            boolean fail = true;
            for(int i =0;i<=window;i++)
            {
                if(equals())
                {
                    fail =false;
                    break;
                }
                if(window==i)
                    break;
                tempMap[encrypted.charAt(i)-'a']-=1;
                tempMap[encrypted.charAt(i+strLen)-'a']+=1;
            }
            if(fail)
                System.out.println("NO");

        }
    }
}
