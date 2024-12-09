package org.conava.dsv.modules.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A custom hash table that stores key-value pairs as strings, uses separate chaining for collisions,
 * and supports dynamic resizing. Also tracks the last hash calculation steps.
 */
public class CustomHashTable {
    static class Entry {
        String key;
        String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<LinkedList<Entry>> buckets;
    private int size;
    private static final int INITIAL_CAPACITY = 5;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private String lastHashCalculation = "";

    public CustomHashTable() {
        buckets = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    private int hash(String key) {
        // Compute hash index
        int hashCode = Objects.hashCode(key);
        int index = Math.abs(hashCode) % buckets.size();
        // Store the calculation for visualization: (hashCode % capacity)
        lastHashCalculation = "hashCode(" + key + ")=" + hashCode + ", capacity=" + buckets.size()
                + " => index=" + index;
        return index;
    }

    public void add(String key, String value) {
        if (key == null) return;
        if ((double)(size + 1) / buckets.size() > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = hash(key);
        LinkedList<Entry> bucket = buckets.get(index);
        for (Entry e : bucket) {
            if (Objects.equals(e.key, key)) {
                e.value = value; // replace value if key already exists
                return;
            }
        }
        bucket.add(new Entry(key, value));
        size++;
    }

    public void remove(String key) {
        int index = hash(key);
        LinkedList<Entry> bucket = buckets.get(index);
        Entry toRemove = null;
        for (Entry e : bucket) {
            if (Objects.equals(e.key, key)) {
                toRemove = e;
                break;
            }
        }
        if (toRemove != null) {
            bucket.remove(toRemove);
            size--;
        }
    }

    public String get(String key) {
        int index = hash(key);
        LinkedList<Entry> bucket = buckets.get(index);
        for (Entry e : bucket) {
            if (Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.size();
    }

    public void clear() {
        buckets.clear();
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    public List<LinkedList<Entry>> getBuckets() {
        return buckets;
    }

    public String getLastHashCalculation() {
        return lastHashCalculation;
    }

    private void resize() {
        int newCapacity = buckets.size() * 2;
        List<LinkedList<Entry>> newBuckets = new ArrayList<>(newCapacity);
        for (int i = 0; i < newCapacity; i++) {
            newBuckets.add(new LinkedList<>());
        }

        // Re-hash all entries
        for (List<Entry> bucket : buckets) {
            for (Entry e : bucket) {
                int hashCode = Objects.hashCode(e.key);
                int index = Math.abs(hashCode) % newCapacity;
                newBuckets.get(index).add(e);
            }
        }

        buckets = newBuckets;
        // Size remains the same
    }
}
