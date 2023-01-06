/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.MenuDAOImpl;
import cookingNotebook.DAO.Menu_FoodDAOimpl;
import cookingNotebook.models.LogicFood;
import cookingNotebook.models.LogicMenu;
import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Food;
import cookingNotebook.support.AlertDialog;
import cookingNotebook.support.MenuSP;
import cookingNotebook.support.TextFieldValidation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class CreateMenuViewController implements Initializable {
    boolean edit = false;
    @FXML
    private AnchorPane HandMenu;
    @FXML
    private VBox container_vbox;
    @FXML
    private TextField txt_name;
    @FXML
    private Label error_name;
    @FXML
    private VBox morning_vbox;
    @FXML
    private VBox noon_vbox;
    @FXML
    private VBox night_vbox;
    @FXML
    private TilePane menuName;
    @FXML
    private Label title;
    @FXML
    private Button but_action;
    public String capitalize(String str) {
        String newString = Character.toString(str.charAt(0)).toUpperCase()
                + str.substring(1, str.length());
        return newString;
    }
    public void append(LogicFood food, VBox timeInday){
        Pane pane = new Pane();
        ImageView img = new ImageView();
        Label name1 = new Label();
        Label category = new Label();
        
        pane.setPrefWidth(570);
        pane.setPrefHeight(70);
        
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
    public void clear(VBox tid){
        if(tid.getChildren().size()>1){
            tid.getChildren().remove(1,tid.getChildren().size());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (MenuSP.getHandMenuState()) {
            //view chỉnh sửa menu
            try {
                title.setText("CHỈNH SỬA THỰC ĐƠN");
                txt_name.setText(LogicMenu.getMenuName());
                but_action.setText("Lưu");
                but_action.setOnAction(eh->{
                    try {
                        update(eh);
                    } catch (SQLException | IOException ex) {}
                });
                if(MenuSP.isLoadDB()){
                    Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
                    List<Menu_Food> mf_morning = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa sáng");
                    List<Menu_Food> mf_noon = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa trưa");
                    List<Menu_Food> mf_night = m_fImpl.getAll(LogicMenu.getMenuId(), "Bữa tối");
                    List<Menu_Food> mf_all = m_fImpl.getAll(LogicMenu.getMenuId());
                    FoodDAOImpl fImpl = new FoodDAOImpl();
                    if (!mf_morning.isEmpty()) {
                        for (int i = 0; i < mf_morning.size(); i++) {
                            int id = mf_morning.get(i).getFid();
                            LogicFood food = fImpl.get(id);
                            append(food, morning_vbox);
                            MenuSP.getMorning().add(food);
                        }
                    } else {
                        morning_vbox.getChildren().add(alertNull("Không có món cho bữa sáng"));
                    }
                    if (!mf_noon.isEmpty()) {
                        for (int i = 0; i < mf_noon.size(); i++) {
                            int id = mf_noon.get(i).getFid();
                            LogicFood food = fImpl.get(id);
                            append(food, noon_vbox);
                            MenuSP.getNoon().add(food);
                        }
                    } else {
                        noon_vbox.getChildren().add(alertNull("Không có món cho bữa trưa"));
                    }
                    if (!mf_night.isEmpty()) {
                        for (int i = 0; i < mf_night.size(); i++) {
                            int id = mf_night.get(i).getFid();
                            LogicFood food = fImpl.get(id);
                            append(food, night_vbox);
                            MenuSP.getNight().add(food);
                        }
                    } else {
                        night_vbox.getChildren().add(alertNull("Không có món cho bữa tối"));
                    }
                }
            } catch (SQLException ex) {}
        } else {
            //view tạo menu
            txt_name.setText(LogicMenu.getMenuName());
            if (MenuSP.getMorning().isEmpty() && morning_vbox.getChildren().size()==1) {
                morning_vbox.getChildren().add(alertNull("Không có món cho bữa sáng"));
            }
            if (MenuSP.getNoon().isEmpty() && noon_vbox.getChildren().size()==1) {
                noon_vbox.getChildren().add(alertNull("Không có món cho bữa trưa"));
            }
            if (MenuSP.getNight().isEmpty()) {
                night_vbox.getChildren().add(alertNull("Không có món cho bữa tối"));
            }
        }
        if(!MenuSP.isLoadDB()){
            
            FoodDAOImpl fImpl = new FoodDAOImpl();
            if (!MenuSP.getMorning().isEmpty()) {
                for (int i = 0; i < MenuSP.getMorning().size(); i++) {
                    try {
                        int id = MenuSP.getMorning().get(i).getId();
                        LogicFood food = fImpl.get(id);
                        append(food, morning_vbox);
                    } catch (SQLException ex) {}
                }
            }else{
                if(morning_vbox.getChildren().size()==1){
                    morning_vbox.getChildren().add(alertNull("Không có món cho bữa sáng"));
                }
            }
            if (!MenuSP.getNoon().isEmpty()) {
                for (int i = 0; i < MenuSP.getNoon().size(); i++) {
                    try {
                        int id = MenuSP.getNoon().get(i).getId();
                        LogicFood food = fImpl.get(id);
                        append(food, noon_vbox);
                    } catch (SQLException ex) {}
                }
            }else{
                if(noon_vbox.getChildren().size()==1){
                    noon_vbox.getChildren().add(alertNull("Không có món cho bữa trưa"));
                }
            }
            if (!MenuSP.getNight().isEmpty()) {
                for (int i = 0; i < MenuSP.getNight().size(); i++) {
                    try {
                        int id = MenuSP.getNight().get(i).getId();
                        LogicFood food = fImpl.get(id);
                        append(food, night_vbox);
                    } catch (SQLException ex) {}
                }
            }else{
                if(night_vbox.getChildren().size()==1){
                    night_vbox.getChildren().add(alertNull("Không có món cho bữa tối"));
                }
            }
        }
       
    }
    @FXML
    private void save(ActionEvent event) throws SQLException, IOException {
        MenuSP.setLoadDB(true);
        MenuDAOImpl mImpl = new MenuDAOImpl();
        boolean nameExist = isNameExist();
        boolean hasFood = true;
        TextFieldValidation.isTextFieldNotEmpty(txt_name, error_name
                , "Hãy nhập tên thực đơn");
        if (MenuSP.getMorning().isEmpty()
                    && MenuSP.getNoon().isEmpty()
                    && MenuSP.getNight().isEmpty()) {
            AlertDialog.display("Thất bại", "Hãy chọn ít nhất 1 món ăn");
            hasFood = false;
        }
        if(!nameExist && hasFood && TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            Menu m = new Menu(txt_name.getText());
            mImpl.save(m);
            m = mImpl.get(txt_name.getText());
            Menu_FoodDAOimpl mfImpl = new Menu_FoodDAOimpl();
            for(int i=1; i<container_vbox.getChildren().size();i++){
                TilePane tp = (TilePane) container_vbox.getChildren().get(i);
                TitledPane tit_pane = (TitledPane) tp.getChildren().get(0);
                Pane grap = (Pane) tit_pane.getGraphic();
                String tid = ((Text)grap.getChildren().get(0)).getText();
                VBox food_list_vb = (VBox) tit_pane.getContent();
                for(int j=1; j<food_list_vb.getChildren().size(); j++){
                    Pane food_pane = (Pane) food_list_vb.getChildren().get(j);
                    if (food_pane.getChildren().size() > 1) {
                        Label name_lb = (Label) food_pane.getChildren().get(1);
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
            HandMenu.getScene().setRoot(MenuView);
        }
    }
    
    private void update(ActionEvent event) throws SQLException, IOException{
        Menu_FoodDAOimpl mfImpl = new Menu_FoodDAOimpl();
        boolean nameExist = isNameExist();
        boolean hasFood = true;
        TextFieldValidation.isTextFieldNotEmpty(txt_name, error_name
                , "Hãy nhập tên thực đơn");
        if (MenuSP.getMorning().isEmpty()
                    && MenuSP.getNoon().isEmpty()
                    && MenuSP.getNight().isEmpty()) {
            AlertDialog.display("Thất bại", "Hãy chọn ít nhất 1 món ăn");
            hasFood = false;
        }
        if(!nameExist && hasFood && TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            MenuDAOImpl mImpl = new MenuDAOImpl();
            Menu m = new Menu(LogicMenu.getMenuId(),txt_name.getText());
            mImpl.update(m);
            LogicMenu.setMenuName(txt_name.getText());
            mfImpl.deleteAllFood(LogicMenu.getMenuId());
            if (!MenuSP.getMorning().isEmpty()) {
                for (LogicFood f : MenuSP.getMorning()) {
                    mfImpl.insert(new Menu_Food(LogicMenu.getMenuId(),
                            f.getId(),
                            "Bữa sáng")
                    );
                }
            }
            if (!MenuSP.getNoon().isEmpty()) {
                for (LogicFood f : MenuSP.getNoon()) {
                    mfImpl.insert(new Menu_Food(LogicMenu.getMenuId(),
                            f.getId(),
                            "Bữa trưa")
                    );
                }
            }
            if (!MenuSP.getNight().isEmpty()) {
                for (LogicFood f : MenuSP.getNight()) {
                    mfImpl.insert(new Menu_Food(LogicMenu.getMenuId(),
                            f.getId(),
                            "Bữa tối")
                    );
                }
            }
            AlertDialog.display("Chỉnh sửa thành công", "Sửa thành công");
            FunctionsController.popRoot();
            MenuSP.setLoadDB(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
            Parent menu_info_view = loader.load();
            HandMenu.getScene().setRoot(menu_info_view);
        }
    }
    public boolean isNameExist() throws SQLException{
        boolean b = false;
        MenuDAOImpl mImpl = new MenuDAOImpl();
        List<Menu> menus = mImpl.getAll();
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            for (int i = 0; i < menus.size(); i++) {
                if (txt_name.getText().equalsIgnoreCase(menus.get(i).getMname())
                        && !txt_name.getText().equals(LogicMenu.getMenuName())) {
                    b = true;
                    error_name.setText("Tên thực đơn đã tồn tại");
                }
            }
        }
        
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name) && !b){
            error_name.setText(null);
        }
        return b;
    }
    @FXML
    private void checkName(KeyEvent event) throws SQLException {
        boolean b = isNameExist();
    }
    @FXML
    private void goBack(ActionEvent event) throws SQLException {
        MenuSP.setLoadDB(true);
        HandMenu.getScene().setRoot(FunctionsController.popRoot());
    }

    @FXML
    private void goChooseFood(ActionEvent event) throws IOException, SQLException {
        Button btn = (Button)event.getSource();
        Pane parent = (Pane)btn.getParent();
        String tid = ((Text) parent.getChildren().get(0)).getText();
        MenuSP.setTimeInDay(tid);
        FoodDAOImpl fImpl = new FoodDAOImpl();
        if(!MenuSP.isLoadDB()){
            MenuSP.getMorning().clear();
            MenuSP.getNoon().clear();
            MenuSP.getNight().clear();
            if (morning_vbox.getChildren().size() > 1) {
                for (int i = 1; i < morning_vbox.getChildren().size(); i++) {
                    Pane pane = (Pane) morning_vbox.getChildren().get(i);
                    if (pane.getChildren().size() > 1) {
                        Label lb = (Label) pane.getChildren().get(1);
                        String f_name = lb.getText();

                        MenuSP.getMorning().add(fImpl.getFood(f_name));
                    }
                }
            }
            if (noon_vbox.getChildren().size() > 1) {
                for (int i = 1; i < noon_vbox.getChildren().size(); i++) {
                    Pane pane = (Pane) noon_vbox.getChildren().get(i);
                    if (pane.getChildren().size() > 1) {
                        Label lb = (Label) pane.getChildren().get(1);
                        String f_name = lb.getText();

                        MenuSP.getNoon().add(fImpl.getFood(f_name));
                    }
                }
            }
            if (night_vbox.getChildren().size() > 1) {
                for (int i = 1; i < night_vbox.getChildren().size(); i++) {
                    Pane pane = (Pane) night_vbox.getChildren().get(i);
                    if (pane.getChildren().size() > 1) {
                        Label lb = (Label) pane.getChildren().get(1);
                        String f_name = lb.getText();
                        MenuSP.getNight().add(fImpl.getFood(f_name));
                    }
                }
            }
        }
        LogicMenu.setMenuName(txt_name.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/ChooseFoodView.fxml"));
        Parent updateMenu = loader.load();
        
        FunctionsController.pushRoot(HandMenu);
        HandMenu.getScene().setRoot(updateMenu);
    }
}
