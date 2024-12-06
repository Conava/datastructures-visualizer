package org.conava.dsv.modules.hashTable;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.conava.dsv.modules.DataStructureModule;

public class HashTableModule implements DataStructureModule {
    private Pane view;

    public HashTableModule() {
        // Initialize the HashTable visualization
    }

    @Override
    public Pane getView() {
        return view;
    }

    @Override
    public void executeCommand(String command) {
        // Handle linked list specific commands
    }

    @Override
    public Node getModuleUI() {
        return null;
    }
}
