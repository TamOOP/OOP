package cookingNotebook.controllers;

import static cookingNotebook.controllers.FunctionsController.pushRoot;
import cookingNotebook.models.Type;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;


public class MainViewController extends FunctionsController<String> implements Initializable {
    final ObservableList<Label> categorys = FXCollections.observableArrayList();
    List<Label> lb = new ArrayList<Label>();
    @FXML
	private Parent mainView;

	@FXML
	private TilePane categorysView;
	      
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
        for(int i = 0; i< categorysView.getChildren().size();i++){
            lb.add((Label) categorysView.getChildren().get(i));
        }
        categorys.addAll(lb);
		categorys.forEach(category -> {
			category.setOnMouseClicked(event -> {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
					Parent categoryView = loader.load();

					((CategoryController) loader.getController()).init((Label) event.getSource());

					pushRoot(mainView);

					mainView.getScene().setRoot(categoryView);
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			});
		});
        
    }
        
}