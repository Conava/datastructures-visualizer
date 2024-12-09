package org.conava.dsv.modules.binaryTree;

import org.conava.dsv.commands.Command;

public class BinaryTree_size implements Command {
    private final CustomBinaryTree binaryTree;
    private String error = "";
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
        return error;
    }

    @Override
    public String getString() {
        return "BinaryTree_size()";
    }
}
