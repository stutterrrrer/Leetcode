import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences_187 {
	public List<String> findRepeatedDnaSequences(String s) {
		Set<String> existingSequences = new HashSet<>();
		Set<String> repeatedSequences = new HashSet<>();
		for (int i = 0; i + 9 < s.length(); i++) {
			final String substring = s.substring(i, i + 10);
			if (existingSequences.contains(substring))
				repeatedSequences.add(substring);
			else existingSequences.add(substring);
		}
		return repeatedSequences.stream().toList();
	}

	public static void main(String[] args) {
		RepeatedDNASequences_187 solver = new RepeatedDNASequences_187();
		String str = "AAAAAAAAAAAAA";
		System.out.println(solver.findRepeatedDnaSequences(str));
	}
}
