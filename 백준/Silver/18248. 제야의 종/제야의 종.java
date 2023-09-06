
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Person implements Comparable<Person>{

        String ary;



        public Person(String ary) {
            this.ary = ary.trim();
        }

        @Override
        public int compareTo(Person person) {
            if (ary.compareTo(person.ary)>0) {
                return -1;
            }
            else if(ary.equals(person.ary))
                return 0;
            else
                return 1;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Person> person = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine().replaceAll(" ", "");
            person.add(new Person(str));
        }
        Collections.sort(person);
        for (int i = 0; i < N-1; i++) {
            Person person1 = person.get(i);
            Person person2 = person.get(i + 1);
            for(int j =0;j<M;j++) {
                if (person1.ary.charAt(j)<person2.ary.charAt(j))
                {
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}
