package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

public class HashTable_get implements Command {
    private final CustomHashTable hashTable;
    private final String key;
    private String error = "";
    private String output = "";

    public HashTable_get(CustomHashTable hashTable, String key) {
        this.hashTable = hashTable;
        this.key = key;
    }

    @Override
    public void execute() {
        String val = hashTable.get(key);
        if (val == null) {
            error = "Key not found: " + key;
        } else {
            output = "Value: " + val;
        }
    }

    @Override
    public void undo() {
        // Get does not change state, nothing to undo
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
        return "HashTable_get(" + key + ")";
    }
}
