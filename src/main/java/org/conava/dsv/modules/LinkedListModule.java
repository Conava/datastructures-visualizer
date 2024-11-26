package org.conava.dsv.modules;

import javafx.scene.layout.Pane;

public class LinkedListModule implements DataStructureModule {

    private Pane view;

    public LinkedListModule() {
        // Initialize the linked list visualization
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void executeCommand(String command) {
        // Handle linked list specific commands
    }
}