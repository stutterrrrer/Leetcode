public class BitwiseANDNumbersRange_201 {
    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        while (left < right) { // find common prefix
            left >>= 1;
            right >>= 1;
            ++shift;
        }
        return left << shift; // append the 0s back to the suffix
    }
}
