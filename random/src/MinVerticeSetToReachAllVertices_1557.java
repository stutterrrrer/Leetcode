import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinVerticeSetToReachAllVertices_1557 {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        // this problem is simply to find all vertices with 0 in-degrees.
        // since it's stated the input is acyclic, there must exist at least one such vertex
        Set<Integer> zeroInDegree = new HashSet<>();
        for (int i = 0; i < n; i++) zeroInDegree.add(i);
        for (List<Integer> edge : edges) zeroInDegree.remove(edge.get(1));
        return zeroInDegree.stream().toList();
    }
}
