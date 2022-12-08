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
import cookingNotebook.support.TextFieldValidation;
import cookingNotebook.support.VNCharacterUtils;
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
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Admin
 */
public class CreateMenuViewController implements Initializable {
    ToolBar tao_td;
    @FXML
    HBox chonmon;
    @FXML
    Button but_tao_td;
    @FXML
    Pane thongbao;
    @FXML
    private Pane CreateMenu;
    private TilePane viewFood;
    @FXML
     VBox foodChoosed;

    @FXML
     TilePane Vegetable;
    @FXML
     TilePane Meat;
    @FXML
     TilePane Soup;
    @FXML
     TilePane Fish;
    @FXML
     TilePane Holiday;
    @FXML
     TilePane Juice;
    @FXML
     TilePane Cake;
    @FXML
     TilePane RiceGruel;
    @FXML
     StackPane stackFood;
    @FXML
    private VBox VboxVegetable;
    @FXML
    private VBox VBoxMeat;
    @FXML
    private VBox VBoxSoup;
    @FXML
    private VBox VBoxFish;
    @FXML
    private VBox VBoxHoliday;
    @FXML
    private VBox VBoxJuice;
    @FXML
    private VBox VBoxCake;
    @FXML
    private VBox VBoxRice;
    @FXML
    private TextField nameMenu;
    @FXML
    private Label nameMenuError;
    @FXML
     TitledPane titledPane;
    @FXML
     Pane sua_menu;
    @FXML
     TextField menu_name;
    @FXML
    private TextField searchBar;
    @FXML
    private Button mon_an_sang_btn;
    @FXML
    private Button mon_chinh_btn;
    @FXML
    private Button mon_phu_btn;
    @FXML
    private Button mon_rau_btn;
    @FXML
    private Button mon_canh_btn;
    @FXML
    private Button mon_an_kem_btn;
    @FXML
    private Button mon_an_dem_btn;
    @FXML
    private Button mon_an_vat_btn;
    

    @FXML
    private void changeTab(ActionEvent event) throws SQLException {

        Button btn = (Button) event.getSource();
        VBox parent = (VBox) ((Pane) btn.getParent()).getParent();
        for (int i = 1; i < 9; i++) {
            Pane pane = (Pane) parent.getChildren().get(i);
            ((Button) pane.getChildren().get(0)).setStyle("-fx-background-color:#635e5e");
        }
        btn.setStyle("-fx-background-color:#04AA6D");
        for (int i = 0; i < 8; i++) {
            ((VBox) stackFood.getChildren().get(i)).setVisible(false);
        }
        switch (btn.getText()) {
            case "Món ăn sáng" ->{
                VboxVegetable.setVisible(true);
                searchBar.setPromptText("Tìm kiếm món ăn sáng");
            }
                
            case "Món chính" ->{
                VBoxMeat.setVisible(true);
                searchBar.setPromptText("Tìm kiếm món chính");
            } 
            case "Món phụ" ->{
                VBoxSoup.setVisible(true);
                searchBar.setPromptText("Tìm kiếm món phụ");
            }
               
            case "Món rau" ->{
                searchBar.setPromptText("Tìm kiếm món rau");
                VBoxFish.setVisible(true);
            }
                
            case "Món canh" ->{
                searchBar.setPromptText("Tìm kiếm món canh");
                VBoxHoliday.setVisible(true);
            }
                
            case "Món ăn kèm" ->{
                searchBar.setPromptText("Tìm kiếm món ăn kèm");
                VBoxJuice.setVisible(true);
            }
                
            case "Món ăn đêm" ->{
                searchBar.setPromptText("Tìm kiếm món ăn đêm");
                VBoxCake.setVisible(true);
            }
                
            case "Món ăn vặt" ->{
                searchBar.setPromptText("Tìm kiếm món ăn vặt");
                VBoxRice.setVisible(true);
            }
                
        }    
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        CreateMenu.getScene().setRoot(FunctionsController.popRoot());
    }

    @FXML
    public void showThongbao() {
        thongbao.setVisible(true);
        chonmon.setOpacity(0.3);
    }

