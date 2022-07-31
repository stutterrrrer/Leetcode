import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline_218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> contour = new ArrayList<>();
        int[] cur = {0, Integer.MAX_VALUE, 0}; // floor
        int n = buildings.length;
        // at start of each iteration, all heap elements will have i[0] < cur[1]
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(i -> -i[2]));

        for (int nextI = 0; nextI < n; ) {
            int[] next = buildings[nextI];
            if (next[0] < cur[1]) { // if there's overlap
                nextI = addToHeap(buildings, n, heap, nextI, next);
                while (heap.peek()[1] <= cur[0]) heap.remove();
                if (heap.peek()[2] > cur[2]) { // if the tallest overlapping building is taller than cur
                    heap.add(cur);
                    cur = heap.remove();
                    contour.add(List.of(cur[0], cur[2]));
                }
            } else {
                if (next[0] == cur[1]) // touching
                    nextI = addToHeap(buildings, n, heap, nextI, next);
                while (heap.peek()[1] <= cur[1]) heap.remove();
                next = heap.remove();
                if (next[2] != cur[2]) contour.add(List.of(cur[1], next[2]));
                cur = next;
            }
        } // after all buildings have been examined
        while (heap.size() > 1) {
            int[] next = heap.remove();
            if (next[1] > cur[1]) {
                if (next[2] != cur[2]) contour.add(List.of(cur[1], next[2]));
                cur = next;
            }
        }
        contour.add(List.of(cur[1], 0));
        return contour;
    }

    private int addToHeap(int[][] buildings, int n, PriorityQueue<int[]> heap, int nextI, int[] next) {
        heap.add(next);
        while (nextI + 1 < n && buildings[nextI + 1][0] == next[0]) {
            heap.add(buildings[++nextI]);
        }
        return nextI + 1;
    }

    public static void main(String[] args) {
        int[][] buildings = {
                 {2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}
//                {0, 2147483647, 2147483647}
//                {1,2,1},{1,2,2},{1,2,3}
        };
        Skyline_218 solver = new Skyline_218();
        System.out.println(solver.getSkyline(buildings));
    }
}
