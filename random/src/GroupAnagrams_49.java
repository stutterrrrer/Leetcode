import java.util.*;

public class GroupAnagrams_49 {

    public List<List<String>> groupAnagramsSort(String[] strs) {
        // converge anagrams into the same string: by sorting a string
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortedStr = new String(arr);
            anagrams.putIfAbsent(sortedStr, new LinkedList<>());
            anagrams.get(sortedStr).add(str);
        }
        return new LinkedList<>(anagrams.values());
    }

    public List<List<String>> groupAnagramsCount(String[] strs) {
        // converge anagrams into the same string: by counting frequencies of 26 letters
        // then merging the frequencies into 1 string separated with "#" for each letter, use it as hash key.
        // foregoes the sorting which takes longer
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            int[] letterFrequencies = new int[26];
            for (int i = 0; i < str.length(); i++) letterFrequencies[str.charAt(i) - 'a']++;
            StringBuilder hashKeyBuilder = new StringBuilder();
            for (int i : letterFrequencies) hashKeyBuilder.append(i + "#");
            final String key = hashKeyBuilder.toString();
            anagrams.putIfAbsent(key, new LinkedList<>());
            anagrams.get(key).add(str);
        }
        return new LinkedList<>(anagrams.values());
    }

    public List<List<String>> groupAnagramsRadixSort(String[] strs) {
        // radix sort: improves running time at cost of space usage
        // radix = 26 (lower case only)
        // radix sorting each word: O(26 + W) = O(W) time, O(26 + W) = O(W) space (the sorted str);
        // in total: O(total letters) time, O(W) space (space reused for each word, not accumulated)
        // Map< Sorted String, List<String> >
        HashMap<String, List<String>> anagrams = new HashMap<>();
        for (String origStr : strs){
            int[] letterCount = new int[26];
            char[] charArr = origStr.toCharArray(); // will be overridden and become sorted char array
            for (char curChar : charArr) letterCount[curChar - 'a']++; // count letters, put into radix buckets
            for (int letter = 0, strIndex = 0; letter < letterCount.length; letter++) { // combine into sorted string
                int count = letterCount[letter];
                char curChar = (char)(letter + 'a');
                for (int i = 0; i < count; i++, strIndex++)
                    charArr[strIndex] = curChar;
            }
            String sortedStr = new String(charArr);
            // insert into map
            if (!anagrams.containsKey(sortedStr)) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(origStr);
                anagrams.put(sortedStr, newGroup);
            } else {
                anagrams.get(sortedStr).add(origStr);
            }
        }
        return new ArrayList<>(anagrams.values());
    }

    public static void main(String[] args) {
        String[] strs = {"ray", "cod", "abe", "ned", "arc", "jar", "owl", "pop", "paw", "sky", "yup", "fed", "jul",
                "woo", "ado", "why", "ben", "mys", "den", "dem", "fat", "you", "eon", "sui", "oct", "asp", "ago",
                "lea", "sow", "hus", "fee", "yup", "eve", "red", "flo", "ids", "tic", "pup", "hag", "ito", "zoo"};
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams_49 solver = new GroupAnagrams_49();
        System.out.println(solver.groupAnagramsSort(strs));
    }
}

