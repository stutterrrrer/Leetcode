import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Pair<U, V> {
    public U first;
    public V second;

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Bucket {
    private final List<Pair<Integer, Integer>> bucket;

    public Bucket() {
        this.bucket = new LinkedList<>();
    }

    public Integer getValue(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key))
                return pair.second;
        }
        return -1;
    }

    public void overrideExistingKeyOrAddNewPair(Integer key, Integer value) {
        boolean found = false;
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                pair.second = value;
                found = true;
            }
        }
        if (!found)
            this.bucket.add(new Pair<>(key, value));
    }

    public void remove(Integer key) {
        for (Pair<Integer, Integer> pair : this.bucket) {
            if (pair.first.equals(key)) {
                this.bucket.remove(pair);
                break;
            }
        }
    }
}

class MyHashMap {
    private final int key_space;
    private final List<Bucket> hash_table;

    public MyHashMap() {
        this.key_space = 2069;
        // no resizing the outer-most array;
        // if insert too many elements, the individual buckets (array elements) will become large.
        this.hash_table = new ArrayList<>();
        for (int i = 0; i < this.key_space; ++i) {
            this.hash_table.add(new Bucket());
        }
    }

    public void put(int key, int value) {
        // assume key is always positive
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).overrideExistingKeyOrAddNewPair(key, value);
    }

    public int get(int key) {
        // return value or -1 if key doesn't exist
        int hash_key = key % this.key_space;
        return this.hash_table.get(hash_key).getValue(key);
    }

    public void remove(int key) {
        int hash_key = key % this.key_space;
        this.hash_table.get(hash_key).remove(key);
    }
}