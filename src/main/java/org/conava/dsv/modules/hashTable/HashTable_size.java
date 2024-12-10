package org.conava.dsv.modules.hashTable;

import org.conava.dsv.commands.Command;

public class HashTable_size implements Command {
    private final CustomHashTable hashTable;
    private String output = "";

    public HashTable_size(CustomHashTable hashTable) {
        this.hashTable = hashTable;
    }

    @Override
    public void execute() {
        int s = hashTable.size();
        output = "Size: " + s;
    }

    @Override
    public void undo() {
        // Size does not change state, nothing to undo
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
        return "hashTable.size()";
    }
}