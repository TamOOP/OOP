/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodDAOImpl;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.FormattableFlags.UPPERCASE;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class CreateMenuViewController implements Initializable{
     @FXML     ToolBar tao_td,sua_td;
     @FXML VBox chonmon;
   @FXML Button vegetableBut,meatBut,soupBut,fishBut,holidayBut,juiceBut,riceBut,cakeBut,but_tao_td;
   @FXML Pane thongbao;
    @FXML
    private Pane CreateMenu;
    @FXML
    private TextField searchType;
    @FXML
    private TilePane viewFood;
    @FXML
    private void changeTab(ActionEvent event) throws SQLException {
        Button btn = (Button) event.getSource();
        VBox parent = (VBox) ((Pane)btn.getParent()).getParent();
        for(int i = 1; i<9 ; i++){
            Pane pane = (Pane) parent.getChildren().get(i);
            ((Button)pane.getChildren().get(0)).setStyle("-fx-background-color:");
        }
        btn.setStyle("-fx-background-color:#04AA6D");
        String category = btn.getText().toUpperCase();
        FoodDAOImpl fImpl = new FoodDAOImpl();
        searchType.setPromptText("Tìm kiếm món loại "+category);
        
        for(int i = 0 ; i < viewFood.getChildren().size() ; i ++ ){
            viewFood.getChildren().remove(i);
        }
        if(fImpl.getFoodInCategory(category).isEmpty()){
            Label lb = new Label();
            lb.setText("Không có công thức");
            TilePane.setMargin(lb, new Insets(200,0,0,280));
            lb.setStyle("-fx-text-fill:#736d6d; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
            viewFood.getChildren().add(lb);
        }
        fImpl.getFoodInCategory(category).forEach((var f) ->{
            CategoryController con = new CategoryController();
            VBox fFood = con.CreateFrame(f.getName(), f.convertImg(), f.getCookTime());
            Pane pane = new Pane();
            pane.setPrefWidth(200);
            pane.setPrefHeight(222);
            CheckBox cb = new CheckBox();
            cb.setLayoutX(91);
            cb.setLayoutY(245);
            pane.getChildren().add(fFood);
            pane.getChildren().add(cb);
            viewFood.getChildren().add(pane);
        });
    }
    @FXML
   public void goBack(ActionEvent e) throws IOException{
       CreateMenu.getScene().setRoot(FunctionsController.popRoot());
   }
    @FXML
   public void showThongbao(){
       thongbao.setVisible(true);
       chonmon.setOpacity(0.3);
   }
    @FXML
   public void hideThongbao(){
       thongbao.setVisible(false);
       chonmon.setOpacity(1);
   }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FoodDAOImpl fImpl = new FoodDAOImpl();
         try {
             if(fImpl.getFoodInCategory("VEGETABLE").isEmpty()){
                 Label lb = new Label();
                 lb.setText("Không có công thức");
                 TilePane.setMargin(lb, new Insets(200,0,0,280));
                 lb.setStyle("-fx-text-fill:#736d6d; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
                 viewFood.getChildren().add(lb);
             }} catch (SQLException ex) {
             Logger.getLogger(CreateMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             fImpl.getFoodInCategory("VEGETABLE").forEach((var f) ->{
                 CategoryController con = new CategoryController();
                 VBox fFood = con.CreateFrame(f.getName(), f.convertImg(), f.getCookTime());
                 Pane pane = new Pane();
                 pane.setPrefWidth(200);
                 pane.setPrefHeight(222);
                 CheckBox cb = new CheckBox();
                 cb.setLayoutX(91);
                 cb.setLayoutY(245);
                 pane.getChildren().add(fFood);
                 pane.getChildren().add(cb);
                 viewFood.getChildren().add(pane);
             });
         } catch (SQLException ex) {
             Logger.getLogger(CreateMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
 

    
}