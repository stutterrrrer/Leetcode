import java.util.HashSet;
import java.util.Set;

public class HappyNumber_202 {
    public boolean isHappy(int n) {
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
