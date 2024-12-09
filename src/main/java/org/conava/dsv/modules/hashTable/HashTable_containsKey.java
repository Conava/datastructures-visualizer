package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

public class HashTable_containsKey implements Command {
    private CustomHashTable hashTable;
    private String key;
    private String error = "";
    private String output = "";

    public HashTable_containsKey(CustomHashTable hashTable, String key) {
        this.hashTable = hashTable;
        this.key = key;
    }

    @Override
    public void execute() {
        boolean found = hashTable.containsKey(key);
        output = "containsKey(" + key + ") = " + found;
    }

    @Override
    public void undo() {
        // Contains does not change state, nothing to undo
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
        return "HashTable_containsKey(" + key + ")";
    }
}
