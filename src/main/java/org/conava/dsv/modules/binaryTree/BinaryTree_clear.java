package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * The BinaryTree_clear class is a command to clear a binary tree.
 */
public class BinaryTree_clear implements Command {
    private final CustomBinaryTree binaryTree;
    private String output = "";
    // Store all values to restore on undo
    private final List<String> oldValues = new ArrayList<>();

    public BinaryTree_clear(CustomBinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    @Override
    public void execute() {
        // Traverse and store all values before clearing
        storeValues(binaryTree.getRoot());
        binaryTree.clear();
        output = "Binary tree cleared";
    }

    private void storeValues(CustomBinaryTree.Node node) {
        if (node == null) return;
        oldValues.add(node.value);
        storeValues(node.left);
        storeValues(node.right);
    }

    @Override
    public void undo() {
        // Reinsert all old values
        for (String v : oldValues) {
            binaryTree.add(v);
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
        return "BinaryTree.clear()";
    }
}
