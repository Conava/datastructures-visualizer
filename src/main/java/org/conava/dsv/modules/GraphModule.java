package org.conava.dsv.modules;

import javafx.scene.layout.Pane;

public class GraphModule implements DataStructureModule {
    private Pane view;

    public GraphModule() {
        // Initialize the Graph visualization
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
