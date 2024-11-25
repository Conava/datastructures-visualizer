module org.conava.dsv.modules.linkedlist {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.conava.dsv.modules.linkedlist to javafx.fxml;
    exports org.conava.dsv.modules.linkedlist;
}