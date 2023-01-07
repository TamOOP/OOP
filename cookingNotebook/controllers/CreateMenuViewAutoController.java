/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.MenuDAOImpl;
import cookingNotebook.DAO.Menu_FoodDAOimpl;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Food;
import cookingNotebook.support.AlertDialog;
import cookingNotebook.support.TextFieldValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CreateMenuViewAutoController implements Initializable {

    @FXML
    private VBox morning_vbox;
    @FXML
    private VBox noon_vbox;
    @FXML
    private VBox night_vbox;
    @FXML
    private AnchorPane AutoMenu;
    @FXML
    private TextField txt_name;
    @FXML
    private Label error_name;
    @FXML
    private VBox container_vbox;

    public String capitalize(String str){
        String newString =  Character.toString(str.charAt(0)).toUpperCase() 
                           + str.substring(1,str.length());
        return newString;
    }
    public LogicFood random(String category) throws SQLException{
        List<LogicFood> l = FoodDAOImpl.getIns().getAll(category);
        LogicFood f = null;
	Random rd = new Random();
        if(!l.isEmpty()){
            int x = rd.nextInt(l.size());
            f = l.get(x);
        }
        return f;
    }
    public void append(LogicFood food, VBox timeInday, String category){
        if(food == null){
            timeInday.getChildren().add(alertNull("Không có món thuộc loại "+
                                                capitalize(category.toLowerCase())));
        }
        else{
            Pane pane = new Pane();
            ImageView img = new ImageView();
            Label name = new Label();
            Label category_lb = new Label();

            pane.setPrefHeight(70);

            img.setPreserveRatio(false);
            img.setFitWidth(80);
            img.setFitHeight(55);
            img.setLayoutX(36);
            img.setLayoutY(8);
            img.setImage(food.convertImg());

            name.setText(food.getName());
            name.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
            name.setPrefWidth(480);
            name.setPrefHeight(28);
            name.setLayoutX(158);
            name.setLayoutY(10);

            category_lb.setText(capitalize(food.getCategory().toLowerCase()));
            category_lb.setStyle("-fx-font-size: 15");
            category_lb.setPrefWidth(480);
            category_lb.setPrefHeight(28);
            category_lb.setLayoutX(158);
            category_lb.setLayoutY(38);
                
            MenuButton menu_but = createMenuButton(food);
            FileInputStream inputstream = null;
            try {
                 inputstream = new FileInputStream("./src/resource/rotate-left.png");
                ImageView random_ico = new ImageView();
                random_ico.setPreserveRatio(false);
                random_ico.setFitWidth(22);
                random_ico.setFitHeight(22);
                random_ico.setImage(new Image(inputstream));
                AnchorPane random = new AnchorPane();
                random.setLayoutX(719);
                random.setLayoutY(24);
                random.setPrefWidth(22);
                random.setPrefHeight(22);
                random.setCursor(Cursor.HAND);
                random.setOnMouseClicked(eh -> {
                    try {
                        change(food, eh);
                    } catch (SQLException ex) {
                    }
                });
                random.getChildren().add(random_ico);
                pane.getChildren().add(random);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CreateMenuViewAutoController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                    inputstream = new FileInputStream("./src/resource/trash.png");
                ImageView delete_ico = new ImageView(new Image(inputstream));
                delete_ico.setPreserveRatio(false);
                delete_ico.setFitWidth(20);
                delete_ico.setFitHeight(22);
                AnchorPane delete = new AnchorPane();
                delete.setLayoutX(762);
                delete.setLayoutY(24);
                delete.setPrefWidth(21);
                delete.setPrefHeight(22);
                delete.setCursor(Cursor.HAND);
                delete.setOnMouseClicked(eh -> {
                    hide(food, eh);
                });
                delete.getChildren().add(delete_ico);
                pane.getChildren().add(delete);
                
            }catch (FileNotFoundException ex) {pane.getChildren().add(menu_but);}
            
            
            pane.getChildren().add(img);
            pane.getChildren().add(name);
            pane.getChildren().add(category_lb);
            timeInday.getChildren().add(pane);
        }
        
    }
    public void append(LogicFood food, VBox timeInday, int index){
        Pane pane = new Pane();
        ImageView img = new ImageView();
        Label name = new Label();
        Label category = new Label();
        
        pane.setPrefHeight(70);
        
        img.setPreserveRatio(false);
        img.setFitWidth(80);
        img.setFitHeight(55);
        img.setLayoutX(36);
        img.setLayoutY(8);
        img.setImage(food.convertImg());
        
        name.setText(food.getName());
        name.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
        name.setPrefWidth(480);
        name.setPrefHeight(28);
        name.setLayoutX(158);
        name.setLayoutY(10);
        
        category.setText(capitalize(food.getCategory().toLowerCase()));
        category.setStyle("-fx-font-size: 15");
        category.setPrefWidth(480);
        category.setPrefHeight(28);
        category.setLayoutX(158);
        category.setLayoutY(38);
        
        MenuButton menu_but = createMenuButton(food);
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("./src/resource/rotate-left.png");
            ImageView random_ico = new ImageView();
            random_ico.setPreserveRatio(false);
            random_ico.setFitWidth(22);
            random_ico.setFitHeight(22);
            random_ico.setImage(new Image(inputstream));
            AnchorPane random = new AnchorPane();
            random.setLayoutX(719);
            random.setLayoutY(24);
            random.setPrefWidth(22);
            random.setPrefHeight(22);
            random.setCursor(Cursor.HAND);
            random.setOnMouseClicked(eh -> {
                try {
                    change(food, eh);
                } catch (SQLException ex) {
                }
            });
            random.getChildren().add(random_ico);
            pane.getChildren().add(random);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateMenuViewAutoController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            inputstream = new FileInputStream("./src/resource/trash.png");
            ImageView delete_ico = new ImageView(new Image(inputstream));
            delete_ico.setPreserveRatio(false);
            delete_ico.setFitWidth(20);
            delete_ico.setFitHeight(22);
            AnchorPane delete = new AnchorPane();
            delete.setLayoutX(762);
            delete.setLayoutY(24);
            delete.setPrefWidth(21);
            delete.setPrefHeight(22);
            delete.setCursor(Cursor.HAND);
            delete.setOnMouseClicked(eh -> {
                hide(food, eh);
            });
            delete.getChildren().add(delete_ico);
            pane.getChildren().add(delete);

        } catch (FileNotFoundException ex) {
            pane.getChildren().add(menu_but);
        }

        pane.getChildren().add(img);
        pane.getChildren().add(name);
        pane.getChildren().add(category);
        timeInday.getChildren().add(index, pane);
    }
    public void change(LogicFood food, ActionEvent eh) throws SQLException {
        List<LogicFood> l = FoodDAOImpl.getIns().getAll(food.getCategory());
        LogicFood f = null;
        if(l.size() > 1){
            do{
                f = random(food.getCategory());
            }  
            while(f.getId() == food.getId()); 
        }else{
            f = food;
        }
        
        ContextMenu ctm = (ContextMenu)((MenuItem) eh.getSource()).getParentPopup();
        Pane pane = (Pane) ((MenuButton)( ctm.getOwnerNode())).getParent();
        VBox tid = (VBox) pane.getParent();
        int i = tid.getChildren().indexOf(pane);
        ctm.hide();
        tid.getChildren().remove(i);
        append(f, tid,i);
        
    }
    public void change(LogicFood food, MouseEvent eh) throws SQLException{
        List<LogicFood> l = FoodDAOImpl.getIns().getAll(food.getCategory());
        LogicFood f = null;
        if(l.size() > 1){
            do{
                f = random(food.getCategory());
            }  
            while(f.getId() == food.getId()); 
        }else{
            f = food;
        }
        Pane pane = (Pane) ((AnchorPane)(eh.getSource())).getParent();
        VBox tid = (VBox) pane.getParent();
        int i = tid.getChildren().indexOf(pane);
        tid.getChildren().remove(i);
        append(f, tid,i);
    }
    public void hide(LogicFood food, ActionEvent eh){
        String category = food.getCategory();
        ContextMenu ctm = (ContextMenu)((MenuItem) eh.getSource()).getParentPopup();
        Pane old_pane = (Pane) ((MenuButton)( ctm.getOwnerNode())).getParent();
        VBox tid = (VBox) old_pane.getParent();
        int i = tid.getChildren().indexOf(old_pane);
        ctm.hide();
        tid.getChildren().remove(i);
        
        Pane new_pane = new Pane();
        Pane container = new Pane();
        new_pane.setPrefWidth(570);
        new_pane.setPrefHeight(70);
        container.setCursor(Cursor.HAND);
        container.setPrefWidth(180);
        container.setPrefHeight(28);
        container.setLayoutX(45);
        container.setLayoutY(21);
        
        Label plus = new Label();
        Label txt = new Label();
        plus.setText(" +");
        plus.setPrefWidth(44);
        plus.setPrefHeight(53);
        plus.setLayoutY(-12);
        Color paint = new Color(0.0314, 0.4941, 1.0, 1.0);
        plus.setTextFill(paint);
        plus.setStyle("-fx-font-size: 36px");
        txt.setText("Thêm " + capitalize(category.toLowerCase()));
        txt.setTextFill(paint);
        txt.setStyle("-fx-font-size: 18px");
        txt.setPrefWidth(160);
        txt.setPrefHeight(27);
        txt.setLayoutX(55);
        txt.setLayoutY(4);
        
        container.getChildren().add(plus);
        container.getChildren().add(txt);
        container.setOnMouseClicked(e->{
            try {
                show(new_pane,category);
            } catch (SQLException ex) {}
        });
        
        new_pane.getChildren().add(container);
        tid.getChildren().add(i, new_pane);
    }
    public void hide(LogicFood food, MouseEvent eh){
        String category = food.getCategory();
        Pane old_pane = (Pane) ((AnchorPane)(eh.getSource())).getParent();
        VBox tid = (VBox) old_pane.getParent();
        int i = tid.getChildren().indexOf(old_pane);
        tid.getChildren().remove(i);
        
        Pane new_pane = new Pane();
        AnchorPane container = new AnchorPane();
        new_pane.setPrefWidth(570);
        new_pane.setPrefHeight(70);
        container.setCursor(Cursor.HAND);
        container.setPrefWidth(180);
        container.setPrefHeight(28);
        container.setLayoutX(45);
        container.setLayoutY(21);
        
        Label plus = new Label();
        Label txt = new Label();
        plus.setText(" +");
        plus.setPrefWidth(44);
        plus.setPrefHeight(53);
        plus.setLayoutY(-12);
        Color paint = new Color(0.0314, 0.4941, 1.0, 1.0);
        plus.setTextFill(paint);
        plus.setStyle("-fx-font-size: 36px");
        txt.setText("Thêm " + capitalize(category.toLowerCase()));
        txt.setTextFill(paint);
        txt.setStyle("-fx-font-size: 18px");
        txt.setPrefWidth(160);
        txt.setPrefHeight(27);
        txt.setLayoutX(55);
        txt.setLayoutY(4);
        
        container.getChildren().add(plus);
        container.getChildren().add(txt);
        container.setOnMouseClicked(e->{
            try {
                show(new_pane,category);
            } catch (SQLException ex) {}
        });
        
        new_pane.getChildren().add(container);
        tid.getChildren().add(i, new_pane);
    }
    public void show(Pane pane, String category) throws SQLException{
        VBox tid = (VBox) pane.getParent();
        int i = tid.getChildren().indexOf(pane);
        tid.getChildren().remove(i);
        LogicFood f = random(category);
        append(f, tid, i);
    }
    public Pane alertNull(String message){
        Pane pane = new Pane();
        Label lb = new Label();
        
        pane.setPrefHeight(70);
        
        lb.setText(message);
        lb.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        lb.setTextFill(new Color(0.4745, 0.4745, 0.4745, 1.0));
        lb.setLayoutX(37);
        lb.setLayoutY(25);
        
        pane.getChildren().add(lb);
        
        return pane;
    }
    public MenuButton createMenuButton(LogicFood food){
        Pane three_dot = new Pane();
        
        MenuItem random = new MenuItem();
        Pane random_grap_pane = new Pane();
        Label random_grap_lb = new Label();
        
        MenuItem del = new MenuItem();
        Pane del_grap_pane = new Pane();
        Label del_grap_lb = new Label();
        
        Circle c1 = new Circle(3);
        Circle c2 = new Circle(3);
        Circle c3 = new Circle(3);
        c1.setLayoutY(10);
        c1.setFill(new Color(0.1176, 0.5647, 1.0, 1.0));
        c2.setLayoutY(10);
        c2.setLayoutX(-10);
        c2.setFill(new Color(0.1176, 0.5647, 1.0, 1.0));
        c3.setLayoutY(10);
        c3.setLayoutX(10);
        c3.setFill(new Color(0.1176, 0.5647, 1.0, 1.0));
        three_dot.setStyle("-fx-background-color: white");
        three_dot.setPrefWidth(126);
        three_dot.setPrefHeight(16);
        three_dot.setCursor(Cursor.HAND);
        three_dot.getChildren().add(c1);
        three_dot.getChildren().add(c2);
        three_dot.getChildren().add(c3);
        
        random_grap_pane.setPrefWidth(156);
        random_grap_pane.setPrefHeight(28);
        random_grap_lb.setText("Ngẫu nhiên lại");
        random_grap_lb.setLayoutX(14);
        random_grap_lb.setLayoutY(4);
        random_grap_lb.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18;");
        random_grap_pane.getChildren().add(random_grap_lb);
        random.setGraphic(random_grap_pane);
        random.setOnAction(eh->{
            try {
                change(food, eh);
            } catch (SQLException ex) {}
        });
        
        del_grap_pane.setPrefWidth(156);
        del_grap_pane.setPrefHeight(28);
        del_grap_lb.setText("Xóa món");
        del_grap_lb.setLayoutX(14);
        del_grap_lb.setLayoutY(4);
        del_grap_lb.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18;");
        del_grap_pane.getChildren().add(del_grap_lb);
        del.setGraphic(del_grap_pane);
        del.setOnAction(eh->{
            hide(food,eh);
        });
        MenuButton but = new MenuButton(null, null,random,del);
        but.setGraphic(three_dot);
        but.setLayoutX(718);
        but.setLayoutY(28);
        but.setPrefWidth(0);
        but.setPrefHeight(0);
        but.setPopupSide(Side.LEFT);
        return but;
        
    }
    public boolean isNameExist() throws SQLException{
        boolean b = false;
        MenuDAOImpl mImpl = new MenuDAOImpl();
        List<Menu> menus = mImpl.getAll();
        for(int i=0; i<menus.size(); i++){
            if(txt_name.getText().equalsIgnoreCase(menus.get(i).getMname())){
                b=true;
                error_name.setText("Tên thực đơn đã tồn tại");
            }
        }
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name) && !b){
            error_name.setText(null);
        }
        return b;
    }
    
    @FXML
    private void save(ActionEvent event) throws SQLException, IOException {
        MenuDAOImpl mImpl = new MenuDAOImpl();
        boolean b = isNameExist();
        TextFieldValidation.isTextFieldNotEmpty(txt_name, error_name
                , "Hãy nhập tên thực đơn");
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            if (!b) {
                int count = 0;
                for (int i = 1; i < container_vbox.getChildren().size(); i++) {
                    TilePane tp = (TilePane) container_vbox.getChildren().get(i);
                    TitledPane tit_pane = (TitledPane) tp.getChildren().get(0);
                    Pane grap = (Pane) tit_pane.getGraphic();
                    VBox food_list_vb = (VBox) tit_pane.getContent();
                    for (int j = 1; j < food_list_vb.getChildren().size(); j++) {
                        Pane food_pane = (Pane) food_list_vb.getChildren().get(j);
                        if (food_pane.getChildren().size() == 1) {
                            count++;
                        }
                    }
                }
                if(count != 9){
                    Menu m = new Menu(txt_name.getText());
                    mImpl.save(m);
                    m = mImpl.get(txt_name.getText());
                    Menu_FoodDAOimpl mfImpl = new Menu_FoodDAOimpl();
                    for (int i = 1; i < container_vbox.getChildren().size(); i++) {
                        TilePane tp = (TilePane) container_vbox.getChildren().get(i);
                        TitledPane tit_pane = (TitledPane) tp.getChildren().get(0);
                        Pane grap = (Pane) tit_pane.getGraphic();
                        String tid = ((Text) grap.getChildren().get(0)).getText();
                        VBox food_list_vb = (VBox) tit_pane.getContent();
                        for (int j = 1; j < food_list_vb.getChildren().size(); j++) {
                            Pane food_pane = (Pane) food_list_vb.getChildren().get(j);
                            if (food_pane.getChildren().size() > 1) {
                                Label name_lb = (Label) food_pane.getChildren().get(3);
                                String food_name = name_lb.getText();
                                FoodDAOImpl fImpl = new FoodDAOImpl();
                                List<LogicFood> foods = fImpl.getAll();
                                for (int k = 0; k < foods.size(); k++) {
                                    LogicFood food = foods.get(k);
                                    if (food.getName().equals(food_name)) {
                                        Menu_Food mf = new Menu_Food(m.getMid(), food.getId(), tid);
                                        mfImpl.insert(mf);
                                    }
                                }
                            }
                        }
                    }
                    error_name.setText(null);
                    AlertDialog.display("Tạo thực đơn", "Tạo thành công");
                    FunctionsController.popRoot();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
                    Parent MenuView = loader.load();
                    AutoMenu.getScene().setRoot(MenuView);
                }else{
                    AlertDialog.display("Thất bại", "Chọn ít nhất 1 món ăn");
                }
            }else{
                AlertDialog.display("Thất bại", "Tên thực đơn đã tồn tại. Hãy chọn tên khác");
            }
        }else{
            AlertDialog.display("Thất bại", "Hãy nhập tên thực đơn");
        }
        
    }
    
    @FXML
    private void checkName(KeyEvent event) throws SQLException {
        boolean b = isNameExist();
    }

    @FXML
    private void checkName1(MouseEvent event) throws SQLException {
        boolean b = isNameExist();
    }
    @FXML
    private void goBack(ActionEvent event) throws SQLException {
        AutoMenu.getScene().setRoot(FunctionsController.popRoot());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LogicFood f = random("MÓN ĂN SÁNG");
            if(f != null){
                append(f, morning_vbox,"MÓN ĂN SÁNG");
            }
            f = random("MÓN CHÍNH");
            append(f, noon_vbox,"MÓN CHÍNH");
            f = random("MÓN PHỤ");
            append(f, noon_vbox,"MÓN PHỤ");
            f = random("MÓN RAU");
            append(f, noon_vbox,"MÓN RAU");
            f = random("MÓN ĂN KÈM");
            append(f, noon_vbox,"MÓN ĂN KÈM");
            
            f = random("MÓN CHÍNH");
            append(f, night_vbox,"MÓN CHÍNH");
            f = random("MÓN PHỤ");
            append(f, night_vbox,"MÓN PHỤ");
            f = random("MÓN RAU");
            append(f, night_vbox,"MÓN RAU");
            f = random("MÓN CANH");
            append(f, night_vbox,"MÓN CANH");
            
        } catch (SQLException ex) { }
    }  
}
