/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cookingNotebook.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuInfoViewController  {
   @FXML
    
      public void SuaThucDon(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            CreateMenuViewController controller = loader.getController();
            controller.sua_td.setVisible(true);
            controller.tao_td.setVisible(false);
            controller.but_tao_td.setVisible(false);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene); 
                
    }
      
      public void goBack(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
    }
      public void XemMonAn(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);

            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene); 
    }
      
    
}