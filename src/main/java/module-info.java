module saracoglu.saracoglu_lab_02 {
    requires javafx.controls;
    requires javafx.fxml;


    opens saracoglu.saracoglu_lab_02 to javafx.fxml;
    exports saracoglu.saracoglu_lab_02;
}