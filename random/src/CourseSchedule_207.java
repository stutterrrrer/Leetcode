import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CourseSchedule_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // build the digraph
        List<LinkedList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new LinkedList<>());
        for (int[] pair : prerequisites) graph.get(pair[1]).add(pair[0]);
        // traverse graph
        boolean[] visitedNoLoop = new boolean[numCourses];
        for (int vertex = 0; vertex < numCourses; vertex++) {
            if (!visitedNoLoop[vertex]) {
                boolean[] visitedCurPath = new boolean[numCourses];
                if (dfsToFindLoop(vertex, graph, visitedNoLoop, visitedCurPath)) return false;
            }
        }
        return true;
    }

    private boolean dfsToFindLoop(int vertex, List<LinkedList<Integer>> graph,
                                  boolean[] visitedNoLoop, boolean[] visitedCurPath) {
        // 2 base cases:
        if (visitedNoLoop[vertex]) return false; // already explored; not in loop
        if (visitedCurPath[vertex]) return true; // found loop
        // recursive case:
        visitedCurPath[vertex] = true;
        for (int adjVertex : graph.get(vertex))
            if (dfsToFindLoop(adjVertex, graph, visitedNoLoop, visitedCurPath)) return true; // found loop
        visitedCurPath[vertex] = false; // if none of the visited vertices are in a loop, backtrack
        visitedNoLoop[vertex] = true; // then also mark this vertex as not in a loop
        return false;
    }

    public static void main(String[] args) {
        int courses = 2;
        int constraints = 2;
        int[][] prerequisites = new int[constraints][2];
        prerequisites[0] = new int[]{1, 0};
        prerequisites[1] = new int[]{0, 1};
        CourseSchedule_207 solver = new CourseSchedule_207();
        System.out.print(solver.canFinish(courses, prerequisites));
    }
}
