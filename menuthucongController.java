/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package do_an_oop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 *
 * @author Admin
 */
public class menuthucongController {
   @FXML VBox vegetable,meat,soup,fish,holiday,juice,cake,rice;
   @FXML Button vegetableBut,meatBut,soupBut,fishBut,holidayBut,juiceBut,riceBut,cakeBut;
    
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
}
