import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet_380 {
    private final HashMap<Integer, Integer> map; // key: inserted; value: its index in the list
    private final ArrayList<Integer> list;
    private final Random rand;

    public RandomizedSet_380() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.remove(val);
        int n = list.size();
        if (index != n - 1) { // if the element is not the last in the list:
            list.set(index, list.get(n - 1)); // "exchange" with last element
            map.put(list.get(index), index); // reflect the updated index in map
        }
        list.remove(n - 1); // remove the last element
        return true;
    }

    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}
