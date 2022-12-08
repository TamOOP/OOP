package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.models.FoodSP;
import cookingNotebook.models.FunctionsModel;
import cookingNotebook.models.LogicFood;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CategoryController implements Initializable{
    @FXML
     Label topLabel;
    @FXML
    private VBox categoryView;
    @FXML
    private TilePane foodsView;
    
    final ObservableList<LogicFood> foods = FXCollections.observableArrayList();
   
    @FXML
	public void goBack(ActionEvent e) throws IOException {
		categoryView.getScene().setRoot(FunctionsController.popRoot());
	}

	@FXML public void goAddFoodView(ActionEvent e) throws IOException {
            FoodSP.AddFood=true;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/AddFoodView.fxml"));
            Parent addFoodView = loader.load();
            AddFoodViewController controller = loader.getController();
            controller.init(topLabel);
            controller.function.setText("Thêm món ăn");
            FunctionsController.pushRoot(categoryView);
            categoryView.getScene().setRoot(addFoodView);
	}
       
    public VBox CreateFrame(String name, Image img, int cooktime){
        VBox frame = new VBox();
        ImageView imgView = new ImageView();
        Text txt_name = new Text();
        Pane pane = new Pane();
        Text t1 = new Text();
        Label time = new Label();
        Text t2 = new Text();
        
        frame.setCursor(Cursor.HAND);
        frame.setId("c1");
        frame.setPrefWidth(200);
        frame.setPrefHeight(238);
        
        imgView.setImage(img);
        imgView.setFitWidth(200);
        imgView.setFitHeight(172);
        
        txt_name.setText(name);
        txt_name.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
        VBox.setMargin(txt_name, new Insets(10,0,0,10));
        
        pane.setPrefWidth(200);
        pane.setPrefHeight(35);
        
        t1.setText("Thời gian nấu: ");
        t1.setLayoutX(10);
        t1.setLayoutY(19);
        
        time.setLayoutX(120);
        time.setLayoutY(3);
        time.setGraphic(t2);
        time.setContentDisplay(ContentDisplay.RIGHT);
        if(cooktime == 0){
            time.setText("NaN");
            t2.setVisible(false);
        }else{
            time.setText(""+cooktime);
            t2.setText("phút");
        }
        pane.getChildren().add(t1);
        pane.getChildren().add(time);
        frame.getChildren().add(imgView);
        frame.getChildren().add(txt_name);
        frame.getChildren().add(pane);

        return frame;
    }
    public void init(String label) throws SQLException {
        this.topLabel.setText(label);
        this.foods.setAll(FunctionsModel.initFoodInDB());
        FoodDAOImpl fImpl = new FoodDAOImpl();
        if (fImpl.getFoodInCategory(label).isEmpty()) {
            Label lb = new Label();
            lb.setText("Không có công thức");
            TilePane.setMargin(lb, new Insets(200, 0, 0, 400));
            lb.setStyle("-fx-text-fill:#6b6b6b;; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
            foodsView.getChildren().add(lb);
        }
        foods.forEach( f -> {
            String foodName = f.getName();
            int cookTime = f.getCookTime();
            Image image = null;
            if (topLabel.getText().equalsIgnoreCase(f.getCategory())) {
                VBox food = null;
                image = f.convertImg();
                
                food = CreateFrame(foodName, image, cookTime);
                food.setOnMouseClicked(event -> {
                    try {
                        String FoodNameTarget = ((Text) ((VBox) event.getSource()).getChildren().get(1)).getText();
                        FoodSP.setName(FoodNameTarget);
                        FXMLLoader loader = new FXMLLoader(CategoryController.this.getClass().getResource("/resource/fxml/FoodView.fxml"));
                        Parent foodView = loader.load();
                        FoodViewController c = loader.getController();
                        c.del_btn.setVisible(true);
                        c.update_btn.setVisible(true);
                        c.back_btn.setOnAction(eh->{
                            try {
                                c.goBack(eh);
                            } catch (IOException ex) { }
                        });
                        FunctionsController.pushRoot(categoryView);
                        categoryView.getScene().setRoot(foodView);
                    } catch (IOException e) {
                    }
                });
                foodsView.getChildren().add(food);
            }
        });
    }

    public void add(LogicFood food) {
        foods.add(food);

    }         

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init(FoodSP.category);
        } catch (SQLException ex) {}
    }
}