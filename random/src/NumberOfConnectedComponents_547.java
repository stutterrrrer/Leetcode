import java.util.LinkedList;
import java.util.Queue;

public class NumberOfConnectedComponents_547 {
    private int[][] adjMatrix;
    private int vertices;

    public int findCircleNumDFS(int[][] isConnected) {
        adjMatrix = isConnected;
        vertices = adjMatrix.length;
        int count = 0;
        for (int v = 0; v < vertices; v++) {
            if (isConnected[v][v] == 0) continue;
            count++;
            dfs(v);
        }
        return count;
    }

    private void dfs(int v) {
        if (adjMatrix[v][v] == 0) return;
        adjMatrix[v][v] = 0;
        for (int w = 0; w < vertices; w++) if (adjMatrix[v][w] == 1) dfs(w);
    }

    public int findCircleNumBFS(int[][] isConnected) {
        int n = isConnected.length, count = 0;
        Queue<Integer> bfs = new LinkedList<>();
        for (int v = 0; v < n; v++) {
            if (isConnected[v][v] == 0) continue; // already visited.

            count++;
            bfs.offer(v);
            isConnected[v][v] = 0;

            while (!bfs.isEmpty()) {
                int cur = bfs.poll();
                for (int w = 0; w < n; w++)
                    if (isConnected[cur][w] == 1 && isConnected[w][w] == 1) {
                        bfs.offer(w);
                        isConnected[w][w] = 0;
                    }
            }
        }
        return count;
    }

}
