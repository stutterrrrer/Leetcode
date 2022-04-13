public class AddStrings {
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
			}
			else {
				int l = remainingOfLongerStr.length();
				int lastDigitSum = remainingOfLongerStr.charAt(l - 1) - '0' + carryForward;
				sumReverse.append(lastDigitSum % 10);
				carryForward = lastDigitSum / 10;
				remainingOfLongerStr = remainingOfLongerStr.substring(0, l - 1);
			}
		}

		return sumReverse.reverse().toString();
	}

	public static void main(String[] args) {
		AddStrings solver = new AddStrings();
		System.out.println(solver.addStrings("1", "9"));
	}
}
