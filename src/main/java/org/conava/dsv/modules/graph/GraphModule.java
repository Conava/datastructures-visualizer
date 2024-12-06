package org.conava.dsv.modules.graph;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.conava.dsv.modules.DataStructureModule;

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
    public Node getModuleUI() {
        return null;
    }
}
