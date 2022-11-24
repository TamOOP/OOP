/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.controllers;

import cookingNotebook.support.TextFieldValidation;
import cookingNotebook.support.AlertDialog;
import cookingNotebook.models.FoodAndIngredient;
import cookingNotebook.models.Ingredient;
import cookingNotebook.models.Step;
import cookingNotebook.DAO.FoodDAOImpl;
import cookingNotebook.DAO.FoodAndIngredientDAOImpl;
import cookingNotebook.DAO.IngredientDAOImpl;
import cookingNotebook.DAO.PathDeleted;
import cookingNotebook.DAO.StepDAOImpl;
import cookingNotebook.models.LogicFood;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javafx.scene.control.ContentDisplay;
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
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class AddFoodViewController implements Initializable{
    DecimalFormat format = new DecimalFormat();
   @FXML Label loaiMon;
    @FXML
     TextField txt_name;
    @FXML
     TextField txt_time;
    @FXML
     TextField url_image;
    @FXML
     TextField txt_ten_nl;
    @FXML
     TextField txt_soluong_nl;
    @FXML
     TextField txt_donvitinh;
   @FXML
     TextArea txt_buoclam;
    @FXML
     Label step_index;
    @FXML
     Label error_food;
    @FXML
     Label error_ingr;
    @FXML
     Label error_step;
    @FXML
     VBox all;
    @FXML
     Label error_time;
    @FXML
     Label error_soluong_nl;
    @FXML
     Label error_unit;
    
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
     ImageView imgFood1;
    private Text texturl;
    @FXML
     Text function;
    @FXML
    private Pane suamon;
    @FXML
     Button btn_save;
    @FXML
    private Pane step1;
    @FXML
    private Pane foodImg;
    
    public void init(Label labelType) {
	this.loaiMon.setText(labelType.getText());	
    }
 
    @FXML
        public void goBack(ActionEvent e) throws IOException {
            AddFoodView.getScene().setRoot(FunctionsController.popRoot());
	}
        
    @FXML
        private void loadFoodToDatabase(ActionEvent e) throws SQLException, IOException{          
            if(isValidate()){
               
            Integer idFood = null;
            String nameFood = txt_name.getText();
            String category = loaiMon.getText(); 
            Integer cooktime = 0;
            String imgFood = null;
            if(TextFieldValidation.isTextFieldNotEmpty(txt_time)){
                cooktime = Integer.valueOf(txt_time.getText());
            }
            if(file!=null){
                Path src = file.toPath();
                Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+".png");
                
                try {
                    Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                imgFood = "./src/resource/imgs/"+txt_name.getText()+".png";
            }
            LogicFood food = new LogicFood( idFood , nameFood ,cooktime,category,imgFood);
            FoodDAOImpl i = new FoodDAOImpl();
            i.insert(food);
            food = i.getFood(txt_name.getText());//getid cho class Food
            
            String nameIngr = null;
            String Unit = null;       
            Integer idIngr = null;
            for (int a=0;a<IngredientName.size();a++){
                nameIngr = IngredientName.get(a).getText();  
                IngredientDAOImpl j = new IngredientDAOImpl();
                Ingredient ingr = new Ingredient(idIngr,nameIngr);
                j.save(ingr);
                ingr = j.getIngr(IngredientName.get(a).getText());
                
                int Fid = food.getId();
                int Iid = ingr.getId();
                Double Amount = Double.NaN;
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(a))){
                    Unit = IngredientUnit.get(a).getText();
                }
                if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(a))){
                    Amount = Double.valueOf(IngredientAmount.get(a).getText());
                }
                FoodAndIngredient food_ingr = new FoodAndIngredient(Fid,Iid,Amount,Unit);
                FoodAndIngredientDAOImpl k = new FoodAndIngredientDAOImpl();
                
                k.insert(food_ingr);
            }
            
            for(int a=0;a< Step.size();a++){
                
                idFood = food.getId();
                String Content = Step.get(a).getText();
                String imgStep = null;
                Pane pane = (Pane)Step.get(a).getParent();
                Integer indexStep = null;              
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
                    imgStep = "./src/resource/imgs/"+txt_name.getText()+"_step"+(a+1)+".png";
                }
                Step step = new Step(idFood,indexStep,Content,imgStep);
                StepDAOImpl l = new StepDAOImpl();
                l.insert(step);
            }
            AlertDialog.display("AddData", "Data Insert Successfully");
            FunctionsController.popRoot();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
            Parent categoryView = loader.load();
            
            AddFoodView.getScene().setRoot(categoryView);
            ((CategoryController) loader.getController()).init(loaiMon.getText());
        }
        }
        private boolean isValidate(){
            boolean b = false;
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
                b= true;
            }
            return b;
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
                    IngredientAmountError.get(i).setText("Hãy nhập số lượng");
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
                    IngredientAmountError.get(i).setText("Hãy nhập số lượng");
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
    private void AddIngrLine(ActionEvent event){
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
        IngrNameLb.setContentDisplay(ContentDisplay.RIGHT);
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
        IngrAmountLb.setContentDisplay(ContentDisplay.RIGHT);
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
        IngrUnitLb.setContentDisplay(ContentDisplay.RIGHT);
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
    private Pane AddIngrLine(){
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
        IngrNameLb.setContentDisplay(ContentDisplay.RIGHT);
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
        IngrAmountLb.setContentDisplay(ContentDisplay.RIGHT);
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
        IngrUnitLb.setContentDisplay(ContentDisplay.RIGHT);
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
        return pane;
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
        StepTa.setWrapText(true);
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
    List<Integer> stepIndex = new ArrayList<Integer>();
    private Pane AddStep(LogicFood f){
        Pane pane = new Pane();
        
        TextArea StepTa = new TextArea();
        StepTa.setPrefWidth(751);
        StepTa.setPrefHeight(92);
        StepTa.setLayoutX(95);
        StepTa.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 16;");
        StepTa.setWrapText(true);
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
        delete.setOnAction((ActionEvent e) -> {
            try {
                stepIndex.add(DeleteStep(e,f)) ;          
            } catch (SQLException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delete.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: Bold; -fx-background-radius: 10;");
       
        
        pane.getChildren().add(StepTa);
        pane.getChildren().add(StepErr);
        pane.getChildren().add(txt);
        pane.getChildren().add(index);
        pane.getChildren().add(selectImg);
        pane.getChildren().add(delete);
 
        VboxStep.getChildren().add(pane);
        return pane;
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
    private int DeleteStep(ActionEvent event,LogicFood f) throws SQLException{
        Pane pane = (Pane) ((Button) event.getSource()).getParent();
        String s = ((TextArea)pane.getChildren().get(0)).getText();
        VboxStep.getChildren().remove(pane);
        Step.remove((TextArea) pane.getChildren().get(0));
        StepError.remove((Label) pane.getChildren().get(1));
        for(int i = 1 ; i < Step.size() ; i ++ ){
            ((Label)((Pane) Step.get(i).getParent()).getChildren().get(3)).setText(""+(i+1));
        }
        int a = 0;
        List<Step> steps = f.getStepsDB();
        for(int i = 0 ; i < steps.size() ; i++){
            if(steps.get(i).getContent().equals(s)){
                a = steps.get(i).getIdStep();
            }
        }    
        return a;
    }
    
    @FXML
    private void ChooseImageFood(ActionEvent event) throws MalformedURLException {
       
       stage = ((Stage) AddFoodView.getScene().getWindow());
       file = fileChooser.showOpenDialog(stage);
       Pane pane = (Pane)( (Button)event.getSource() ).getParent(); 
        
       if(file!=null){
           Button del  = new Button();
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold");
           del.setLayoutX(592);
           del.setLayoutY(0);
           del.setOnAction(e -> DeleteFoodImg(e));
           del.setCursor(Cursor.HAND);
           pane.getChildren().add(del);
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
         Button del  = new Button();
       
       if(file1!=null){
           
           ImageView view = new ImageView();
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
           del.setOnAction(e -> DeleteStepImg(e));
           del.setCursor(Cursor.HAND);
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
        
              
    }

    private void DeleteStepImg(ActionEvent event) {
        int i = 0 ;
        Pane pane = (Pane) ((Button)event.getSource()).getParent();
        if(pane.getChildren().size() == 7){
            i = 5;
           
        }
        if(pane.getChildren().size() == 8){
            i = 6;
        }
        pane.getChildren().remove(i);
        pane.getChildren().remove(i);
    }
    private void DeleteFoodImg(ActionEvent event){
        Pane pane = (Pane)( (Button)event.getSource() ).getParent();
        Button del = (Button) event.getSource();
        pane.getChildren().remove(del);
        imgFood1.setImage(null);
    }
    public void loadData(LogicFood food) throws SQLException, FileNotFoundException{
        function.setText("Sửa món ăn");
        txt_name.setText(food.getName());
        txt_time.setText("" + food.getCookTime());
        if(food.getLinkImg() != null){
            imgFood1.setImage(food.convertImg());
            Button del  = new Button();
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold");
           del.setLayoutX(592);
           del.setLayoutY(0);
           del.setOnAction(e -> DeleteFoodImg(e));
           del.setCursor(Cursor.HAND);
           foodImg.getChildren().add(del);
        }
        loaiMon.setText(food.getCategory());
        List<Ingredient> ingrs = food.getIngredientsDB();
        List<FoodAndIngredient> fais = new ArrayList<FoodAndIngredient>();
        FoodAndIngredientDAOImpl impl = new FoodAndIngredientDAOImpl();
        for(int i = 0 ; i < ingrs.size() ; i ++){
            fais.add(impl.get(food.getId(), ingrs.get(i).getId()));
        } 
        txt_ten_nl.setText(ingrs.get(0).getName());
        if(fais.get(0).getAmount() != null && fais.get(0).getUnit() != null){
            txt_soluong_nl.setText(""+format.format(fais.get(0).getAmount()));
            txt_donvitinh.setText(""+fais.get(0).getUnit());
        }
        for( int i = 1 ; i < ingrs.size() ; i ++){
            Pane pane = AddIngrLine();
            ((TextField)((Label)pane.getChildren().get(0)).getGraphic()).setText(ingrs.get(i).getName());
            if(fais.get(i).getAmount() != null && fais.get(i).getUnit() != null){
                ((TextField)((Label)pane.getChildren().get(2)).getGraphic()).setText((format.format(fais.get(i).getAmount())));
                ((TextField)((Label)pane.getChildren().get(4)).getGraphic()).setText(fais.get(i).getUnit());
            }
        }
        List<Step> steps = food.getStepsDB();
         txt_buoclam.setText(steps.get(0).getContent());
         if(steps.get(0).getLinkImg() != null){
             ImageView view = new ImageView();
                view.setPreserveRatio(false);
                view.setLayoutX(300);
                view.setLayoutY(130);
                view.setFitWidth(323);
                view.setFitHeight(189);
                view.setImage(steps.get(0).convertImg());
           
           Button del  = new Button();
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold");
           del.setLayoutX(623);
           del.setLayoutY(130);
           del.setOnAction(e -> DeleteStepImg(e));
           del.setCursor(Cursor.HAND);
           step1.getChildren().add(view);
           step1.getChildren().add(del);
         }
        for(int i = 1 ; i < steps.size() ; i ++){
            Pane pane = AddStep(food);
            ((TextArea)pane.getChildren().get(0)).setText(steps.get(i).getContent());
            if(steps.get(i).getLinkImg() != null){
                
                ImageView view = new ImageView();
                view.setPreserveRatio(false);
                view.setLayoutX(300);
                view.setLayoutY(130);
                view.setFitWidth(323);
                view.setFitHeight(189);
                view.setImage(steps.get(i).convertImg());
           
           Button del  = new Button();
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold");
           del.setLayoutX(623);
           del.setLayoutY(130);
           del.setOnAction(e -> DeleteStepImg(e));
           del.setCursor(Cursor.HAND);
   
            pane.getChildren().add(view);  
            pane.getChildren().add(del);
          
        }
        }
    }
    void UpdateFood(ActionEvent eh, LogicFood f) throws SQLException, IOException {
        PathDeleted pd = new PathDeleted();
        if(isValidate()){
            StepDAOImpl sImpl = new StepDAOImpl();
            FoodDAOImpl fImpl = new FoodDAOImpl();
            IngredientDAOImpl iImpl = new IngredientDAOImpl();
            FoodAndIngredientDAOImpl faiImpl = new FoodAndIngredientDAOImpl();
            List<Ingredient> ingrs = new ArrayList<Ingredient>();
            List<FoodAndIngredient> fais = new ArrayList<FoodAndIngredient>();
            List<Ingredient> ingrsDB = f.getIngredientsDB();
            List<Step> steps = f.getStepsDB();
            
        String name_ = txt_name.getText();
        int cooktime = Integer.parseInt(txt_time.getText());
        String imgFood_ = f.getLinkImg();
        if(imgFood1.getImage() != null){
            if(file != null){
                if(imgFood_ !=null){
                    pd.add(imgFood_);
                }
            Path src = file.toPath();
            Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+imgFood1.getImage()+".png");
            try {
                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                
            } catch (IOException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgFood_ = "./src/resource/imgs/"+txt_name.getText()+imgFood1.getImage()+".png";
            }
        }
        LogicFood food = new LogicFood(f.getId(), name_, cooktime, f.getCategory(), imgFood_);
        
        fImpl.update(food);
        for(int i = 0 ; i < ingrsDB.size() ; i ++){
                FoodAndIngredient a = faiImpl.get(f.getId(), ingrsDB.get(i).getId());   
                faiImpl.delete(a);
        }
        for(int i = 0 ; i < IngredientName.size() ; i++){
            Ingredient ingr_ = new Ingredient(1, IngredientName.get(i).getText());
            iImpl.save(ingr_);
            ingrs.add(iImpl.getIngr(IngredientName.get(i).getText()));
        }
        
        for(int i = 0 ; i < IngredientName.size() ; i++){
            double amount = Double.NaN;
            String unit = null;
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i))){
                amount = Double.parseDouble(IngredientAmount.get(i).getText());
            }
            if(TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))){
                unit = IngredientUnit.get(i).getText();
            }
            FoodAndIngredient fai =
            new FoodAndIngredient(f.getId(), ingrs.get(i).getId(),amount,unit);
            faiImpl.save(fai);
        }
        
        for(int i = 0 ; i < stepIndex.size(); i++){
            for(int j = 0 ; j < steps.size(); j ++){
                if(steps.get(j).getIdStep() == sImpl.get(stepIndex.get(i)).getIdStep()){
                    steps.remove(j);
                }
            }
            if(sImpl.get(stepIndex.get(i)).getLinkImg() != null){
                pd.add(sImpl.get(stepIndex.get(i)).getLinkImg());
            }
            sImpl.delete(sImpl.get(stepIndex.get(i)));
        }
        for(int i = 0 ; i < steps.size(); i++){
            
            String imgStep = null;
            Pane pane = (Pane) VboxStep.getChildren().get(i);
            Step step = steps.get(i);
            if(pane.getChildren().size()>6){
                imgStep = steps.get(i).getLinkImg();
                int a = 6;
            if(i==0){
                a = 5;
            }
            Image img = ((ImageView)pane.getChildren().get(a)).getImage();
            if(img.getUrl() != null){
                if(steps.get(i).getLinkImg()!=null){
                    pd.add(steps.get(i).getLinkImg());
                }
               Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
            Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+"_step"+(i+1)+img+".png");
            try {
                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgStep = "./src/resource/imgs/"+txt_name.getText()+"_step"+(i+1)+img+".png"; 
            }
            }else{
                if(steps.get(i).getLinkImg() != null){
                    pd.add(steps.get(i).getLinkImg());
                }
            }
            step.setContent(((TextArea)pane.getChildren().get(0)).getText());
            step.setLinkImg(imgStep);
            sImpl.update(step);
        }
        
        for(int i = steps.size() ; i < Step.size(); i ++){
            String imgStep = null;
            Pane pane = (Pane) VboxStep.getChildren().get( i );
            String content = ((TextArea)pane.getChildren().get(0)).getText();
            if(pane.getChildren().size() == 8){
                Image img = ((ImageView)pane.getChildren().get(6)).getImage();
            Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
            Path des = FileSystems.getDefault().getPath("./src/resource/imgs",txt_name.getText()+"_step"+(i+1)+img+".png");
            try {
                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgStep = "./src/resource/imgs/"+txt_name.getText()+"_step"+(i+1)+img+".png";
            }
            Step step = new Step(f.getId(), 0, content, imgStep);
            sImpl.insert(step);
        }
        AlertDialog.display("AddData", "Data Insert Successfully");
        FunctionsController.popRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
        Parent categoryView = loader.load();     
        AddFoodView.getScene().setRoot(categoryView);
        ((CategoryController) loader.getController()).init(loaiMon.getText());
        }
    }
 
}
