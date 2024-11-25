module org.conava.dsv.modules.api {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.conava.dsv.modules.api to javafx.fxml;
    exports org.conava.dsv.modules.api;
}