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
}
