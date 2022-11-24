/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cookingNotebook.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuInfoViewController  {

    @FXML
    private Pane MenuInfo;
   @FXML
    
      public void SuaThucDon(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
            Parent addFoodView = loader.load();
            CreateMenuViewController controller = loader.getController();
            controller.sua_td.setVisible(true);
            controller.tao_td.setVisible(false);
            controller.but_tao_td.setVisible(false);
            FunctionsController.pushRoot(MenuInfo);
            MenuInfo.getScene().setRoot(addFoodView);
    }
      
    @FXML
      public void goBack(ActionEvent e) throws IOException{
        MenuInfo.getScene().setRoot(FunctionsController.popRoot());
    }
    @FXML
      public void XemMonAn(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
            Parent addFoodView = loader.load();
            FunctionsController.pushRoot(MenuInfo);
            MenuInfo.getScene().setRoot(addFoodView);
    }
      
    
}