import java.util.HashMap;
import java.util.HashSet;

public class WordPattern_290 {
	public boolean wordPattern(String pattern, String s) {
		HashMap<String, Character> wordToPatChar = new HashMap<>();
		HashSet<Character> occupiedPatChars = new HashSet<>();
		String[] words = s.split(" ");
		if (words.length != pattern.length()) return false;

		for (int i = 0; i < words.length; i++) {
			String curWord = words[i];
			char curPatChar = pattern.charAt(i);
			if (!wordToPatChar.containsKey(curWord)) {
				if (!occupiedPatChars.contains(curPatChar)) {
					wordToPatChar.put(curWord, curPatChar);
					occupiedPatChars.add(curPatChar);
				} else return false; // this pattern char is already mapped to another word.
			}
			// if this word is already mapped to another pattern char
			else if (wordToPatChar.get(curWord) != curPatChar) return false;
		}
		return true;
	}

	public boolean wordPatternOneMap(String pattern, String s) {
		String[] words = s.split(" ");
		if (words.length != pattern.length()) return false;

		// this map stores the index of the first occurrences of both the word and the pattern chars
		// until the first mismatch, there will be two entries for each index,
		// one key is the word, the other key is its matching pattern char
		// use raw type Hash Map to distinguish a 'a' char in pattern and a "a" string in words[].
		HashMap firstOccurrenceIndices = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			final char curChar = pattern.charAt(i);
			final String  curWord = words[i];
			firstOccurrenceIndices.putIfAbsent(curChar, i);
			firstOccurrenceIndices.putIfAbsent(curWord, i);
			if (!firstOccurrenceIndices.get(curWord).equals(firstOccurrenceIndices.get(curChar))) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		WordPattern_290 solver = new WordPattern_290();
		System.out.println(solver.wordPatternOneMap(
				"abba",
				"dog cat cat dog"
		));
	}
}
