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
			if (!wordToPatChar.containsKey(curWord) ) {
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

	public static void main(String[] args) {
		WordPattern_290 solver = new WordPattern_290();
		System.out.println(solver.wordPattern(
				"abba",
				"dog dog dog dog"
		));
	}
}
