
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static class Student implements Comparable<Student> {
		int h, k;

		public Student(int h, int k) {
			this.h = h;
			this.k = k;
		}

		@Override
		public int compareTo(Student o) {
			return Integer.compare(o.h, this.h);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Student> students = new ArrayList<>(N);

		for(int i= 0;i<N;i++) {
			var st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			students.add(new Student(h, k));
		}
		Collections.sort(students);

		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			Student student = students.get(i);

			Map.Entry<Integer, Integer> entry = map.lowerEntry(student.k);
			if(entry == null) {
				map.put(1, map.getOrDefault(1, 0) + 1);
			} else {
				if(entry.getValue()==1) {
					map.remove(entry.getKey());
				} else {
					map.replace(entry.getKey(), entry.getValue() - 1);
				}
				map.put(entry.getKey() + 1, map.getOrDefault(entry.getKey() + 1, 0) + 1);

			}
			// System.out.println("map = " + map);
		}
		
		System.out.println(map.values().stream()
			.mapToInt(Integer::intValue)
			.sum());

	}
}
