public class JumpGame_55 {
    public boolean canJumpConcise(int[] maxJumps) {
        int n = maxJumps.length;
        for (int i = 0, furthestReachable = 0; i <= furthestReachable; i++) {
            furthestReachable = Math.max(furthestReachable, i + maxJumps[i]);
            if (furthestReachable >= n - 1) return true;
        }
        return false;
    }

    public int jump(int[] maxJumps) {
        int n = maxJumps.length;
        int furthestPrevJump = -1, furthestCurJump = 0;
        for (int jumps = 0; furthestPrevJump < furthestCurJump; jumps++) {
            if (furthestCurJump >= n - 1) return jumps;
            int nextFurthest = 0;
            for (int i = furthestPrevJump + 1; i <= furthestCurJump; i++)
                nextFurthest = Math.max(nextFurthest, i + maxJumps[i]);
            furthestPrevJump = furthestCurJump;
            furthestCurJump = nextFurthest;
        }
        return -1;
    }

    public boolean canJumpVerbose(int[] maxJumps) {
        int n = maxJumps.length;
        int furthestPrevJump = -1, furthestCurJump = 0;
        while (furthestPrevJump < furthestCurJump) { // the #of iterations in this loop is the min # of jumps
            int nextFurthest = 0;
            for (int i = furthestPrevJump + 1; i <= furthestCurJump; i++)
                nextFurthest = Math.max(nextFurthest, i + maxJumps[i]);
            if (nextFurthest >= n - 1) return true;
            furthestPrevJump = furthestCurJump;
            furthestCurJump = nextFurthest;
        }
        return false;
    }
}
