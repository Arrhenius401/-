package com.work.controller;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

//警告窗口
public class AlertController {
    public static void showAlert(Stage primaryStage,String title,String headText,String content){
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(headText);
        alert.showAndWait();
    }
}
