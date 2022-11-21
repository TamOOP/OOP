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
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class MenuViewController {
    
    @FXML public void goBack(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MainView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
    }
    public void TaoThuCong(ActionEvent e) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            CreateMenuViewController controller = loader.getController();
            controller.sua_td.setVisible(false);
            controller.tao_td.setVisible(true);
            controller.but_tao_td.setVisible(true);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene); 
                
    }
    public void MenuInfo(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
        }
}