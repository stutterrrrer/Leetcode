import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoinChange_322 {
    private int[] coins;
    private int min;
    private Set<Integer> deadEnd;

    public int coinChange(int[] coins, int amount) {
        int[] minCoinsFor = new int[amount + 1];
        Arrays.fill(minCoinsFor, 1, amount + 1, Integer.MAX_VALUE);
        for (int remain = 1; remain <= amount; remain++) {
            for (int coin : coins) {
                if (remain - coin >= 0)
                    minCoinsFor[remain] = Math.min(minCoinsFor[remain], minCoinsFor[remain - coin]);
            }
            minCoinsFor[remain] += minCoinsFor[remain] != Integer.MAX_VALUE ? 1 : 0;
        }
        return minCoinsFor[amount] <= amount ? minCoinsFor[amount] : -1;
    }

    public int coinChangeBacktrack(int[] coins, int amount) {
        this.coins = coins;
        int n = coins.length;
        min = amount + 1;
        deadEnd = new HashSet<>();
        Arrays.sort(coins);
        return makeUpRemain(amount, n - 1, 0) ? min : -1;
    }

    private boolean makeUpRemain(int remain, int maxCoinIndex, int count) {
        int maxCoin = coins[maxCoinIndex];
        if (remain == 0) {
            min = Math.min(min, count);
            return true;
        } else if (remain < 0) return false;
        else if (deadEnd.contains(remain)) return false;
        else if (count + remain / maxCoin >= min) return true; // no need to explore further
        boolean found = false;
        for (int next = maxCoinIndex; next >= 0; next--)
            found = makeUpRemain(remain - coins[next], next, count + 1) || found;
        if (!found) deadEnd.add(remain);
        return found;
    }

    public static void main(String[] args) {
        int[] coins = {3, 4, 5};
        int amount = 17;
        CoinChange_322 solver = new CoinChange_322();
        System.out.println(solver.coinChangeBacktrack(coins, amount));
    }
}
