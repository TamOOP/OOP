/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class MenuViewController {

    @FXML
    private VBox MenuView;
    
    @FXML public void goBack(ActionEvent e) throws IOException{
        MenuView.getScene().setRoot(FunctionsController.popRoot());
    }
    @FXML
    public void TaoThuCong(ActionEvent e) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
            Parent addFoodView = loader.load();
            CreateMenuViewController controller = loader.getController();
            controller.sua_td.setVisible(false);
            controller.tao_td.setVisible(true);
            controller.but_tao_td.setVisible(true);
            
            FunctionsController.pushRoot(MenuView);
            MenuView.getScene().setRoot(addFoodView);
    }
    @FXML
    public void MenuInfo(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
            FunctionsController.pushRoot(MenuView);
            MenuView.getScene().setRoot(root);
        }
}