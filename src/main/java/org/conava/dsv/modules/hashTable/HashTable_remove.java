package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

public class HashTable_remove implements Command {
    private final CustomHashTable hashTable;
    private final String key;
    private String removedValue;
    private String error = "";
    private String output = "";

    public HashTable_remove(CustomHashTable hashTable, String key) {
        this.hashTable = hashTable;
        this.key = key;
    }

    @Override
    public void execute() {
        if (!hashTable.containsKey(key)) {
            error = "Key not found: " + key;
            return;
        }
        removedValue = hashTable.get(key);
        hashTable.remove(key);
        output = "Removed key: " + key;
    }

    @Override
    public void undo() {
        if (removedValue != null) {
            hashTable.add(key, removedValue);
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
        return "hashTable.remove(" + key + ")";
    }
}
