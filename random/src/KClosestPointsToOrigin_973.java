import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin_973 {
    public int[][] kClosest(int[][] points, int K) {
        // calculate all points' distances first and store in a map
        // avoids re-computation when inserting / deleting in the heap.
        HashMap<int[], Integer> distances = new HashMap<>();
        for (int[] pt : points) distances.put(pt, pt[0] * pt[0] + pt[1] * pt[1]);
        int n = points.length;
        boolean heapStoresClosestPoints = K < n - K;
        // K closest points in max heap, or (n - K) farthest points in min heap
        int capacity = heapStoresClosestPoints ? K : n - K;
        if (capacity == 0) return points;
        PriorityQueue<int[]> heap = new PriorityQueue<>(capacity,
                Comparator.comparingInt(heapStoresClosestPoints ? i -> -distances.get(i) : distances::get));
        for (int i = 0; i < capacity; i++) heap.add(points[i]);
        if (heapStoresClosestPoints) {
            for (int i = capacity; i < n; i++)
                if (distances.get(points[i]) < distances.get(heap.peek())) {
                    heap.remove();
                    heap.add(points[i]);
                }
            return heap.toArray(int[][]::new);
        } else { // heap stores furthest points
            for (int i = capacity; i < n; i++)
                if (distances.get(points[i]) > distances.get(heap.peek())){
                    heap.remove();
                    heap.add(points[i]);
                }
            for (int[] pt : heap) distances.remove(pt);
            return distances.keySet().toArray(int[][]::new);
        }
    }
}
