package org.conava.dsv.modules.linkedList;

import org.conava.dsv.commands.Command;

public class LinkedList_remove implements Command {
    CustomLinkedList linkedList;
    String value;
    int index;

    public LinkedList_remove(CustomLinkedList linkedList, String value) {
        this.linkedList = linkedList;
        this.value = value;
    }
    @Override
    public void execute() {
        index = linkedList.indexOf(value);
        linkedList.remove(value);
    }

    @Override
    public void undo() {
        linkedList.add(index, value);
    }

    @Override
    public String getString() {
        return "LinkedList.remove(" + value + ")";
    }

    @Override
    public String getOutput() {
        if (value == null) {
            return "";
        }
        return "Removed \"" + value + "\" from index " + index;
    }

    @Override
    public String getError() {
        if (value == null) {
            return "Value cannot be null";
        }

        if (value.isEmpty()) {
            return "Value is empty. Enter a name in the text field to remove the desired element";
        }

        return "";
    }
}
