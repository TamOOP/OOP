/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.support;

import cookingNotebook.controllers.AddFoodViewController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class AlertDialog {
    public static void display(String title,String mess) {
        Stage window = new Stage();
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);
        
        Label label = new Label();
        label.setText(mess);
        Button buttonOk= new Button("OK");
        buttonOk.setOnAction(e -> window.close());
        
        VBox layout = new VBox(5);
        layout.getChildren().addAll(label,buttonOk);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
            
            
            
}
