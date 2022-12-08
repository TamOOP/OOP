/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.MenuDAOImpl;
import cookingNotebook.DAO.Menu_FoodDAOimpl;
import cookingNotebook.models.FoodSP;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.LogicMenu;
import cookingNotebook.models.Menu;
import cookingNotebook.support.AlertDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuInfoViewController implements Initializable{

    @FXML
    private VBox MenuInfo;
    @FXML
     FlowPane foodList;
    @FXML
     Label m_name;
   @FXML
    public void setCheckBox(TilePane x, String fName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
        Parent updateMenu = loader.load();
        CreateMenuViewController controller = loader.getController();
        for (int i = 0; i < x.getChildren().size(); i++) {
            Pane pane = (Pane) x.getChildren().get(i);
            VBox vb = (VBox) pane.getChildren().get(0);
            Text txt_name = (Text) vb.getChildren().get(1);
            if (txt_name.getText().equals(fName)) {
                CheckBox cb = (CheckBox) pane.getChildren().get(1);
                cb.selectedProperty().set(true);
            }
        }
    }

    public void SuaThucDon(ActionEvent e) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
        Parent updateMenu = loader.load();
        CreateMenuViewController controller = loader.getController();
        controller.but_tao_td.setText("Cancel");
        controller.but_tao_td.setOnAction(eh->{
            try {
                controller.goBack(eh);
            } catch (IOException ex) { }
        });
        controller.sua_menu.setVisible(true);
        controller.menu_name.setText(LogicMenu.getMenuName());
        FunctionsController.pushRoot(MenuInfo);
        MenuInfo.getScene().setRoot(updateMenu);
        Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
        m_fImpl.getAllFoodName(LogicMenu.getMenuId()).forEach(f_name -> {
            FoodDAOImpl fImpl = new FoodDAOImpl();
            LogicFood f = null;
            try {
                f = fImpl.getFood(f_name);
            } catch (SQLException ex) {
            }
            Text name = new Text();
            name.setLayoutX(45);
            name.setText("- " + f.getName());
            name.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20;-fx-fill:white");
            Pane p = new Pane();
            Label lb = new Label();
            Line line = new Line();
            lb.setText("⚫ " + f.getCategory());
            lb.setStyle("-fx-text-fill:white; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
            line.setStroke(new Color(0.4196, 0.4196, 0.4196, 1.0));
            line.setLayoutX(127);
            line.setLayoutY(32);
            line.setStartX(-110);
            line.setStartY(2);
            line.setEndX(-110);
            line.setEndY(0);
            if (controller.foodChoosed.getChildren().isEmpty()) {
                line.setEndY(20);
                name.setLayoutY(50);
                p.getChildren().add(lb);
                p.getChildren().add(line);
                p.getChildren().add(name);
                controller.foodChoosed.getChildren().add(p);
                controller.titledPane.setExpanded(true);
            } else {
                int a = 0;
                for (int i = 0; i < controller.foodChoosed.getChildren().size(); i++) {
                    Pane pane_choice = (Pane) controller.foodChoosed.getChildren().get(i);
                    Label lb_ = (Label) pane_choice.getChildren().get(0);
                    if (lb_.getText().substring(2).equals(f.getCategory())) {
                        a = 1;
                        double pre_Y = ((Text) pane_choice.getChildren().get(pane_choice.getChildren().size() - 1)).getLayoutY();
                        name.setLayoutY((pre_Y + 30));
                        Line l = (Line) pane_choice.getChildren().get(1);
                        pane_choice.getChildren().add(name);
                        l.setEndY((pane_choice.getHeight()));
                    }
                }
                if (a == 0) {
                    name.setLayoutY(50);
                    p.getChildren().add(lb);
                    p.getChildren().add(line);
                    p.getChildren().add(name);
                    controller.foodChoosed.getChildren().add(p);
                }
            }
            String category = f.getCategory();
            switch (category) {
                case "MÓN ĂN SÁNG" -> {
                    try {
                        setCheckBox(controller.Vegetable, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN CHÍNH" -> {
                    try {
                        setCheckBox(controller.Meat, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN PHỤ" -> {
                    try {
                        setCheckBox(controller.Soup, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN RAU" -> {
                    try {
                        setCheckBox(controller.Fish, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN CANH" -> {
                    try {
                        setCheckBox(controller.Holiday, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN ĂN KÈM" -> {
                    try {
                        setCheckBox(controller.Juice, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN ĂN ĐÊM" -> {
                    try {
                        setCheckBox(controller.Cake, f_name);
                    } catch (IOException ex) {
                    }
                }

                case "MÓN ĂN VẶT" -> {
                    try {
                        setCheckBox(controller.RiceGruel, f_name);
                    } catch (IOException ex) {
                    }
                }

            }
        });
    }
      
    @FXML
      public void goBack(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
        Parent menu_view = loader.load();
        FunctionsController.popRoot();
        MenuInfo.getScene().setRoot(menu_view);
    }
    public void XemMonAn(ActionEvent e) throws IOException, SQLException {
        Pane pane = (Pane)((Button)e.getSource()).getParent();
        Label lb = (Label) pane.getChildren().get(0);
        int index = Integer.parseInt(lb.getText().substring(0,1));
        Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
        String food_name = m_fImpl.getAllFoodName(LogicMenu.getMenuId()).get(index-1);
        FoodSP.setName(food_name);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
        Parent addFoodView = loader.load();
        FoodViewController c = loader.getController();
        c.del_btn.setVisible(false);
        c.update_btn.setVisible(false);
        c.back_btn.setOnAction(eh->{
            try {
                c.goBack(eh);
            } catch (IOException ex) {}
        });
        FunctionsController.pushRoot(MenuInfo);
        MenuInfo.getScene().setRoot(addFoodView);
        
    }

    @FXML
    private void DeleteMenu(ActionEvent event) throws SQLException, IOException {
           MenuDAOImpl mImpl = new MenuDAOImpl();
           Menu t = mImpl.get(m_name.getText());
           mImpl.delete(t);
           AlertDialog.display("Xóa thực đơn", "Xóa thực đơn thành công");
           FunctionsController.popRoot();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
           Parent menuView = loader.load();
           MenuInfo.getScene().setRoot(menuView);
    }
    LogicMenu food;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        m_name.setText(LogicMenu.getMenuName());
        Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
        try {
            m_fImpl.getAllFoodName(LogicMenu.getMenuId()).forEach(name->{
                Pane pane = new Pane();
                foodList.getChildren().add(pane);
                pane.setPrefWidth(667);
                
                Label lb = new Label();
                lb.setText(foodList.getChildren().size() + "." + name + "........................................................................................................");
                lb.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;-fx-text-fill:#0c3b86;"
                        + "-fx-font-style:Italic");
                lb.setPrefWidth(407);
                lb.setPrefHeight(25);
                lb.setLayoutX(55);
                lb.setLayoutY(19);
                
                Button btn = new Button();
                btn.setText("Xem công thức");
                btn.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18;"
                        + "-fx-background-color:white;-fx-border-color:green;-fx-border-radius:20;-fx-background-radius:20");
                btn.setLayoutX(473);
                btn.setLayoutY(15);
                btn.setCursor(Cursor.HAND);
                btn.setOnAction(eh -> {
                    try {
                        XemMonAn(eh);
                    } catch (IOException | SQLException ex) {}
                });
                
                pane.getChildren().add(lb);
                pane.getChildren().add(btn);
            });
        } catch (SQLException ex) { }
    }
      
    
}