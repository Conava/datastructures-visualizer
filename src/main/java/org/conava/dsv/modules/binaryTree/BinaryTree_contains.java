package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

public class BinaryTree_contains implements Command {
    private final CustomBinaryTree binaryTree;
    private final String value;
    private String error = "";
    private String output = "";

    public BinaryTree_contains(CustomBinaryTree binaryTree, String value) {
        this.binaryTree = binaryTree;
        this.value = value;
    }

    @Override
    public void execute() {
        boolean found = binaryTree.contains(value);
        output = "contains(" + value + ") = " + found;
    }

    @Override
    public void undo() {
        // contains does not change state, nothing to undo
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
        return "BinaryTree_contains(" + value + ")";
    }
}
