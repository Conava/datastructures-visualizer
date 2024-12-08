package org.conava.dsv.modules.linkedList;

import org.conava.dsv.commands.Command;

public class LinkedList_size implements Command {
    CustomLinkedList linkedList;
    int size;

    public LinkedList_size(CustomLinkedList linkedList) {
        this.linkedList = linkedList;
    }
    public void execute() {
        size = linkedList.size();
    }

    public void undo() {
        // Not needed for this command
    }

    public String getString() {
        return "LinkedList.size()";
    }

    public String getOutput() {
        return "" + size;
    }

    public String getError() {
        return "";
    }
}
