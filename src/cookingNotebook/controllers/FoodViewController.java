/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import java.text.DecimalFormat;
import cookingNotebook.DAO.FoodAndIngredientDAOImpl;
import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.Step;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author Admin
 */
public class FoodViewController {  
    DecimalFormat format = new DecimalFormat();
    
    @FXML
    private Text name;
    @FXML
    private VBox foodView;
    @FXML
    private ImageView foodImage;
    @FXML
    private TilePane IngrList;
    @FXML
    private TextFlow Step;
    @FXML
   public void goBack(ActionEvent e) throws IOException {
       foodView.getScene().setRoot(FunctionsController.popRoot());
    }
    @FXML
   public void ChinhSua(ActionEvent e) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/AddFoodView.fxml"));
            Parent addFoodView = loader.load();
            
            AddFoodViewController controller = loader.getController();
            controller.function.setText("Sửa món ăn");
            
            FunctionsController.pushRoot(foodView);
            foodView.getScene().setRoot(addFoodView);
	}


    void init(String foodname) throws SQLException {
        FoodDAOImpl f = new FoodDAOImpl();
        FoodAndIngredientDAOImpl food_ingr = new FoodAndIngredientDAOImpl();
        LogicFood food = f.getFood(foodname);
        name.setText(foodname);
        foodImage.setImage(food.convertImg());
        
        List <String> IngrName = new ArrayList<String>();
        List <Double> IngrAmount = new ArrayList<Double>();
        List <String> IngrUnit = new ArrayList<String>();     
        food.getIngredientsDB().forEach(ingr ->{
            IngrName.add(ingr.getName().toString());
            try {
                IngrAmount.add(food_ingr.get(food.getId(), ingr.getId()).getAmount());
                IngrUnit.add(food_ingr.get(food.getId(), ingr.getId()).getUnit());
           
            } catch (SQLException ex) {
                Logger.getLogger(FoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        for(int i = 0; i < IngrName.size(); i++){
            Text txt_ingr = new Text();
            if(IngrUnit.get(i) == null){
                txt_ingr.setText("-" + IngrName.get(i));
            }else{
                txt_ingr.setText("-" + IngrName.get(i) + ": " + format.format(IngrAmount.get(i)) +
                    " "+ IngrUnit.get(i));
            }  
            txt_ingr.setFontSmoothingType(FontSmoothingType.LCD);
            txt_ingr.setWrappingWidth(200);
            txt_ingr.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
            TilePane.setMargin(txt_ingr, new Insets(5,10,5,0));
            IngrList.getChildren().add(txt_ingr);
        }
        List<Step> StepInfo = new ArrayList<Step>();
        food.getStepsDB().forEach(step ->{
            StepInfo.add(step);
        });
        for(int i = 0; i < StepInfo.size(); i++){
            VBox vbox = new VBox();
            TextFlow tf = new TextFlow();
            ImageView StepImg = new ImageView();
            Text index = new Text();
            Text content = new Text();
            
            vbox.setPrefWidth(900);
            vbox.setAlignment(Pos.TOP_CENTER);
            tf.setPadding(new Insets(0,0,0,20));
            index.setText("Bước " + (i+1) +":");
            index.setStyle("-fx-font-family: 'Times New Roman Bold'; -fx-font-size: 22;");
            content.setText(StepInfo.get(i).getContent());
            content.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
            StepImg.setImage(StepInfo.get(i).convertImg());
            StepImg.setFitWidth(400);
            StepImg.setFitHeight(269);
            VBox.setMargin(StepImg,new Insets(10,0,10,0));
            
            tf.getChildren().add(index);
            tf.getChildren().add(content);
            vbox.getChildren().add(tf);
            if(StepInfo.get(i).getLinkImg() != null){
                vbox.getChildren().add(StepImg);
            }
            Step.getChildren().add(vbox);
        }
    }
}