package cookingNotebook.controllers;

import java.io.IOException;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainViewController {
	@FXML
	public void goCategoryView(MouseEvent e) throws IOException {
            FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resource/fxml/CategoryView.fxml"));
        Parent CategoryView = loader.load();
        Scene scene = new Scene(CategoryView);
        CategoryController controller = loader.getController();
    	controller.topLabel.setText(((Label) e.getSource()).getText());
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
        public void goMenu(ActionEvent e) throws IOException{
            Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
        }
        
}