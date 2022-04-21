public class MultiplyStrings_43 {
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) return "0";
		// num1 is the factor above, num2 below
		String prevSum = "";
		for (int i = num2.length() - 1; i >= 0; i--) {
			int num2Digit = num2.charAt(i) - '0';
			StringBuilder curProdReverse = new StringBuilder();
			int carry = 0;
			for (int j = num1.length() - 1; j >= 0; j--) {
				int num1Digit = num1.charAt(j) - '0';
				int prod = num1Digit * num2Digit + carry;
				curProdReverse.append(prod % 10);
				carry = prod / 10;
			}
			if (carry != 0) curProdReverse.append(carry);
			StringBuilder curProd = curProdReverse.reverse();
			for (int k = 0; k < num2.length() - 1 - i; k++) curProd.append(0);
			prevSum = sumStrs(prevSum, curProd.toString());
		}
		return prevSum;
	}

	private String sumStrs(String str1, String str2) {
		StringBuilder sumReverse = new StringBuilder();
		int carry = 0;
		for (int p1 = str1.length() - 1, p2 = str2.length() - 1; p1 >= 0 || p2 >= 0; p1--, p2--) {
			int x1 = p1 >= 0 ? str1.charAt(p1) - '0' : 0;
			int x2 = p2 >= 0 ? str2.charAt(p2) - '0' : 0;
			int curDigitSum = x1 + x2 + carry;
			sumReverse.append(curDigitSum % 10);
			carry = curDigitSum / 10;
		}
		if (carry != 0) sumReverse.append(carry);
		return sumReverse.reverse().toString();
	}

	public static void main(String[] args) {
		MultiplyStrings_43 solver = new MultiplyStrings_43();
		System.out.println(solver.multiply(args[0], args[1]));
	}
}
