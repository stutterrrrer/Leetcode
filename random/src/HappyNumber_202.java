import java.util.HashSet;
import java.util.Set;

public class HappyNumber_202 {

    public boolean isHappy(int n) {
        int hare = n, tortoise = n;
        do {
            tortoise = next(tortoise);
            hare = next(next(hare));
        } while (hare != 1 && hare != tortoise);
        return hare == 1;
    }

    private int next (int num) { // if num == 1, next == 1
        int next = 0;
        while (num != 0) {
            int curDigit = num % 10;
            next += curDigit * curDigit;
            num /= 10;
        }
        return next;
    }

    public boolean isHappyHashSet(int n) {
        int curNum = n;
        Set<Integer> visited = new HashSet<>();
        while (curNum != 1 && !visited.contains(curNum)) {
            visited.add(curNum);
            String num = Integer.toString(curNum);
            curNum = 0;
            for (int i = 0; i < num.length(); i++) {
                int digit = num.charAt(i) - '0';
                curNum += digit * digit;
            }
        }
        return curNum == 1;
    }
}
