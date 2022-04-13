public class AddStrings_415 {
	public String addStrings(String num1, String num2) {
		// implement the plus operation of a calculator
		StringBuilder sumReverse = new StringBuilder();
		int carryForward = 0;
		while (!num1.isEmpty() && !num2.isEmpty()) {
			int l1 = num1.length(), l2 = num2.length();
			int lastDigitSum = num1.charAt(l1 - 1) - '0' + num2.charAt(l2 - 1) - '0' + carryForward;
			sumReverse.append(lastDigitSum % 10);
			carryForward = lastDigitSum / 10;
			num1 = num1.substring(0, l1 - 1);
			num2 = num2.substring(0, l2 - 1);
		}
		String remainingOfLongerStr = num1.isEmpty() ? num2 : num1;
		while (carryForward > 0 || !remainingOfLongerStr.isEmpty()) {
			if (carryForward == 0) { // so remainingOfLongerStr is not empty:
				StringBuilder remReverse = new StringBuilder(remainingOfLongerStr).reverse();
				sumReverse.append(remReverse);
				remainingOfLongerStr = remainingOfLongerStr.substring(0, 0);
			} else if (remainingOfLongerStr.isEmpty()) {// so carryForward > 0
				sumReverse.append(carryForward);
				carryForward /= 10;
			} else {
				int l = remainingOfLongerStr.length();
				int lastDigitSum = remainingOfLongerStr.charAt(l - 1) - '0' + carryForward;
				sumReverse.append(lastDigitSum % 10);
				carryForward = lastDigitSum / 10;
				remainingOfLongerStr = remainingOfLongerStr.substring(0, l - 1);
			}
		}

		return sumReverse.reverse().toString();
	}

	public String addStringsPadZero(String num1, String num2) {
		// implement the "plus" operation, with padded zeros.
		int carryForward = 0; // would be either 0 or 1 in summation.
		int pt1 = num1.length() - 1, pt2 = num2.length() - 1;
		StringBuilder sumReversed = new StringBuilder();
		for (; pt1 >= 0 || pt2 >= 0; pt1--, pt2--) {
			int x1 = pt1 >= 0 ? num1.charAt(pt1) - '0' : 0;
			int x2 = pt2 >= 0 ? num2.charAt(pt2) - '0' : 0;
			int curDigitSum = x1 + x2 + carryForward;
			sumReversed.append(curDigitSum % 10);
			carryForward = curDigitSum / 10;
		}
		if (carryForward != 0) sumReversed.append(carryForward);
		return sumReversed.reverse().toString();
	}

	public static void main(String[] args) {
		AddStrings_415 solver = new AddStrings_415();
		System.out.println(solver.addStringsPadZero(args[0], args[1]));
	}
}
