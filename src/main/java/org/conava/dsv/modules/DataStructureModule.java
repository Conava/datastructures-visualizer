package org.conava.dsv.modules;

import javafx.scene.layout.Pane;

public interface DataStructureModule {
    Pane getView();
    void executeCommand(String command);
}
