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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class FoodViewController {
        @FXML Label loaiMon;
        @FXML Button loai,menu,xoa;
   public void goBackLoaiMon(ActionEvent e) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            CategoryController controller = loader.getController();
            controller.topLabel.setText(loaiMon.getText());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
	}
   public void goBackMenuInfo(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
        }
   
}
