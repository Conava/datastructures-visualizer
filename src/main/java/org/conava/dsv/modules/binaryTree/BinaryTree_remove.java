package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

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
        // Undoing remove is tricky without storing the tree state.
        // Since we have no old value to reinsert that keeps order,
        // we assume exact same value re-add:
        // Actually, since we know the value and it's a BST, re-adding the same value restores it.
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
        return "BinaryTree_remove(" + value + ")";
    }
}
