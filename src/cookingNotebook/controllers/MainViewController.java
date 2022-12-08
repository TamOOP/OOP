package cookingNotebook.controllers;

import cookingNotebook.DAO.PathDeleted;
import static cookingNotebook.controllers.FunctionsController.pushRoot;
import cookingNotebook.models.FoodSP;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;


public class MainViewController extends FunctionsController<String> implements Initializable {

    @FXML
	private Parent mainView;

	@FXML
	private TilePane categorysView;
	      
    @FXML
        public void goMenu(ActionEvent e) throws IOException{
                Parent allMenuView = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuView.fxml"));

		pushRoot(mainView);

		mainView.getScene().setRoot(allMenuView);
        
        }

    @Override
    public void check(String t) throws SQLException {
        System.out.println(t+ " ok");    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        PathDeleted pd = new PathDeleted();
        try {
            pd.removeAll();
        } catch (SQLException | IOException ex) {}
        for (int i = 0; i < categorysView.getChildren().size(); i++) {
            Pane pane = (Pane) categorysView.getChildren().get(i);
            pane.setOnMouseClicked(eh->{
                try {
                    Text txt_category = (Text) ((Pane) ((Pane) eh.getSource()).getChildren().get(1)).getChildren().get(0);
                    FoodSP.category = txt_category.getText();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
                    Parent categoryView = loader.load();
                    pushRoot(mainView);
                    mainView.getScene().setRoot(categoryView);
                } catch (IOException e) {}
            });
        }
    }
        
}