module com.mycompany.trieproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.trieproject to javafx.fxml;
    exports com.mycompany.trieproject;
}
