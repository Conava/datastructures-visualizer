package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

/**
 * The BinaryTree_remove class is a command to remove a value from a binary tree.
 */
public class BinaryTree_remove implements Command {
    private final CustomBinaryTree binaryTree;
    private final String value;
    private String error = "";
    private String output = "";
    private boolean removed = false;

    public BinaryTree_remove(CustomBinaryTree binaryTree, String value) {
        this.binaryTree = binaryTree;
        this.value = value;
    }

    @Override
    public void execute() {
        if (!binaryTree.contains(value)) {
            error = "Value not found: " + value;
            return;
        }
        binaryTree.remove(value);
        removed = true;
        output = "Removed: " + value;
    }

    @Override
    public void undo() {
        if (removed) {
            binaryTree.add(value);
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
        return "BinaryTree.remove(" + value + ")";
    }
}
