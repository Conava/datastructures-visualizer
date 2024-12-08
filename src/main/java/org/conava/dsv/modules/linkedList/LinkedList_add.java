package org.conava.dsv.modules.linkedList;

import org.conava.dsv.commands.Command;

public class LinkedList_add implements Command {
    CustomLinkedList linkedList;
    String value;

    public LinkedList_add(CustomLinkedList linkedList, String value) {
        this.linkedList = linkedList;
        this.value = value;
    }
    @Override
    public void execute() {
        if(value != null && !value.isEmpty()) {
            linkedList.add(value);
        }
    }

    @Override
    public void undo() {
        linkedList.remove(value);
    }

    @Override
    public String getString() {
        if (value == null) {
            return "";
        }
        return "LinkedList.add(" + value + ")";
    }

    @Override
    public String getOutput() {
        return "";
    }

    @Override
    public String getError() {
        if (value == null) {
            return "Value cannot be null";
        }

        if (value.isEmpty()) {
            return "Please enter a value in the text field to add elements to the linked list";
        }

        return "";
    }
}
