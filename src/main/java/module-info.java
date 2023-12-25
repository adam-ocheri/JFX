module com.datadis.javafx_dnd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.datadis.javafx_dnd to javafx.fxml;
    exports com.datadis.javafx_dnd;
}