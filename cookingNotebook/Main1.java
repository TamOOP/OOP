package cookingNotebook;
	
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MainView.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("cookingNotebook");
			primaryStage.show();
		} catch(IOException e) {
		e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}