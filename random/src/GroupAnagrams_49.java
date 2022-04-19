import javax.swing.*;
import java.util.*;

public class GroupAnagrams_49 {
	public List<List<String>> groupAnagrams(String[] strs) {
		HashMap<Integer, List<Integer>> charSetToAnagGroupIndex = new HashMap<>();
		ArrayList<List<String>> anagramGroups = new ArrayList<>();

		for (String str : strs) {
			int n = str.length(), sum = 0;
			for (int i = 0; i < n; i++) sum += str.charAt(i);
			int hashCode = sum * n;

			if (charSetToAnagGroupIndex.containsKey(hashCode)) {
				List<Integer> groupIndices = charSetToAnagGroupIndex.get(hashCode);
				int m = groupIndices.size();
				boolean belongsInExistingGroup = false;
				for (int i = 0; i < m; i++) {
					List<String> anagGroup = anagramGroups.get(groupIndices.get(i));
					if (areAnagrams(anagGroup.get(0), str)) {
						anagGroup.add(str);
						belongsInExistingGroup = true;
						break;
					}
				}
				if (!belongsInExistingGroup) {
					anagramGroups.add(new LinkedList<>(Collections.singleton(str)));
					groupIndices.add(anagramGroups.size() - 1);
				}
			} else {
				anagramGroups.add(new LinkedList<>(Collections.singleton(str)));
				charSetToAnagGroupIndex.put(hashCode, new LinkedList<>(Collections.singleton(anagramGroups.size() - 1)));
			}
		}

		return anagramGroups;
	}

	private boolean areAnagrams(String s1, String s2) {
		int n = s1.length();
		if (s2.length() != n) return false;
		HashMap<Character, Integer> charCounts = new HashMap<>();
		for (int i = 0; i < n; i++) {
			char c = s1.charAt(i);
			charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
		}
		for (int i = 0; i < n; i++) {
			Integer count = charCounts.get(s2.charAt(i));
			if (count == null || count == 0) return false;
			else charCounts.put(s2.charAt(i), count - 1);
		}
		return true;
	}

	public static void main(String[] args) {
		String[] strs = {"ray", "cod", "abe", "ned", "arc", "jar", "owl", "pop", "paw", "sky", "yup", "fed", "jul",
				"woo", "ado", "why", "ben", "mys", "den", "dem", "fat", "you", "eon", "sui", "oct", "asp", "ago",
				"lea", "sow", "hus", "fee", "yup", "eve", "red", "flo", "ids", "tic", "pup", "hag", "ito", "zoo"};
		String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
		GroupAnagrams_49 solver = new GroupAnagrams_49();
		System.out.println(solver.groupAnagrams(strs1));
	}
}

