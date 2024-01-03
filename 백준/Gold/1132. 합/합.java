import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Alphabet {
        long value;
        String c;
        boolean flag = false;

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Alphabet{");
            sb.append("value=").append(value);
            sb.append(", c=").append(c);
            sb.append('}');
            return sb.toString();
        }

        public Alphabet(long value, String c) {
            this.value = value;
            this.c = c;
        }

    }
    static HashSet<String> cantZero;
    static long[] 배수 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = new String[N];
        //abcdefghi
        Alphabet[] alphabet = new Alphabet[10];
        for(int i= 0;i<10;i++) {
            alphabet[i] = new Alphabet(0,String.valueOf((char) (i + 'A')));
        }
        cantZero = new HashSet<>();
        for(int i= 0;i<N;i++) {

            str[i] = br.readLine();
            char[] array =str[i].toCharArray();
            cantZero.add(String.valueOf(array[0]));

            for (int c = 0; c < array.length; c++) {
                alphabet[array[c] - 'A'].value += 배수[array.length - c - 1];

            }
        }
        Arrays.sort(alphabet, (o1, o2) -> Long.compare(o2.value, o1.value));

        int val = 9;
        for (Alphabet alphabet1 : alphabet) {
            alphabet1.value = val--;
        }
        if (alphabet[9].value == 0 && cantZero.contains(alphabet[9].c)) {
            int index = 8;
            a:
            while (true) {
                Alphabet alphabet1 = alphabet[index];
                if (!cantZero.contains(alphabet1.c)) {
                    alphabet[index++].value = 0;

                    while (true) {
                        if (index > 9) {
                            break a;
                        }
                        alphabet[index++].value += 1;
                    }
                }
                index--;

            }
        }
        Arrays.sort(alphabet, (o1, o2) -> Long.compare(o2.value, o1.value));

        val = 9;
        for (Alphabet alphabet1 : alphabet) {
            for (int i = 0; i < str.length; i++) {
                str[i] = str[i].replaceAll(alphabet1.c, String.valueOf(val));
            }
            val--;
        }
        long sum = 0;
        for (String s : str) {
//            System.out.println(Long.parseLong(s));
            sum += Long.parseLong(s);
        }
        System.out.println(sum);




    }
}
