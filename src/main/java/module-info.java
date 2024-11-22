module org.conava.dsv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens org.conava.dsv to javafx.fxml;
    exports org.conava.dsv;
    exports org.conava.dsv.controllers;
    opens org.conava.dsv.controllers to javafx.fxml;
}