    @FXML
    public void hideThongbao() {
        thongbao.setVisible(false);
        chonmon.setOpacity(1);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            searchBar.setPromptText(searchBar.getPromptText()+"MÓN ĂN SÁNG");
            loadData("MÓN ĂN SÁNG");
            loadData("MÓN CHÍNH");
            loadData("MÓN PHỤ");
            loadData("MÓN RAU");
            loadData("MÓN CANH");
            loadData("MÓN ĂN KÈM");
            loadData("MÓN ĂN ĐÊM");
            loadData("MÓN ĂN VẶT");
        } catch (SQLException ex) {}
    }
    private void checkboxAction(String category, CheckBox cb, LogicFood f) {
        if (cb.selectedProperty().get()) {
            Text name = new Text();
            name.setLayoutX(45);
            name.setText("- " + f.getName());
            name.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20;-fx-fill:white");
            Pane p = new Pane();
            Label lb = new Label();
            Line line = new Line();
            lb.setText("⚫ " + category);
            lb.setStyle("-fx-text-fill:white; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
            line.setStroke(new Color(0.4196, 0.4196, 0.4196, 1.0));
            line.setLayoutX(127);
            line.setLayoutY(32);
            line.setStartX(-110);
            line.setStartY(2);
            line.setEndX(-110);
            line.setEndY(0);
            if (foodChoosed.getChildren().isEmpty()) {
                line.setEndY(20);
                name.setLayoutY(50);
                p.getChildren().add(lb);
                p.getChildren().add(line);
                p.getChildren().add(name);
                foodChoosed.getChildren().add(p);
                titledPane.setExpanded(true);
            } else {
                int a = 0;
                for (int i = 0; i < foodChoosed.getChildren().size(); i++) {
                    Pane pane_choice = (Pane) foodChoosed.getChildren().get(i);
                    Label lb_ = (Label) pane_choice.getChildren().get(0);
                    if (lb_.getText().substring(2).equals(category)) {
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
                    foodChoosed.getChildren().add(p);
                }
            }
        } else {
            for (int i = 0; i < foodChoosed.getChildren().size(); i++) {
                Pane pane_choice = (Pane) foodChoosed.getChildren().get(i);
                Label lb_category = (Label) pane_choice.getChildren().get(0);
                if (lb_category.getText().substring(2).equals(category)) {
                    if (pane_choice.getChildren().size() == 3) {
                        foodChoosed.getChildren().remove(i);
                    } else {
                        for (int j = 2; j < pane_choice.getChildren().size(); j++) {
                            Text txt_food = (Text) pane_choice.getChildren().get(j);
                            if (txt_food.getText().substring(2).equals(f.getName())) {
                                pane_choice.getChildren().remove(j);
                                Line l = (Line) pane_choice.getChildren().get(1);

                                for (int k = 2; k < pane_choice.getChildren().size(); k++) {
                                    Text txt = ((Text) pane_choice.getChildren().get(k));
                                    if (k == 2) {
                                        txt.setLayoutY(50);
                                    } else {
                                        double pre_Y = ((Text) pane_choice.getChildren().get(k - 1)).getLayoutY();
                                        txt.setLayoutY(pre_Y + 30);
                                    }
                                }
                                l.setEndY((pane_choice.getHeight() - 66));
                            }
                        }
                    }
                }
            }
        }
        if (foodChoosed.getChildren().isEmpty()) {
            titledPane.setExpanded(false);
        }
    }
    public void loadData(String category) throws SQLException {
        FoodDAOImpl fImpl = new FoodDAOImpl();
        fImpl.getFoodInCategory(category).forEach((var f) -> {
            CategoryController con = new CategoryController();
            VBox fFood = con.CreateFrame(f.getName(), f.convertImg(), f.getCookTime());
            Pane pane = new Pane();
            pane.setPrefWidth(200);
            pane.setPrefHeight(222);
            CheckBox cb = new CheckBox();
            cb.setLayoutX(91);
            cb.setLayoutY(245);
            cb.setCursor(Cursor.HAND);
            cb.setOnAction(eh -> checkboxAction(category, cb, f));
            pane.getChildren().add(fFood);
            pane.getChildren().add(cb);
            switch (category) {
                case "MÓN ĂN SÁNG" ->
                    Vegetable.getChildren().add(pane);
                case "MÓN CHÍNH" ->
                    Meat.getChildren().add(pane);
                case "MÓN PHỤ" ->
                    Soup.getChildren().add(pane);
                case "MÓN RAU" ->
                    Fish.getChildren().add(pane);
                case "MÓN CANH" ->
                    Holiday.getChildren().add(pane);
                case "MÓN ĂN KÈM" ->
                    Juice.getChildren().add(pane);
                case "MÓN ĂN ĐÊM" ->
                    Cake.getChildren().add(pane);
                case "MÓN ĂN VẶT" ->
                    RiceGruel.getChildren().add(pane);
            }
        });

        if (fImpl.getFoodInCategory(category).isEmpty()) {
            alert("Không có công thức",category);
        }
    }
    public void loadData(LogicFood food, String category){
        CategoryController con = new CategoryController();
            VBox fFood = con.CreateFrame(food.getName(), food.convertImg(), food.getCookTime());
            Pane pane = new Pane();
            pane.setPrefWidth(200);
            pane.setPrefHeight(222);
            CheckBox cb = new CheckBox();
            cb.setLayoutX(91);
            cb.setLayoutY(245);
            cb.setCursor(Cursor.HAND);
            cb.setOnAction(eh -> checkboxAction(category, cb, food));
            pane.getChildren().add(fFood);
            pane.getChildren().add(cb);
            switch (category) {
                case "MÓN ĂN SÁNG" ->
                    Vegetable.getChildren().add(pane);
                case "MÓN CHÍNH" ->
                    Meat.getChildren().add(pane);
                case "MÓN PHỤ" ->
                    Soup.getChildren().add(pane);
                case "MÓN RAU" ->
                    Fish.getChildren().add(pane);
                case "MÓN CANH" ->
                    Holiday.getChildren().add(pane);
                case "MÓN ĂN KÈM" ->
                    Juice.getChildren().add(pane);
                case "MÓN ĂN ĐÊM" ->
                    Cake.getChildren().add(pane);
                case "MÓN ĂN VẶT" ->
                    RiceGruel.getChildren().add(pane);
            }
    }
    public void clear(String category){
        TilePane foodList = null;
        switch(category){
                case "MÓN ĂN SÁNG" ->
                    foodList = Vegetable;
                case "MÓN CHÍNH" ->
                    foodList = Meat;
                case "MÓN PHỤ" ->
                    foodList = Soup;
                case "MÓN RAU" ->
                    foodList = Fish;
                case "MÓN CANH" ->
                    foodList = Holiday;
                case "MÓN ĂN KÈM" ->
                    foodList = Juice;
                case "MÓN ĂN ĐÊM" ->
                    foodList = Cake;
                case "MÓN ĂN VẶT" ->
                   foodList = RiceGruel;
        }
        int size=foodList.getChildren().size();
        for(int i = 0 ; i <  size; i ++){
            foodList.getChildren().remove(0);
        }
    }
    @FXML
    private void loadMenutoDB(ActionEvent event) throws SQLException, IOException {
        MenuDAOImpl mImpl = new MenuDAOImpl();
        FoodDAOImpl fImpl = new FoodDAOImpl();
        Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
        TextFieldValidation.isTextFieldNotEmpty(nameMenu,nameMenuError,"Hãy nhập tên thực đơn");
        List<Menu> menus= mImpl.getAll();
        boolean b = true;
        for(int i = 0 ; i < menus.size() ; i ++){
            if(menus.get(i).getMname().equals(nameMenu.getText()) && TextFieldValidation.isTextFieldNotEmpty(nameMenu)){
                b = false;
                nameMenuError.setText("Tên thực đơn đã tồn tại");
            }
        }
        if(TextFieldValidation.isTextFieldNotEmpty(nameMenu) && b ){
            Menu m = new Menu(0, nameMenu.getText());
            mImpl.insert(m);
            m = mImpl.get(nameMenu.getText());
            for (int i = 0; i < foodChoosed.getChildren().size(); i++) {
                Pane pane = (Pane) foodChoosed.getChildren().get(i);
                for (int j = 2; j < pane.getChildren().size(); j++) {
                    String name = ((Text) pane.getChildren().get(j)).getText().substring(2);
                    Menu_Food m_f = new Menu_Food(m.getMid(),  fImpl.getFood(name).getId());
                    m_fImpl.insert(m_f);
                }
            }
            AlertDialog.display("Menu", "Lưu thực đơn thành công");
            FunctionsController.popRoot();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuView.fxml"));
            Parent MenuView = loader.load(); 
            CreateMenu.getScene().setRoot(MenuView);
        }
    }
    
    @FXML
    private void updateMenu(ActionEvent event) throws SQLException, IOException {
        if(foodChoosed.getChildren().isEmpty()){
            AlertDialog.display("Chỉnh sửa thất bại", "Hãy chọn ít nhất 1 món ăn");
        }
        if(!TextFieldValidation.isTextFieldNotEmpty(menu_name)){
            AlertDialog.display("Chỉnh sửa thất bại", "Hãy nhập tên thực đơn");
        }
        else{
            Menu_FoodDAOimpl m_fImpl = new Menu_FoodDAOimpl();
            MenuDAOImpl mImpl = new MenuDAOImpl();
            FoodDAOImpl fImpl = new FoodDAOImpl();
            m_fImpl.deleteAllFood(LogicMenu.getMenuId());
            LogicMenu.setMenuName(menu_name.getText());
            mImpl.update(new Menu(LogicMenu.getMenuId(),LogicMenu.getMenuName()));
            for (int i = 0; i < foodChoosed.getChildren().size(); i++) {
                Pane pane = (Pane) foodChoosed.getChildren().get(i);
                for (int j = 2; j < pane.getChildren().size(); j++) {
                    String name = ((Text) pane.getChildren().get(j)).getText().substring(2);
                    Menu_Food m_f = new Menu_Food(LogicMenu.getMenuId(),  fImpl.getFood(name).getId());
                    m_fImpl.insert(m_f);
                }
            }
            AlertDialog.display("Chỉnh sửa thành công", "Sửa thành công");
            FunctionsController.popRoot();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/MenuInfoView.fxml"));
            Parent menu_info_view = loader.load(); 
            CreateMenu.getScene().setRoot(menu_info_view);
        }
    }
    public void alert(String text, String category){
        Label lb = new Label();
        lb.setText(text);
        TilePane.setMargin(lb, new Insets(200, 0, 0, 280));
        lb.setStyle("-fx-text-fill:#736d6d; -fx-font-family: 'Times New Roman'; -fx-font-size: 21;");
        switch (category) {
            case "MÓN ĂN SÁNG" -> {
                if (Vegetable.getChildren().isEmpty()) {
                    Vegetable.getChildren().add(lb);
                }
            }
            case "MÓN CHÍNH" -> {
                if (Meat.getChildren().isEmpty()) {
                    Meat.getChildren().add(lb);
                }
            }
            case "MÓN PHỤ" -> {
                if (Soup.getChildren().isEmpty()) {
                    Soup.getChildren().add(lb);
                }
            }
            case "MÓN RAU" -> {
                if (Fish.getChildren().isEmpty()) {
                    Fish.getChildren().add(lb);
                }
            }
            case "MÓN CANH" -> {
                if (Holiday.getChildren().isEmpty()) {
                    Holiday.getChildren().add(lb);
                }
            }
            case "MÓN ĂN KÈM" -> {
                if (Juice.getChildren().isEmpty()) {
                    Juice.getChildren().add(lb);
                }
            }
            case "MÓN ĂN ĐÊM" -> {
                if (Cake.getChildren().isEmpty()) {
                    Cake.getChildren().add(lb);
                }
            }
            case "RICE_GRUEL" -> {
                if (RiceGruel.getChildren().isEmpty()) {
                    RiceGruel.getChildren().add(lb);
                }
            }
        }
    }
    public void checkToSet(TilePane x, String food_name){
        for (int k = 0; k < x.getChildren().size(); k++) {
            Pane pane_frame = (Pane) x.getChildren().get(k);
            VBox fFood = (VBox) pane_frame.getChildren().get(0);
            CheckBox cb = (CheckBox) pane_frame.getChildren().get(1);
            Text txt_name = (Text) fFood.getChildren().get(1);
            if (txt_name.getText().equals(food_name)) {
                cb.selectedProperty().set(true);
            }
        }
    }
    public void setCheckBox(){
        if (!foodChoosed.getChildren().isEmpty()) {
            for(int i = 0 ; i < foodChoosed.getChildren().size() ; i ++){
                Pane pane = (Pane) foodChoosed.getChildren().get(i);
                String category = ((Label) pane.getChildren().get(0)).getText().substring(2);
                for(int j = 2 ; j < pane.getChildren().size(); j ++){
                    Text txt = (Text) pane.getChildren().get(j);
                    String food_name = txt.getText().substring(2);
                    switch(category){
                        case "MÓN ĂN SÁNG" ->{
                            checkToSet(Vegetable, food_name);
                        }
                            
                        case "MÓN CHÍNH" ->
                            checkToSet(Meat, food_name);
                        case "MÓN PHỤ" ->
                            checkToSet(Soup, food_name);
                        case "MÓN RAU" ->
                             checkToSet(Fish, food_name);
                        case "MÓN CANH" ->
                            checkToSet(Holiday, food_name);
                        case "MÓN ĂN KÈM" ->
                            checkToSet(Juice, food_name);
                        case "MÓN ĂN ĐÊM" ->
                            checkToSet(Cake, food_name);
                        case "MÓN ĂN VẶT" ->
                            checkToSet(RiceGruel, food_name);
                    }
                            
                }
                
            }
        }
    }
    @FXML
    private void searchType(KeyEvent event) throws SQLException {
        String category = searchBar.getPromptText().substring(8).toUpperCase();
        clear(category);
        if(!TextFieldValidation.isTextFieldNotEmpty(searchBar)){
            loadData(category);
            setCheckBox();
        }
        else{
            FoodDAOImpl fImpl = new FoodDAOImpl();
            boolean hasResult = false;
            List<LogicFood> foods = fImpl.getFoodInCategory(category);
            String inputs[] = searchBar.getText().split(" ");
            clear(category);
            List<LogicFood> food_match = new ArrayList<>();
            List<String> name_match = new ArrayList<>();
            for (int j = 0; j < foods.size(); j++) {
                String parts[] = foods.get(j).getName().split(" ");
                String s = null;
                for (int k = 0; k < parts.length; k++) {
                    String part_fName = VNCharacterUtils.removeAccent(parts[k].toLowerCase());
                    String txt_input = VNCharacterUtils.removeAccent(inputs[0].toLowerCase());
                    if (part_fName.contains(txt_input)) {
                        food_match.add(foods.get(j));
                        for (int l = k + 1; l < parts.length; l++) {
                            if (s == null) {
                                s = parts[l];
                            } else {
                                s = s + " " + parts[l];
                            }
                        }
                        name_match.add(s);
                        break;
                    } else {
                        if (s == null) {
                            s = parts[k];
                        } else {
                            s = s + " " + parts[k];
                        }
                    }
                }
            }
            for (int i = 1; i < inputs.length; i++) {
                List<String> name_remove = new ArrayList<>();
                List<LogicFood> food_remove = new ArrayList<>();
                for (int j = 0; j < name_match.size(); j++) {
                    String parts[] = name_match.get(j).split(" ");
                    boolean remove = true;
                    for (int k = 0; k < parts.length; k++) {
                        String part_fName = VNCharacterUtils.removeAccent(parts[k].toLowerCase());
                        String txt_input = VNCharacterUtils.removeAccent(inputs[i].toLowerCase());
                        if (part_fName.contains(txt_input)) {
                            remove = false;
                        }
                    }
                    if (remove) {
                        name_remove.add(name_match.get(j));
                        food_remove.add(food_match.get(j));
                    }
                }
                for (int k = 0; k < name_match.size(); k++) {
                    for (int j = 0; j < name_remove.size(); j++) {
                        if (name_match.get(k).equals(name_remove.get(j))) {
                            name_match.remove(k);
                            food_match.remove(k);
                            k = -1;
                            break;
                        }
                    }
                }
            }
            if (food_match.isEmpty()) {
                hasResult = false;
            } else {
                food_match.forEach(food -> {
                    loadData(food, category);
                    setCheckBox();
                });
            }
            if (!hasResult) {
                alert("Không có kết quả", category);
            }
        }
    }

    
    
}