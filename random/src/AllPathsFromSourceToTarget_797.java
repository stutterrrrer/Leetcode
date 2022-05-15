import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourceToTarget_797 {
    private HashMap<Integer, List<Integer>> sourceVerticesTo;
    private int[][] graph;
    private int n;
    private List<List<Integer>> paths;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        n = graph.length;
        paths = new LinkedList<>();
        Deque<Integer> curPath = new LinkedList<>();
        dfs(0, curPath);
        return paths;
    }

    private void dfs(int vertex, Deque<Integer> curPath) {
        curPath.addLast(vertex);
        if (vertex == n - 1) paths.add(new LinkedList<>(curPath));
        else for (int target : graph[vertex]) dfs(target, curPath);
        curPath.removeLast();
    }

    public List<List<Integer>> allPathsSourceTargetExtra(int[][] graph) {
        this.graph = graph;
        int n = graph.length;
        boolean[] visited = new boolean[n];
        sourceVerticesTo = new HashMap<>();
        buildFromGraph(0, visited);
        paths = new LinkedList<>();
        Deque<Integer> curPath = new LinkedList<>();
        findPaths(n - 1, curPath);
        return paths;
    }

    private void findPaths(int vertex, Deque<Integer> curPath) {
        curPath.push(vertex); // should be first out
        if (vertex == 0) paths.add(new LinkedList<>(curPath));
        else for (int source : sourceVerticesTo.get(vertex)) findPaths(source, curPath);
        curPath.pop();
    }

    private void buildFromGraph(int source, boolean[] visited) {
        if (visited[source]) return;
        visited[source] = true;
        for (int target : graph[source]) {
            sourceVerticesTo.putIfAbsent(target, new LinkedList<>());
            sourceVerticesTo.get(target).add(source);
            buildFromGraph(target, visited);
        }
    }
}
