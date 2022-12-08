/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.MenuDAOImpl;
import cookingNotebook.models.Menu;
import cookingNotebook.models.LogicMenu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class MenuViewController implements Initializable{
    ContextMenu contextMenu = new ContextMenu();
    @FXML
    private VBox MenuView;
    @FXML
    private Pane alert;
    @FXML
    private Pane head;
    @FXML
    private TilePane MenuList;
    public List<String> f_name = new ArrayList<String>();
    @FXML public void goBack(ActionEvent e) throws IOException{
        MenuView.getScene().setRoot(FunctionsController.popRoot());
    }
    
    public void TaoThuCong(ActionEvent e) throws IOException{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
            Parent addFoodView = loader.load();
            CreateMenuViewController controller = loader.getController();
            controller.sua_menu.setVisible(false);
            controller.but_tao_td.setVisible(true);
            
            FunctionsController.pushRoot(MenuView);
            MenuView.getScene().setRoot(addFoodView);
    }
    public void MenuInfo( Menu m) throws IOException, SQLException{
        LogicMenu.setMenuId(m.getMid());
        LogicMenu.setMenuName(m.getMname());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
        Parent MInfo = loader.load();
        FunctionsController.pushRoot(MenuView);
        MenuView.getScene().setRoot(MInfo);
        
    }

    @FXML
    private void showOption(MouseEvent event) {
        
        Label lb = new Label();
        lb.setLayoutX(701);
        lb.setLayoutY(47);
        lb.setVisible(false);
        lb.setContextMenu(contextMenu);
        head.getChildren().add(lb);
        contextMenu.show(lb,950,193);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Label lbl = new Label("Thêm mới thủ công");
        Label lbl1 = new Label("Thêm mới tự động");
        lbl.setPrefWidth(250);
        lbl.setPrefHeight(30);
        lbl.setCursor(Cursor.HAND);
        lbl.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16;-fx-border-radius:5");
        lbl1.setPrefWidth(250);
        lbl1.setPrefHeight(30);
        lbl1.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 16;-fx-border-radius:5");
        lbl1.setCursor(Cursor.HAND);
        MenuItem menuItem1 = new MenuItem();
        MenuItem menuItem2 = new MenuItem();
        menuItem1.setGraphic(lbl);
        menuItem1.setOnAction(eh->{
            try {
                TaoThuCong(eh);
            } catch (IOException ex) {
                }
        });
        menuItem2.setGraphic(lbl1);
        contextMenu.getItems().add(menuItem1);
        contextMenu.getItems().add(menuItem2);
        
        MenuDAOImpl mImpl = new MenuDAOImpl();
        try {
            if(!mImpl.getAll().isEmpty()){              
                    mImpl.getAll().forEach(m -> {
                        Pane pane = new Pane();
                        pane.setStyle("-fx-border-color: rgba(224, 224, 224, 1);");
                        pane.setPrefWidth(915);
                        pane.setPrefHeight(46);
                        pane.setCursor(Cursor.HAND);
                        pane.setOnMouseClicked(eh -> {
                            try {
                                MenuInfo( m);
                            } catch (IOException ex) {
                            } catch (SQLException ex) {
                            }
                           
                        });
                        Text txt_Mname = new Text();
                        txt_Mname.setText(m.getMname());
                        txt_Mname.setLayoutX(109);
                        txt_Mname.setLayoutY(30);
                        Button btn = new Button();
                        btn.setStyle("-fx-font-size:18px;-fx-font-weight:Bold;-fx-text-fill:white;-fx-background-color:red");
                        btn.setPadding(new Insets(-8,0,-7,0));
                        btn.setPrefWidth(26);
                        btn.setPrefHeight(20);
                        btn.setLayoutX(14);
                        btn.setLayoutY(13);
                        btn.setText("-");
                        pane.getChildren().add(txt_Mname);
                        pane.getChildren().add(btn);
                        MenuList.getChildren().add(1, pane);
                    });
                }else{
                    alert.setVisible(true);
                }
            }
        catch (SQLException ex) {
        }
        
    }
}