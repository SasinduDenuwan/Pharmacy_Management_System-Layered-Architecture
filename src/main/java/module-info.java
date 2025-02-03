module com.assignment.final_course_work {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires com.jfoenix;
    requires java.desktop;
    requires java.mail;
    requires com.google.protobuf;
    requires net.sf.jasperreports.core;

    opens com.assignment.final_course_work.controller to javafx.fxml;
    exports com.assignment.final_course_work;
    exports com.assignment.final_course_work.controller;

}