import java.util.*;

public class KeysAndRooms_841 {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> unlockedRooms = new HashSet<>();
        int n = rooms.size();
        Queue<Integer> roomsToVisit = new LinkedList<>();
        roomsToVisit.offer(0);
        while (!roomsToVisit.isEmpty() && unlockedRooms.size() < n) {
            int cur = roomsToVisit.poll();
            unlockedRooms.add(cur);
            for (int roomKey : rooms.get(cur)) if (!unlockedRooms.contains(roomKey)) roomsToVisit.offer(roomKey);
        }
        return unlockedRooms.size() == n;
    }
}
