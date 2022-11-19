/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.DAO.Database;
import cookingNotebook.support.TextFieldValidation;
import cookingNotebook.support.AlertDialog;
import cookingNotebook.DAO.Food;
import cookingNotebook.DAO.FoodDAO;
import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.FoodIngr;
import cookingNotebook.DAO.FoodIngrDAO;
import cookingNotebook.DAO.FoodIngrDAOImpl;
import cookingNotebook.DAO.Ingredient;
import cookingNotebook.DAO.IngredientDAOImpl;
import cookingNotebook.DAO.Step;
import cookingNotebook.DAO.StepDAOImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Admin
 */
public class AddFoodViewController implements Initializable{
   @FXML Label loaiMon;
   @FXML Pane themmon,suamon;
    @FXML
    private TextField txt_name;
    @FXML
    private TextField txt_time;
    @FXML
    private TextField url_image;
    @FXML
    private TextField txt_ten_nl;
    @FXML
    private TextField txt_soluong_nl;
    @FXML
    private TextField txt_donvitinh;
   @FXML
    private TextArea txt_buoclam;
    @FXML
    private Label step_index;
    @FXML
    private Label error_food;
    @FXML
    private Label error_ingr;
    @FXML
    private Label error_step;
    @FXML
    private VBox all;
    @FXML
    private Label error_time;
    @FXML
    private Label error_soluong_nl;
    @FXML
    private Label error_unit;
    private List<TextField> IngredientName = new ArrayList<TextField>();
    private List<Label> IngredientNameError = new ArrayList<Label>();
    private List<TextField> IngredientUnit = new ArrayList<TextField>();
    private List<Label> IngredientUnitError = new ArrayList<Label>();
    private List<TextField> IngredientAmount = new ArrayList<TextField>();
    private List<Label> IngredientAmountError = new ArrayList<Label>();
    private List<Label> StepError = new ArrayList<Label>();
    private List<TextArea> Step = new ArrayList<TextArea>();
    private List<String> StepImgDB = new ArrayList<String>();
   
    @FXML
    private TilePane Ingredient;
    @FXML
    private VBox VboxStep;
    private FileChooser fileChooser;
    private File file;
    private File file1;
    private Stage stage;
    @FXML
    private VBox AddFoodView;
    private ImageView imgFood;
    private Image image;
    private FileInputStream fis;
    @FXML
    private ImageView imgFood1;
    private Text texturl;
    
    public Label getLoaiMon() {
		return loaiMon;
    }
    @FXML
        public void goBackLoaiMon(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            CategoryController controller = loader.getController();
            controller.topLabel.setText(loaiMon.getText());
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
	}
        public void goBackMonAn(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
            Parent addFoodView = loader.load();
            Scene scene = new Scene(addFoodView);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
	}
        
