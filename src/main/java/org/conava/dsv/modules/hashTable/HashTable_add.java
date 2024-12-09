package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

public class HashTable_add implements Command {
    private CustomHashTable hashTable;
    private String key;
    private String value;
    private String error = "";
    private String output = "";
    private String oldValue; // For undo
    private boolean replaced = false;

    public HashTable_add(CustomHashTable hashTable, String key, String value) {
        this.hashTable = hashTable;
        this.key = key;
        this.value = value;
    }

    @Override
    public void execute() {
        if (key == null || key.isEmpty()) {
            error = "Key cannot be empty.";
            return;
        }
        // Check if key existed before
        String existing = hashTable.get(key);
        if (existing != null) {
            replaced = true;
            oldValue = existing;
        }
        hashTable.add(key, value);
        output = "Added (" + key + "=" + value + ")";
    }

    @Override
    public void undo() {
        if (replaced) {
            hashTable.add(key, oldValue);
        } else {
            hashTable.remove(key);
        }
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getString() {
        return "HashTable_add(" + key + ", " + value + ")";
    }
}
