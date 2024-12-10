package org.conava.dsv.modules.linkedList;

import org.conava.dsv.commands.Command;

import java.util.LinkedList;
import java.util.List;

public class LinkedList_clear implements Command {
    CustomLinkedList linkedList;
    List<String> values;

    public LinkedList_clear(CustomLinkedList linkedList) {
        this.linkedList = linkedList;
    }
    @Override
    public void execute() {
        values = new LinkedList<>();
        for (int i = 0; i < linkedList.size(); i++) {
            values.add(linkedList.get(i));
        }
        linkedList.clear();
    }

    @Override
    public void undo() {
        for (String value : values) {
            linkedList.add(value);
        }
    }

    @Override
    public String getString() {
        return "LinkedList.clear()";
    }

    @Override
    public String getOutput() {
        if (values.isEmpty()) {
            return "";
        }
        return "Linked List Cleared";
    }

    @Override
    public String getError() {
        if (values.isEmpty()) {
            return "Linked List is already empty";
        }
        return "";
    }
}
