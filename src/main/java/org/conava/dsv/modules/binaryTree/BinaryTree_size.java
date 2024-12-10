package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

/**
 * The BinaryTree_size class is a command to get the size of a binary tree.
 */
public class BinaryTree_size implements Command {
    private final CustomBinaryTree binaryTree;
    private String output = "";

    public BinaryTree_size(CustomBinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    @Override
    public void execute() {
        int s = binaryTree.size();
        output = "Size: " + s;
    }

    @Override
    public void undo() {
        // size does not change state, nothing to undo
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
        return "BinaryTree.size()";
    }
}
