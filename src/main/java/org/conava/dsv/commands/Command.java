package org.conava.dsv.commands;

public interface Command {
    void execute();
    void undo();
}