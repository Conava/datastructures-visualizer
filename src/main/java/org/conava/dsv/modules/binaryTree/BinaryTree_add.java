package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

/**
 * The BinaryTree_add class is a command to add a value to a binary tree.
 */
public class BinaryTree_add implements Command {
    private final CustomBinaryTree binaryTree;
    private final String value;
    private String error = "";
    private String output = "";
    private boolean added = false;

    public BinaryTree_add(CustomBinaryTree binaryTree, String value) {
        this.binaryTree = binaryTree;
        this.value = value;
    }

    @Override
    public void execute() {
        if (value == null || value.isEmpty()) {
            error = "Value cannot be empty.";
            return;
        }
        int oldSize = binaryTree.size();
        binaryTree.add(value);
        if (binaryTree.size() > oldSize) {
            added = true;
            output = "Added: " + value;
        } else {
            output = "Value already exists: " + value;
        }
    }

    @Override
    public void undo() {
        if (added) {
            binaryTree.remove(value);
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
        return "BinaryTree.add(" + value + ")";
    }
}
