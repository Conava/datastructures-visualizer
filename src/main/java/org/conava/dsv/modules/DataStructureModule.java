package org.conava.dsv.modules;

import javafx.scene.Node;

/**
 * The DataStructureModule interface is used to define a module that can be added to the Data Structure Visualizer.
 */
public interface DataStructureModule {
    /**
     * Get the UI of the module.
     * @return The UI of the module.
     */
    Node getModuleUI();
}
