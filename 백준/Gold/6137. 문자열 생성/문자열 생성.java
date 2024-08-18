
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

  static char[] chars;
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    N = Integer.parseInt(br.readLine());

    chars = new char[N];
    for(int i= 0;i<N;i++) {
      chars[i] = br.readLine().charAt(0);
    }

    List<Character> result = new ArrayList<>();

    int lo = 0, hi = N - 1;

    while (true) {

      if(chars[lo] > chars[hi]) {
        result.add(chars[hi]);
        hi--;
      } else if (chars[lo] < chars[hi]) {
        result.add(chars[lo]);
        lo++;
      } else {
        // 둘 다 같으면 다음 depth로
        boolean res = search(lo, hi);
        if(res) {
          // hi를 줄이기
          result.add(chars[hi]);
          hi--;
        } else {
          result.add(chars[lo]);
          lo++;
        }
      }
      if(lo >= hi) {
        break;
      }
    }
    if (lo == hi) {
      result.add(chars[lo]);
    }
//    System.out.println("lo+\" \"+hi = " + lo+" "+hi);
    bw.write(result.get(0)+"");
    for(int i = 1;i<result.size();i++) {
      if(i%80==0) {
        bw.write("\n");
      }
      bw.write(result.get(i) + "");
    }
    bw.flush();bw.close();

  }

  static boolean search(int lo, int hi) {
    while(true) {
      lo++;
      hi--;
      if (lo >= hi) {
        return true;
      }

      if(chars[lo] > chars[hi]) {
        return true;
      } else if (chars[lo] < chars[hi]) {
        return false;
      }
    }
  }

}
