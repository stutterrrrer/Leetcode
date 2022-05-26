public class IntegerBreak_343 {
    public int integerBreak(int n) {
        int[] maxProd = new int[n + 1]; // maxProd[0] is not used - for ease of reading
        for (int i = 1; i <= n; i++) {
            maxProd[i] = i < n ? i : 0; // e.g. maxProd[1] = 1; maxProd[2] = 2 if n > 2
            for (int j = 1; j < i; j++)
                maxProd[i] = Math.max(maxProd[i],
                        j * maxProd[i - j]);
        }
        return maxProd[n];
    }
}
