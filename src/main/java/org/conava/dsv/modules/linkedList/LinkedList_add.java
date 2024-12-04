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
        linkedList.add(value);
    }

    @Override
    public void undo() {
        linkedList.remove(value);
    }

    @Override
    public String getString() {
        return "LinkedList.add(" + value + ")";
    }
}
