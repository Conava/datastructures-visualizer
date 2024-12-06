package org.conava.dsv.modules;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface DataStructureModule {
    Pane getView();
    Node getModuleUI();
}
