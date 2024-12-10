package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class HashTable_clear implements Command {
    private final CustomHashTable hashTable;
    private String output = "";
    // To support undo, store the state before clearing
    private final List<StateEntry> oldEntries = new ArrayList<>();

    private static class StateEntry {
        String key;
        String value;
        StateEntry(String k, String v) {
            key = k; value = v;
        }
    }

    public HashTable_clear(CustomHashTable hashTable) {
        this.hashTable = hashTable;
    }

    @Override
    public void execute() {
        // Save all current key-value pairs
        var buckets = hashTable.getBuckets();
        for (var bucket : buckets) {
            for (var entry : bucket) {
                oldEntries.add(new StateEntry(entry.key, entry.value));
            }
        }
        hashTable.clear();
        output = "Hash table cleared";
    }

    @Override
    public void undo() {
        // Restore all entries
        for (StateEntry e : oldEntries) {
            hashTable.add(e.key, e.value);
        }
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public String getError() {
        return "";
    }

    @Override
    public String getString() {
        return "hashTable.clear()";
    }
}
