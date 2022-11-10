package cookingNotebook.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CategoryController {
	@FXML
	Label topLabel;
	public Label getTopLabel() {
		return topLabel;
	}
	public void goBack(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MainView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	@FXML public void goAddFoodView(ActionEvent e) throws IOException {
		
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/AddFoodView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            AddFoodViewController controller = loader.getController();
            controller.loaiMon.setText(topLabel.getText());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
	}
        
        public void goFoodView(MouseEvent e) throws IOException{
            FXMLLoader foodview = new FXMLLoader();
            foodview.setLocation(getClass().getResource("/resource/fxml/FoodView.fxml"));
            Parent FoodView = foodview.load();
            Scene scene = new Scene(FoodView);
            AddFoodViewController controller = foodview.getController();
            controller.loaiMon.setText(topLabel.getText());
            Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
           stage.setScene(scene);
        }
         
}