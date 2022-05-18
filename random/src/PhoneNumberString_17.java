import java.util.ArrayList;
import java.util.List;

public class PhoneNumberString_17 {
    private String digits;
    private List<String> combos;

    public List<String> letterCombinations(String digits) {
        this.digits = digits;
        combos = new ArrayList<>();
        char[] curStr = new char[digits.length()];
        if (digits.length() != 0) mapDigitsStarting(0, curStr);
        return combos;
    }

    private void mapDigitsStarting(int bgn, char[] curStr) {
        if (bgn == digits.length()) combos.add(new String(curStr));
        else {
            char digit = digits.charAt(bgn);
            char letter = (char) ((digit - '2') * 3 + 'a' + (digit == '8' || digit == '9' ? 1 : 0));
            int count = digit == '7' || digit == '9' ? 4 : 3;
            for (int i = 0; i < count; i++) {
                curStr[bgn] = letter++;
                mapDigitsStarting(bgn + 1, curStr);
            }
        }
    }
}
