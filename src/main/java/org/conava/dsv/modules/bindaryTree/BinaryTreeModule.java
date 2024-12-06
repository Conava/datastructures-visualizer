package org.conava.dsv.modules.bindaryTree;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.conava.dsv.modules.DataStructureModule;

public class BinaryTreeModule implements DataStructureModule {
    private Pane view;

    public BinaryTreeModule() {
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
