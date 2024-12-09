package org.conava.dsv.modules.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * A simple hash table implementation using separate chaining.
 * Stores key-value pairs as strings.
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

    private final List<LinkedList<Entry>> buckets;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomHashTable() {
        buckets = new ArrayList<>(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets.add(new LinkedList<>());
        }
        size = 0;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % buckets.size();
    }

    public void add(String key, String value) {
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

    public void clear() {
        for (LinkedList<Entry> bucket : buckets) {
            bucket.clear();
        }
        size = 0;
    }

    // For visualization
    public int capacity() {
        return buckets.size();
    }

    public List<LinkedList<Entry>> getBuckets() {
        return buckets;
    }
}