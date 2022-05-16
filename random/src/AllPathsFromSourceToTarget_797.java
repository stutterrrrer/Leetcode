import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AllPathsFromSourceToTarget_797 {
    private int[][] graph;
    private int target;
    private boolean[] isDeadEnd;
    private List<List<Integer>> paths;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        target = graph.length - 1;
        isDeadEnd = new boolean[target + 1];
        paths = new ArrayList<>(); // use ArrayList over LinkedList for space efficiency
        Deque<Integer> curPath = new LinkedList<>(); // Deque (LinkedList) for fast insert and removal
        dfs(0, curPath);
        return paths;
    }

    private void dfs(int vertex, Deque<Integer> curPath) {
        if (isDeadEnd[vertex]) return;
        curPath.addLast(vertex);
        if (vertex == target) paths.add(new ArrayList<>(curPath));
        else { // vertex is true if 1. has no neighbors; 2. all neighbors are dead ends
            isDeadEnd[vertex] = true;
            for (int neighbor : graph[vertex]) { // if no neighbor -> true
                dfs(neighbor, curPath);
                isDeadEnd[vertex] = isDeadEnd[vertex] && isDeadEnd[neighbor]; // if all neighbors are dead ends -> true
            }
        }
        curPath.removeLast();
    }

    public List<List<Integer>> allPathsSourceTargetNaive(int[][] graph) {
        this.graph = graph;
        target = graph.length - 1;
        paths = new ArrayList<>();
        Deque<Integer> curPath = new LinkedList<>();
        dfsNaive(0, curPath);
        return paths;
    }

    private void dfsNaive(int vertex, Deque<Integer> curPath) {
        curPath.addLast(vertex);
        if (vertex == target) paths.add(new ArrayList<>(curPath));
        else for (int target : graph[vertex]) dfsNaive(target, curPath);
        curPath.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        int[][] graphWithDeadEnds = {{1,2,6}, {3}, {1, 6}, {4, 5}, {}, {4}, {}};
        AllPathsFromSourceToTarget_797 solver = new AllPathsFromSourceToTarget_797();
        for (List<Integer> list : solver.allPathsSourceTarget(graphWithDeadEnds)) System.out.println(list);
    }
}
