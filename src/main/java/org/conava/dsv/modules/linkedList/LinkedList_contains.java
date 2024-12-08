package org.conava.dsv.modules.linkedList;

import org.conava.dsv.commands.Command;

public class LinkedList_contains implements Command {
    CustomLinkedList linkedList;
    String value;
    boolean contains;

    public LinkedList_contains(CustomLinkedList linkedList, String value) {
        this.linkedList = linkedList;
        this.value = value;
    }
    @Override
    public void execute() {
        contains = linkedList.contains(value);
    }

    @Override
    public void undo() {
        // Not needed for this command
    }

    @Override
    public String getString() {
        return "LinkedList.contains(" + value + ")";
    }

    @Override
    public String getOutput() {
        return "" + contains;
    }

    @Override
    public String getError() {
        if (value == null) {
            return "Value cannot be null";
        }

        if (value.isEmpty()) {
            return "Please enter a value in the text field to look for the desired element";
        }

        return "";
    }
}
