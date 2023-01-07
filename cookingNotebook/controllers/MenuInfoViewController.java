/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodAndIngredientDAOImpl;
import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.IngredientDAOImpl;
import cookingNotebook.DAO.MenuDAOImpl;
import cookingNotebook.DAO.Menu_FoodDAOimpl;
import cookingNotebook.models.FoodAndIngredient;
import cookingNotebook.support.FoodSP;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.LogicMenu;
import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Food;
import cookingNotebook.models.Menu_Ingredient;
import cookingNotebook.support.AlertDialog;
import cookingNotebook.support.MenuSP;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class MenuInfoViewController implements Initializable{
    DecimalFormat format = new DecimalFormat();
    @FXML
    private VBox MenuInfo;
    @FXML
    private Label name;
    @FXML
    private VBox container_vbox;
    @FXML
    private VBox morning_vbox;
    @FXML
    private VBox noon_vbox;
    @FXML
    private VBox night_vbox;
    @FXML
    private VBox ingr_list;
    boolean state = false;
    
    public String capitalize(String str){
        String newString =  Character.toString(str.charAt(0)).toUpperCase() 
                           + str.substring(1,str.length());
        return newString;
    }
    public void append(LogicFood food, VBox timeInday){
        Pane pane = new Pane();
        ImageView img = new ImageView();
        Label name1 = new Label();
        Label category = new Label();
        
        pane.setPrefWidth(570);
        pane.setPrefHeight(70);
        pane.setCursor(Cursor.HAND);
        pane.setOnMouseClicked(eh->{
            try {
                XemMonAn(eh);
            } catch (IOException | SQLException ex) {}
        });
        img.setPreserveRatio(false);
        img.setFitWidth(80);
        img.setFitHeight(55);
        img.setLayoutX(36);
        img.setLayoutY(8);
        img.setImage(food.convertImg());
        
        name1.setText(food.getName());
        name1.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
        name1.setPrefWidth(480);
        name1.setPrefHeight(28);
        name1.setLayoutX(158);
        name1.setLayoutY(10);
        
        category.setText(capitalize(food.getCategory().toLowerCase()));
        category.setStyle("-fx-font-size: 15");
        category.setPrefWidth(480);
        category.setPrefHeight(28);
        category.setLayoutX(158);
        category.setLayoutY(38);
        
        
        
        pane.getChildren().add(img);
        pane.getChildren().add(name1);
        pane.getChildren().add(category);
        timeInday.getChildren().add(pane);
    }
    public TilePane alertNull(String message){
        TilePane tp = new TilePane();
        Label lb = new Label();
        tp.setAlignment(Pos.CENTER);
        tp.setPrefTileHeight(45);
        tp.setPadding(new Insets(20, 0, 20, 0));
        
        lb.setText(message);
        lb.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
        lb.setTextFill(new Color(0.4745, 0.4745, 0.4745, 1.0));
        
        tp.getChildren().add(lb);
        
        return tp;
    }
    public void exportTotalIngr(String tid, List<Menu_Food> mfs) throws SQLException{
        FoodAndIngredientDAOImpl fiImpl = new FoodAndIngredientDAOImpl();
        IngredientDAOImpl ingrImpl = new IngredientDAOImpl();
        int menuId = mfs.get(0).getMid();
        Menu_Ingredient mis = new Menu_Ingredient();
        mis.setMid(menuId);
        mfs.forEach(mf ->{
            List<FoodAndIngredient> fis=null;
            try {
                fis = fiImpl.getFis(mf.getFid());
                fis.forEach(fi->{
                    try {
                        String ingrName = ingrImpl.get(fi.getIdIngredient()).getName();
                        if (mis.getIid().isEmpty()) {
                            mis.getIid().add(fi.getIdIngredient());
                            mis.getIname().add(ingrName);
                            mis.getIamount().add(format.format(fi.getAmount()).toString());
                            mis.getIunit().add(fi.getUnit());
                        } else {
                            boolean b = false;
                            for(int i =0; i<mis.getIid().size(); i++){
                                if(mis.getIname().get(i).equalsIgnoreCase(ingrName)){
                                    String unit_exist = mis.getIunit().get(i);
                                    String unit_new = fi.getUnit();
                                    if(unit_exist == null && unit_new != null){
                                        mis.getIunit().set(i, fi.getUnit());
                                        mis.getIamount().set(i, format.format(fi.getAmount()).toString());
                                    }
                                    if(unit_exist != null && unit_new != null && unit_exist.equalsIgnoreCase(unit_new)){
                                        mis.getIamount().set(i, mis.getIamount().get(i)+format.format(fi.getAmount()));
                                    }
                                    if(unit_exist != null && unit_new != null && !unit_exist.equalsIgnoreCase(unit_new)){
                                         mis.getIamount().set(i, mis.getIamount().get(i)+";"+format.format(fi.getAmount()).toString());
                                         mis.getIunit().set(i, unit_exist+";"+unit_new);
                                    }
                                            
                                    b = true;
                                }
                            }
                            
                            if(!b){
                                mis.getIid().add(fi.getIdIngredient());
                                mis.getIname().add(ingrName);
                                mis.getIamount().add(format.format(fi.getAmount()).toString());
                                mis.getIunit().add(fi.getUnit());
                            }
                        }
                    } catch (SQLException ex) {}
                });
            } catch (SQLException ex) {}
        });
                              
        Text tid_txt = new Text();
        tid_txt.setText(tid);
        tid_txt.setFont( Font.font("Times New Roman",FontWeight.BOLD,24));
        VBox.setMargin(tid_txt, new Insets(0, 0, 10, 0));
        
        TilePane tp = new TilePane();
        VBox.setMargin(tp, new Insets(0, 0, 30, 0));
        tp.setPrefHeight(45);
        tp.setHgap(40);
        tp.setVgap(5);
        tp.setPadding(new Insets(20, 0, 20, 50));
        tp.setStyle("-fx-border-color: #c1bebe;-fx-border-radius: 10");

        for (int i = 0; i < mis.getIid().size(); i++) {
            String[] a = null;
            String[] u = null;
            if(mis.getIunit().get(i) != null){
                a = mis.getIamount().get(i).split(";");
                u = mis.getIunit().get(i).split(";");
            }
            Text txt_ingr = new Text();
            if (u == null) {
                txt_ingr.setText("- " + mis.getIname().get(i));
            } else {
                String txt = "- " + mis.getIname().get(i) + ": " + a[0] + " " +  u[0];
                for(int j=1; j<a.length; j++){
                    txt += " và " + a[j] + " " + u[j];
                }
                txt_ingr.setText(txt);
            }
            txt_ingr.setFontSmoothingType(FontSmoothingType.LCD);
            txt_ingr.setWrappingWidth(200);
            txt_ingr.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
            
            TilePane.setMargin(txt_ingr, new Insets(5, 10, 5, 0));
            tp.getChildren().add(txt_ingr);
        }
        ingr_list.getChildren().add(tid_txt);
        ingr_list.getChildren().add(tp);
    }
    public void exportAllIngr(List<Menu_Food> mfs) throws SQLException{
        FoodAndIngredientDAOImpl fiImpl = new FoodAndIngredientDAOImpl();
        IngredientDAOImpl ingrImpl = new IngredientDAOImpl();
        int menuId = mfs.get(0).getMid();
        Menu_Ingredient mis = new Menu_Ingredient();
        mis.setMid(menuId);
        mfs.forEach(mf ->{
            List<FoodAndIngredient> fis=null;
            try {
                fis = fiImpl.getFis(mf.getFid());
                fis.forEach(fi->{
                    try {
                        String ingrName = ingrImpl.get(fi.getIdIngredient()).getName();
                        if (mis.getIid().isEmpty()) {
                            mis.getIid().add(fi.getIdIngredient());
                            mis.getIname().add(ingrName);
                            mis.getIamount().add(format.format(fi.getAmount()).toString());
                            mis.getIunit().add(fi.getUnit());
                        } 
                        else {
                            boolean b = false;
                            for(int i =0; i<mis.getIid().size(); i++){
                                if(mis.getIid().get(i).equals(fi.getIdIngredient())){
                                    String[] unit_exist = null;
                                    String[] amount_exist = null;
                                    if (mis.getIunit().get(i) != null) {
                                        amount_exist = mis.getIamount().get(i).split(";");
                                        unit_exist = mis.getIunit().get(i).split(";");
                                    }
                                    String unit_new = fi.getUnit();
                                    if(unit_exist == null){
                                        if(unit_new != null){
                                            mis.getIamount().set(i, format.format(fi.getAmount()).toString());
                                            mis.getIunit().set(i, unit_new);
                                        }
                                    }
                                    else{
                                        for (int j = 0; j < unit_exist.length; j++) {
                                            if (unit_new != null && unit_exist[j].equalsIgnoreCase(unit_new)) {
                                                double db = fi.getAmount() + Double.valueOf(amount_exist[j]);
                                                amount_exist[j] = format.format(db);
                                                String txtA = amount_exist[0];
                                                if (amount_exist.length > 1) {
                                                    for (int k = 1; k < amount_exist.length; k++) {
                                                        txtA += ";" + amount_exist[k];
                                                    }
                                                   
                                                }
                                                mis.getIamount().set(i, txtA);
                                                break;
                                            }
                                            if (unit_new != null && !unit_exist[j].equalsIgnoreCase(unit_new)) {
                                                mis.getIamount().set(i, mis.getIamount().get(i) + ";" + format.format(fi.getAmount()).toString());
                                                mis.getIunit().set(i, mis.getIunit().get(i) + ";" + unit_new);
                                            }
                                        }
                                    }
                                    b = true;
                                }
                            }
                            if(!b){
                                mis.getIid().add(fi.getIdIngredient());
                                mis.getIname().add(ingrName);
                                mis.getIamount().add(format.format(fi.getAmount()).toString());
                                mis.getIunit().add(fi.getUnit());
                            }
                        }
                    } catch (SQLException ex) {}
                });
            } catch (SQLException ex) {}
        });
        TilePane tp = new TilePane();
        VBox.setMargin(tp, new Insets(0, 0, 30, 0));
        tp.setHgap(40);
        tp.setVgap(5);
        tp.setPadding(new Insets(20, 0, 20, 50));
        tp.setStyle("-fx-border-color: #c1bebe;-fx-border-radius: 10");
        
        for (int i = 0; i < mis.getIid().size(); i++) {
            String[] a = null;
            String[] u = null;
            if(mis.getIunit().get(i) != null){
                a = mis.getIamount().get(i).split(";");
                u = mis.getIunit().get(i).split(";");
            }
            Text txt_ingr = new Text();
            if (u == null) {
                txt_ingr.setText("- " + mis.getIname().get(i));
            } else {
                String txt = "- " + mis.getIname().get(i) + ": " + a[0] + " " +  u[0];
                if(a.length>1){
                    for (int j = 1; j < a.length; j++) {
                        txt += " và " + a[j] + " " + u[j];
                    }  
                }
                txt_ingr.setText(txt);
            }
            txt_ingr.setFontSmoothingType(FontSmoothingType.LCD);
            txt_ingr.setWrappingWidth(200);
            txt_ingr.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 22;");
            
            TilePane.setMargin(txt_ingr, new Insets(5, 10, 5, 0));
            tp.getChildren().add(txt_ingr);
        }
        ingr_list.getChildren().add(tp);
    }
    public AnchorPane createToggleBut(){
        AnchorPane contain = new AnchorPane();
        Pane toggleBut = new Pane();
        Label lb = new Label();
        Circle c = new Circle(10);
        Rectangle r = new Rectangle();
        Color offCir = new Color(0.5255, 0.5216, 0.5216, 1.0);
        Color offRec = new Color(0.8, 0.8, 0.8, 1.0);
        
        VBox.setMargin(contain, new Insets(20,0,20,0));
        contain.setPrefHeight(20);
        
        c.setFill(offCir);
        c.setStroke(offCir);
        c.setLayoutX(9);
        c.setLayoutY(9);
        
        r.setFill(offRec);
        r.setStroke(offRec);
        r.setLayoutX(5);
        r.setLayoutY(3);
        r.setWidth(30);
        r.setHeight(12);
        
        toggleBut.setCursor(Cursor.HAND);
        toggleBut.setPrefWidth(41);
        toggleBut.setPrefHeight(18);
        toggleBut.setLayoutX(259);
        toggleBut.setLayoutY(2);
        toggleBut.getChildren().add(r);
        toggleBut.getChildren().add(c);
        toggleBut.setOnMouseClicked(eh->{
            toggle(eh,r,c);
        });
        
        lb.setText("Xem nguyên liệu mỗi bữa");
        lb.setFont(Font.font("Times New Roman",FontWeight.BOLD,18));
        lb.setLayoutX(26);
        lb.setLayoutY(1);
        
        contain.getChildren().add(toggleBut);
        contain.getChildren().add(lb);
        
        return contain;
    }
    
    
    @FXML
    public void goBack(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
        Parent menu_view = loader.load();
        FunctionsController.popRoot();
        MenuInfo.getScene().setRoot(menu_view);
    }
    public void XemMonAn(MouseEvent e) throws IOException, SQLException {
        Pane pane = (Pane)e.getSource();
        Label name_food_fb = (Label) pane.getChildren().get(1);
        String name_food = name_food_fb.getText();
        FoodSP.setName(name_food);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
        Parent addFoodView = loader.load();
        FoodViewController c = loader.getController();
        c.del_btn.setVisible(false);
        c.update_btn.setVisible(false);
        FunctionsController.pushRoot(MenuInfo);
        MenuInfo.getScene().setRoot(addFoodView);
        
    }
    @FXML
    private void SuaThucDon(ActionEvent event) throws IOException {
        MenuSP.setHandMenuState(true);
        MenuSP.getMorning().clear();
        MenuSP.getNight().clear();
        MenuSP.getNoon().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CreateMenuView.fxml"));
        Parent handMenuView = loader.load();
        
        FunctionsController.pushRoot(MenuInfo);
        MenuInfo.getScene().setRoot(handMenuView);
    }
    @FXML
    private void DeleteMenu(ActionEvent event) throws SQLException, IOException {
           MenuDAOImpl mImpl = new MenuDAOImpl();
           Menu t = mImpl.get(name.getText());
           mImpl.delete(t);
           AlertDialog.display("Xóa thực đơn", "Xóa thực đơn thành công");
           FunctionsController.popRoot();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
           Parent menuView = loader.load();
           MenuInfo.getScene().setRoot(menuView);
    }
    private void toggle(MouseEvent event,Rectangle rectangle, Circle circle) {
        state = !state;
        if(state){
            try {
                Color paint = new Color(0.1176, 0.5647, 1.0, 1.0);
                Color paint1 = new Color(0.502, 0.7608, 1.0, 1.0);
                circle.setFill(paint);
                circle.setStroke(paint);
                circle.setLayoutX(35);
                circle.setLayoutY(9);
                rectangle.setFill(paint1);
                rectangle.setStroke(paint1);
                Text morning = new Text();
                morning.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
                VBox.setMargin(morning, new Insets(0, 0, 10, 0));
                Text noon = new Text();
                noon.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
                VBox.setMargin(noon, new Insets(0, 0, 10, 0));
                Text night = new Text();
                night.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
                VBox.setMargin(night, new Insets(0, 0, 10, 0));
                
                TilePane morning_tp = alertNull("Không có nguyên liệu");
                morning_tp.setStyle("-fx-border-color: #c1bebe;-fx-border-radius: 10");
                VBox.setMargin(morning_tp, new Insets(0,0,30,0));
                TilePane noon_tp = alertNull("Không có nguyên liệu");
                noon_tp.setStyle("-fx-border-color: #c1bebe;-fx-border-radius: 10");
                VBox.setMargin(noon_tp, new Insets(0,0,30,0));
                TilePane night_tp = alertNull("Không có nguyên liệu");
                night_tp.setStyle("-fx-border-color: #c1bebe;-fx-border-radius: 10");
                VBox.setMargin(night_tp, new Insets(0,0,30,0));
                
                Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
                List<Menu_Food> mf_morning = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa sáng");
                List<Menu_Food> mf_noon = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa trưa");
                List<Menu_Food> mf_night = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa tối");
                FoodDAOImpl fImpl = new FoodDAOImpl();
                if (!mf_morning.isEmpty()) {
                    exportTotalIngr("Bữa sáng", mf_morning);
                } else {
                    morning.setText("Bữa sáng");
                    ingr_list.getChildren().add(morning);
                    ingr_list.getChildren().add(morning_tp);
                }
                if (!mf_noon.isEmpty()) {
                    exportTotalIngr("Bữa trưa", mf_noon);
                } else {
                    noon.setText("Bữa trưa");
                    ingr_list.getChildren().add(noon);
                    ingr_list.getChildren().add(noon_tp);
                }
                if (!mf_night.isEmpty()) {
                    exportTotalIngr("Bữa tối", mf_night);
                } else {
                    night.setText("Bữa tối");
                    ingr_list.getChildren().add(night);
                    ingr_list.getChildren().add(night_tp);
                }
            } catch (SQLException ex) { }
        }
        else{
            Color paint = new Color(0.5255, 0.5216, 0.5216, 1.0);
            Color paint1 = new Color(0.8, 0.8, 0.8, 1.0);
            circle.setFill(paint);
            circle.setStroke(paint);
            circle.setLayoutX(9);
            circle.setLayoutY(9);
            rectangle.setFill(paint1);
            rectangle.setStroke(paint1);
            ingr_list.getChildren().remove(3, 9);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setText(LogicMenu.getMenuName());
        Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
        try {
            List<Menu_Food> mf_morning = m_fImpl.getAll(LogicMenu.getMenuId(),"Bữa sáng");
            List<Menu_Food> mf_noon = m_fImpl.getAll(LogicMenu.getMenuId(),"Bữa trưa");
            List<Menu_Food> mf_night = m_fImpl.getAll(LogicMenu.getMenuId(),"Bữa tối");
            List<Menu_Food> mf_all = m_fImpl.getAll(LogicMenu.getMenuId());
            FoodDAOImpl fImpl = new FoodDAOImpl();
            if(!mf_morning.isEmpty()){
                for(int i = 0; i<mf_morning.size(); i++){
                    int id = mf_morning.get(i).getFid();
                    LogicFood food = fImpl.get(id);
                    append(food, morning_vbox);
                }
            }else{
                morning_vbox.getChildren().add(alertNull("Không có món cho bữa sáng"));
            }
            if(!mf_noon.isEmpty()){
                for(int i = 0; i<mf_noon.size(); i++){
                    int id = mf_noon.get(i).getFid();
                    LogicFood food = fImpl.get(id);
                    append(food, noon_vbox);
                }
            }else{
                noon_vbox.getChildren().add(alertNull("Không có món cho bữa trưa"));
            }
            if(!mf_night.isEmpty()){
                for(int i = 0; i<mf_night.size(); i++){
                    int id = mf_night.get(i).getFid();
                    LogicFood food = fImpl.get(id);
                    append(food, night_vbox);
                }
            }else{
                night_vbox.getChildren().add(alertNull("Không có món cho bữa tối"));
            }
            exportAllIngr(mf_all);
            ingr_list.getChildren().add(createToggleBut());
        } catch (SQLException ex) {
        }
    }

    

    
      
    
}