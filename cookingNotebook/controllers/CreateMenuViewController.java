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
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class CreateMenuViewController {
     @FXML     ToolBar tao_td,sua_td;
   @FXML VBox vegetable,meat,soup,fish,holiday,juice,cake,rice,chonmon;
   @FXML Button vegetableBut,meatBut,soupBut,fishBut,holidayBut,juiceBut,riceBut,cakeBut,but_tao_td;
   @FXML Pane thongbao;
    
   public void VegetableOn(){
      vegetable.setVisible(true);
      vegetableBut.setStyle("-fx-background-color:#04AA6D");
      meat.setVisible(false);
      meatBut.setStyle("-fx-background-color:");
      fish.setVisible(false);
      fishBut.setStyle("-fx-background-color:");
      soup.setVisible(false); 
      soupBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
    } 
   public void MeatOn(){
      vegetable.setVisible(false);
      meat.setVisible(true);
      fish.setVisible(false);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:#04AA6D");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
   }
   public void FishOn(){
      vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(true);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:#04AA6D");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
    }
   public void SoupOn(){
      vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(false);
      soup.setVisible(true); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:#04AA6D");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
    }
   public void HolidayOn(){
      vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(false);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(true); 
      holidayBut.setStyle("-fx-background-color:#04AA6D");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
    }
   public void CakeOn(){
       vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(false);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(true); 
      cakeBut.setStyle("-fx-background-color:#04AA6D");
   }
   public void JuiceOn(){
       vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(false);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(true); 
      juiceBut.setStyle("-fx-background-color:#04AA6D");
      rice.setVisible(false); 
      riceBut.setStyle("-fx-background-color:");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
   }
   public void RiceOn(){
       vegetable.setVisible(false);
      meat.setVisible(false);
      fish.setVisible(false);
      soup.setVisible(false); 
      meatBut.setStyle("-fx-background-color:");
      vegetableBut.setStyle("-fx-background-color:");
      soupBut.setStyle("-fx-background-color:");
      fishBut.setStyle("-fx-background-color:");
      holiday.setVisible(false); 
      holidayBut.setStyle("-fx-background-color:");
      juice.setVisible(false); 
      juiceBut.setStyle("-fx-background-color:");
      rice.setVisible(true); 
      riceBut.setStyle("-fx-background-color:#04AA6D");
      cake.setVisible(false); 
      cakeBut.setStyle("-fx-background-color:");
   }
   public void goBack(ActionEvent e) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
   }
   public void goMenuInfo(ActionEvent e) throws IOException{
       Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
   }
   public void showThongbao(){
       thongbao.setVisible(true);
       chonmon.setOpacity(0.3);
   }
   public void hideThongbao(){
       thongbao.setVisible(false);
       chonmon.setOpacity(1);
   }
}