    @FXML
        private void loadFoodToDatabase(ActionEvent e) throws SQLException{
            
            boolean isFoodNameNotEmpty = TextFieldValidation.isTextFieldNotEmpty(txt_name);
            boolean isAlliNameNotEmpty = false;
            int t = 0;
            for(int i=0;i<IngredientName.size();i++){
                if(!TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i))){
                    TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i));
                    t = 1;
                }
            }
            if(t!=1){
                isAlliNameNotEmpty = true;
            }
            boolean isAllStepNotEmpty = false;
            t = 0;
            for(int i=0;i<Step.size();i++){
                if(!TextFieldValidation.isTextFieldNotEmpty(Step.get(i))){
                    TextFieldValidation.isTextFieldNotEmpty(Step.get(i));
                    t = 1;
                }
            }
            if(t!=1){
                isAllStepNotEmpty = true;
            }
           
            boolean isNotEmpty = TextFieldValidation.isTextFieldNotEmpty(txt_time);
            boolean isTimeNumber = TextFieldValidation.textFieldTypeNumber(txt_time);
            
            t = 0;
            boolean isValidate = false;
            for(int i=0;i<IngredientName.size();i++){
                if(!TextFieldValidation.checkAmountAndUnit(IngredientAmount.get(i), IngredientUnit.get(i))){
                    
                    t = 1;
                }
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                        boolean f = (TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i)) && TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i)));
                        if(!f){
                            t = 1;
                }
                    
                    
                }
            }
            if(t!=1){
                isValidate = true;
            }
                          
            if(isFoodNameNotEmpty && isAlliNameNotEmpty && isAllStepNotEmpty && (isTimeNumber||!isNotEmpty) && isValidate ){
            Food food = new Food();
            food.setName(txt_name.getText());
            food.setCategory(loaiMon.getText()); 
            if(isNotEmpty){
                food.setCooktime(Double.parseDouble(txt_time.getText()));
            }
            else{
                food.setCooktime(Double.NaN);
            }

            if(file!=null){
                Path src = file.toPath();
                Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+".png");
                System.out.print(src);
                try {
                    Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                food.setImg("./src/resource/imgs/"+txt_name.getText()+".png");
            }
            FoodDAOImpl i = new FoodDAOImpl();
            i.insert(food);
            food = i.getFood(txt_name.getText());//getid cho class Food
            
            Ingredient ingr = new Ingredient();
            for (int a=0;a<IngredientName.size();a++){
                ingr.setName(IngredientName.get(a).getText());
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(a))){
                    ingr.setUnit(IngredientUnit.get(a).getText());
                }
                else{
                    ingr.setUnit(null);
                }
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(a))){
                    ingr.setHasQuantity(1);
                
                }else{
                    ingr.setHasQuantity(0);
                }
                IngredientDAOImpl j = new IngredientDAOImpl();
                j.insert(ingr);      
                ingr = j.getIngr(IngredientName.get(a).getText());
            
                FoodIngr food_ingr = new FoodIngr();
                food_ingr.setFid(food.getId());
                food_ingr.setIid(ingr.getId());
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(a))){
                    food_ingr.setAmount(Double.valueOf(IngredientAmount.get(a).getText()));
                }
                else{
                    food_ingr.setAmount(Double.NaN);
                }
                FoodIngrDAO k = new FoodIngrDAOImpl();
                k.insert(food_ingr);
            }
            
            for(int a=0;a< Step.size();a++){
                Step step = new Step();
                step.setId(food.getId());
                
                step.setContent(Step.get(a).getText());
                Pane pane = (Pane)Step.get(a).getParent();
          
                if( pane.getChildren().size() == 5 || (pane.getChildren().size() == 6 && "1".equals(((Label)pane.getChildren().get(3)).getText()))){
                    step.setImg(null);
                }
                if(pane.getChildren().size() == 8 || (pane.getChildren().size() == 7 && "1".equals(((Label)pane.getChildren().get(3)).getText()))){
                    Image img = null;
                    if(pane.getChildren().size() == 8){
                         img = ((ImageView)pane.getChildren().get(6)).getImage();
                    }
                    else{
                         img = ((ImageView)pane.getChildren().get(5)).getImage();
                    }
                    Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
                    Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+"_step"+(a+1)+".png");
                try {
                    Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                    step.setImg("./src/resource/imgs/"+txt_name.getText()+"_step"+(a+1)+".png");
                }
                step.setIndex(a+1);
                StepDAOImpl l = new StepDAOImpl();
                l.insert(step);
            }
            AlertDialog.display("AddData", "Data Insert Successfully");
            
            txt_name.clear();
            txt_time.clear();
            error_time.setText(null);
            for(int a=0;a<IngredientName.size();a++){
                IngredientName.get(a).clear();
                IngredientAmount.get(a).clear();
                IngredientUnit.get(a).clear();
                IngredientUnitError.get(a).setText(null);
                IngredientAmountError.get(a).setText(null);
            }
            for(int a=0;a<Step.size();a++){
                Step.get(a).clear();
            }
            
        }
        }
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        IngredientName.add(txt_ten_nl);
        IngredientNameError.add(error_ingr);
        IngredientUnit.add(txt_donvitinh);
        IngredientUnitError.add(error_unit);
        IngredientAmount.add(txt_soluong_nl);
        IngredientAmountError.add(error_soluong_nl);
        StepError.add(error_step);
        Step.add(txt_buoclam);
        
        checkEmpty();
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images","*.png","*.jpg","*.gif"));
    }
    public void checkEmpty(){
        boolean isFoodNameEmpty = TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food, "Tên món ăn không được trống");
        
        for(int i=0;i<IngredientName.size();i++){
            TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i), IngredientNameError.get(i), "Nhập tên nguyên liệu");
        }
        for(int i=0;i<Step.size();i++){
            boolean isStepEmpty = TextFieldValidation.isTextFieldNotEmpty(Step.get(i), StepError.get(i), "Bước làm không được để trống");
        }
    }
    @FXML
    private void checkTextField(KeyEvent event) {
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            error_food.setText(null);
        }
        else{
            TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food, "Tên món ăn không được trống");  
        }
        
        if(TextFieldValidation.isTextFieldNotEmpty(txt_time)){
            TextFieldValidation.textFieldTypeNumber(txt_time, error_time, "Nhập kí tự số");
            if(TextFieldValidation.textFieldTypeNumber(txt_time)){
                error_time.setText(null);
            }
        }
        else{
            error_time.setText(null);
        }
        
        for(int i = 0; i < IngredientName.size(); i++){
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i))){
                IngredientNameError.get(i).setText(null);
            }
            else{
                TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i), IngredientNameError.get(i), "Nhập tên nguyên liệu");
            }
        }
        
        for(int i = 0; i < Step.size(); i++){
            if(TextFieldValidation.isTextFieldNotEmpty(Step.get(i))){
                StepError.get(i).setText(null);
            }
            else{
           boolean isStepEmpty = TextFieldValidation.isTextFieldNotEmpty(Step.get(i), StepError.get(i), "Bước làm không được để trống");
            }
        }
        for(int i = 0; i < IngredientName.size(); i++){
            if(!TextFieldValidation.checkAmountAndUnit(IngredientAmount.get(i), IngredientUnit.get(i))){
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập đơn vị tính");
                    if(!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))){
                        IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                    }
                    else{
                        IngredientAmountError.get(i).setText(null);
                    }
                }
                if(!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập số lượng");
                    if(!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))){
                        IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                    }
                    else{
                        IngredientUnitError.get(i).setText(null);
                    }
                }
            }
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                if(!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))){
                    IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                }
                else{
                    IngredientAmountError.get(i).setText(null);
                }
                if(!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                }
                else{
                     IngredientUnitError.get(i).setText(null);
                }
                
            }
            if(!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                IngredientAmountError.get(i).setText(null);
                IngredientUnitError.get(i).setText(null);
            }
        }
        
    }
    @FXML
    private void checkTextField1(MouseEvent event){
        if(TextFieldValidation.isTextFieldNotEmpty(txt_name)){
            error_food.setText(null);
        }
        else{
            TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food, "Tên món ăn không được trống");  
        }
        
        if(TextFieldValidation.isTextFieldNotEmpty(txt_time)){
            TextFieldValidation.textFieldTypeNumber(txt_time, error_time, "Nhập kí tự số");
            if(TextFieldValidation.textFieldTypeNumber(txt_time)){
                error_time.setText(null);
            }
        }
        else{
            error_time.setText(null);
        }
        
        for(int i = 0; i < IngredientName.size(); i++){
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i))){
                IngredientNameError.get(i).setText(null);
            }
            else{
                TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i), IngredientNameError.get(i), "Nhập tên nguyên liệu");
            }
        }
        
        for(int i = 0; i < Step.size(); i++){
            if(TextFieldValidation.isTextFieldNotEmpty(Step.get(i))){
                StepError.get(i).setText(null);
            }
            else{
           boolean isStepEmpty = TextFieldValidation.isTextFieldNotEmpty(Step.get(i), StepError.get(i), "Bước làm không được để trống");
            }
        }
        for(int i = 0; i < IngredientName.size(); i++){
            if(!TextFieldValidation.checkAmountAndUnit(IngredientAmount.get(i), IngredientUnit.get(i))){
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập đơn vị tính");
                    if(!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))){
                        IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                    }
                    else{
                        IngredientAmountError.get(i).setText(null);
                    }
                }
                if(!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập số lượng");
                    if(!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))){
                        IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                    }
                    else{
                        IngredientUnitError.get(i).setText(null);
                    }
                }
            }
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                if(!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))){
                    IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                }
                else{
                    IngredientAmountError.get(i).setText(null);
                }
                if(!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))){
                    IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                }
                else{
                     IngredientUnitError.get(i).setText(null);
                }
                
            }
            if(!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                IngredientAmountError.get(i).setText(null);
                IngredientUnitError.get(i).setText(null);
            }
        }
        
    }
    @FXML
    private void AddIngrLine(ActionEvent event) {
        Pane pane = new Pane();
        pane.setPrefHeight(48);
        
        Label IngrNameLb = new Label();
        TextField IngrNameTf = new TextField();
        IngrNameTf.setPrefWidth(173);
        IngrNameTf.setPrefHeight(30);
        IngrNameTf.setStyle("-fx-font-size:16");
        IngredientName.add(IngrNameTf);
        Label IngrNameLbError = new Label();
        IngrNameLbError.setStyle("-fx-text-fill:red");
        IngrNameLbError.setLayoutX(41);
        IngrNameLbError.setLayoutY(37);
        IngredientNameError.add(IngrNameLbError);
        IngrNameLb.setText("Tên:");
        IngrNameLb.setStyle("-fx-font-size:18;");
        IngrNameLb.setPrefWidth(210);
        IngrNameLb.setGraphic(IngrNameTf);
        pane.getChildren().add(IngrNameLb);
        pane.getChildren().add(IngrNameLbError);
        Ingredient.getChildren().add(pane);
        
        TextField IngrAmountTf = new TextField();
        IngrAmountTf.setPrefWidth(173);
        IngrAmountTf.setPrefHeight(30);
        IngrAmountTf.setStyle("-fx-font-size:16");
        IngrAmountTf.setOnKeyReleased(e ->checkTextField(e));
        IngredientAmount.add(IngrAmountTf);
        Label IngrAmountLbError = new Label();
        IngrAmountLbError.setStyle("-fx-text-fill:red");
        IngrAmountLbError.setLayoutX(306);
        IngrAmountLbError.setLayoutY(37);
        IngredientAmountError.add(IngrAmountLbError);
        Label IngrAmountLb = new Label();
        IngrAmountLb.setText("Số lượng:");
        IngrAmountLb.setStyle("-fx-font-size:18;");
        IngrAmountLb.setGraphic(IngrAmountTf);
        IngrAmountLb.setLayoutX(225);
        pane.getChildren().add(IngrAmountLb);
        pane.getChildren().add(IngrAmountLbError);
        
        TextField IngrUnitTf = new TextField();
        IngrUnitTf.setPrefWidth(173);
        IngrUnitTf.setPrefHeight(30);
        IngrUnitTf.setStyle("-fx-font-size:16");
        IngrUnitTf.setOnKeyReleased(e ->checkTextField(e));
        IngredientUnit.add(IngrUnitTf);
        Label IngrUnitLbError = new Label();
        IngrUnitLbError.setStyle("-fx-text-fill:red");
        IngrUnitLbError.setLayoutX(599);
        IngrUnitLbError.setLayoutY(37);
        IngredientUnitError.add(IngrUnitLbError);
        Label IngrUnitLb = new Label();
        IngrUnitLb.setText("Đơn vị tính:");
        IngrUnitLb.setStyle("-fx-font-size:18;");
        IngrUnitLb.setGraphic(IngrUnitTf);
        IngrUnitLb.setLayoutX(503);
        pane.getChildren().add(IngrUnitLb);
        pane.getChildren().add(IngrUnitLbError);
        
        Button delete = new Button();
        delete.setText("XÓA");
        delete.setPrefWidth(52);
        delete.setPrefHeight(27);
        delete.setLayoutX(809);
        delete.setCursor(Cursor.HAND);
        delete.setOnAction( e ->DeleteIngrLine(e));
        delete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: Bold; -fx-background-radius: 10;");
       
        pane.getChildren().add(delete);
        
         }

    private void DeleteIngrLine(ActionEvent event) {
        Pane pane = (Pane) ((Button) event.getSource()).getParent();
        Ingredient.getChildren().remove(pane);// Xóa ở view
        //Xóa ở list

        IngredientName.remove((TextField) ((Label) pane.getChildren().get(0)).getGraphic());
        IngredientNameError.remove(((Label) pane.getChildren().get(1)));
        IngredientAmount.remove((TextField) ((Label) pane.getChildren().get(2)).getGraphic());
        IngredientAmountError.remove(((Label) pane.getChildren().get(3)));
        IngredientUnit.remove((TextField) ((Label) pane.getChildren().get(4)).getGraphic());
        IngredientUnitError.remove(((Label) pane.getChildren().get(5)));
       
    }
    
    @FXML
    private void AddStep(ActionEvent event){
        Pane pane = new Pane();
        
        TextArea StepTa = new TextArea();
        StepTa.setPrefWidth(751);
        StepTa.setPrefHeight(92);
        StepTa.setLayoutX(95);
        StepTa.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 16;");
        Step.add(StepTa);
        
        Label txt = new Label();
        txt.setText("Bước");
        txt.setLayoutX(14);
        txt.setLayoutY(36);
        txt.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 18;");
        Label index = new Label();
        index.setText(""+Step.size());
        index.setLayoutX(56);
        index.setLayoutY(36);
        index.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 18;");
        
        Button selectImg = new Button();
        selectImg.setLayoutX(5);
        selectImg.setLayoutY(113);
        selectImg.setText("Chọn ảnh");
        selectImg.setCursor(Cursor.HAND);
        selectImg.setOnAction(e -> {
            try {
                chooseStepImg(e);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        selectImg.setStyle("-fx-background-color: #ffbc48; -fx-background-radius: 20;-fx-font-family: Times New Roman; -fx-font-size: 18;");
        
        Label StepErr = new Label();
        StepErr.setStyle("-fx-text-fill:red");
        StepErr.setLayoutX(101);
        StepErr.setLayoutY(92);
        StepError.add(StepErr);
        
        Button delete = new Button();
        delete.setText("XÓA");
        delete.setLayoutX(877);
        delete.setLayoutY(34);
        delete.setCursor(Cursor.HAND);
        delete.setOnAction( e ->DeleteStep(e));
        delete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: Bold; -fx-background-radius: 10;");
       
        
        pane.getChildren().add(StepTa);
        pane.getChildren().add(StepErr);
        pane.getChildren().add(txt);
        pane.getChildren().add(index);
        pane.getChildren().add(selectImg);
        pane.getChildren().add(delete);
        
        
        VboxStep.getChildren().add(pane);
        
        
    }
    
    private void DeleteStep(ActionEvent event){
        Pane pane = (Pane) ((Button) event.getSource()).getParent();
        VboxStep.getChildren().remove(pane);
        
        Step.remove((TextArea) pane.getChildren().get(0));
        StepError.remove((Label) pane.getChildren().get(1));
        
        for(int i = 1 ; i < Step.size() ; i ++ ){
            ((Label)((Pane) Step.get(i).getParent()).getChildren().get(3)).setText(""+(i+1));
        }
    }
    
    @FXML
    private void ChooseImageFood(ActionEvent event) throws MalformedURLException {
       
       stage = ((Stage) AddFoodView.getScene().getWindow());
       file = fileChooser.showOpenDialog(stage);
       if(file!=null){
           
           Image img = new Image(file.getAbsoluteFile().toURL().toString());
           imgFood1.setImage(img);
          
       }
    } 

    @FXML
    private void chooseStepImg(ActionEvent event) throws MalformedURLException {
        stage = ((Stage) AddFoodView.getScene().getWindow());
       file1 = fileChooser.showOpenDialog(stage);
       Pane pane = (Pane)( (Button)event.getSource() ).getParent(); 
       String index =  pane.getChildren().get(3).toString();
       ImageView view = new ImageView();  
       Button del  = new Button();
       if(file1!=null){
           
           view.setPreserveRatio(false);
           view.setLayoutX(300);
           view.setLayoutY(130);
           view.setFitWidth(323);
           view.setFitHeight(189);
           Image img = new Image(file1.getAbsoluteFile().toURL().toString());
           view.setImage(img);
           
           
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold");
           del.setLayoutX(623);
           del.setLayoutY(130);
           del.setOnAction(e -> DeleteImg(e));
           del.setCursor(Cursor.HAND);
       }
        
        if( pane.getChildren().size() == 8 || ( pane.getChildren().size() == 7 && "1".equals(((Label)pane.getChildren().get(3)).getText()))){
            int i = 0;
            if(pane.getChildren().size() == 7){
                i = 5;
            }
            else{
                i = 6;
            }
            pane.getChildren().remove(i);
            pane.getChildren().add(i,view);
                
        }       
        if( pane.getChildren().size() == 5 || ( pane.getChildren().size() == 6 && !"1".equals(((Label)pane.getChildren().get(3)).getText())) ){
            int i = 0;
            if(pane.getChildren().size() == 5){
                i = 5;
            }
            else{
                i = 6;
            }   
            pane.getChildren().add(i,view);  
                pane.getChildren().add(del);
       }         
    }

    @FXML
    private void DeleteImg(ActionEvent event) {
        int i = 0 ;
        Pane pane = (Pane) ((Button)event.getSource()).getParent();
        if(pane.getChildren().size() == 7){
            i = 5;
            System.out.print("7");
        }
        if(pane.getChildren().size() == 8){
            i = 6;
        }
        pane.getChildren().remove(i);
        pane.getChildren().remove(i);
    }
}