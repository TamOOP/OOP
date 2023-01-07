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
import cookingNotebook.support.FoodSP;
import cookingNotebook.models.LogicFood;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Objects;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    
    private final List<TextField> IngredientName = new ArrayList<>();
    private final List<Label> IngredientNameError = new ArrayList<>();
    private final List<TextField> IngredientUnit = new ArrayList<>();
    private final List<Label> IngredientUnitError = new ArrayList<>();
    private final List<TextField> IngredientAmount = new ArrayList<>();
    private final List<Label> IngredientAmountError = new ArrayList<>();
    private final List<Label> StepError = new ArrayList<>();
    private final List<TextArea> Step = new ArrayList<>();
    private final List<String> StepImgDB = new ArrayList<>();
   
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
    @FXML
    private Pane chooseFoodImg;
    @FXML
     MenuButton choiceBox;
    
    public void init(Label labelType) {
	this.loaiMon.setText(labelType.getText());
    }
    @FXML
        public void goBack(ActionEvent e) throws IOException {
            AddFoodView.getScene().setRoot(FunctionsController.popRoot());
	}
    public void loadFood() throws SQLException, IOException{
        Integer idFood = null;
        String nameFood = txt_name.getText();
        String category = null;
        Integer cooktime = 0;
        String imgFood = null;

        if (choiceBox.isVisible()) {
            category = choiceBox.getText().toUpperCase();
        } else {
            category = loaiMon.getText();
        }
        if (TextFieldValidation.isTextFieldNotEmpty(txt_time)) {
            cooktime = Integer.valueOf(txt_time.getText());
        }
        if (file != null) {
            Path src = file.toPath();
            Path des = FileSystems.getDefault().getPath("./src/resource/imgs", txt_name.getText() + ".png");

            try {
                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgFood = "./src/resource/imgs/" + txt_name.getText() + ".png";
        }
        LogicFood food = new LogicFood(idFood, nameFood, cooktime, category, imgFood);
        FoodDAOImpl i = new FoodDAOImpl();
        i.insert(food);
        food = i.getFood(txt_name.getText());//getid cho class Food

        String nameIngr = null;
        Integer idIngr = null;
        for (int a = 0; a < IngredientName.size(); a++) {
            nameIngr = IngredientName.get(a).getText();
            IngredientDAOImpl j = new IngredientDAOImpl();
            Ingredient ingr = new Ingredient(idIngr, nameIngr);
            j.save(ingr);
            ingr = j.getIngr(IngredientName.get(a).getText());

            int Fid = food.getId();
            int Iid = ingr.getId();
            Double Amount = Double.NaN;
            String Unit = null;
            if (TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(a))) {
                Unit = IngredientUnit.get(a).getText();
            }
            if (TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(a))) {
                Amount = Double.valueOf(IngredientAmount.get(a).getText());
            }
            FoodAndIngredient food_ingr = new FoodAndIngredient(Fid, Iid, Amount, Unit);
            FoodAndIngredientDAOImpl k = new FoodAndIngredientDAOImpl();

            k.insert(food_ingr);
        }

        for (int a = 0; a < Step.size(); a++) {

            idFood = food.getId();
            String Content = Step.get(a).getText();
            String imgStep = null;
            Pane pane = (Pane) Step.get(a).getParent();
            Integer indexStep = null;
            String content = ((TextArea) pane.getChildren().get(0)).getText();
            ImageView img_view = new ImageView();
            for (int j = 0; j < pane.getChildren().size(); j++) {
                if (pane.getChildren().get(j).getClass().getName()
                        .equals("javafx.scene.image.ImageView")) {
                    img_view = ((ImageView) pane.getChildren().get(j));
                }
            }
            if (img_view.getImage() != null) {
                if (img_view.getImage().getUrl() != null) {
                    Image img = img_view.getImage();
                    Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
                    Path des = FileSystems.getDefault().getPath("./src/resource/imgs", txt_name.getText() + "_step" + (a + 1) + img + ".png");
                    Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                    imgStep = "./src/resource/imgs/" + txt_name.getText() + "_step" + (a + 1) + img + ".png";
                }
            }
            Step step = new Step(food.getId(), 0, content, imgStep);
            StepDAOImpl l = new StepDAOImpl();
            l.insert(step);

        }
    }
    @FXML
    public void loadFoodToDatabase_mainView(ActionEvent e) throws SQLException, IOException {
        if (isValidate() && !isFoodNameExist()) {
            if (choiceBox.isVisible()) {
                String category = choiceBox.getText();
                if (category.equals("Chọn loại món")) {
                    AlertDialog.display("Thất bại", "Hãy chọn loại món ăn");
                }else{
                    loadFood();
                    AlertDialog.display("AddData", "Thêm thành công");
                    AddFoodView.getScene().setRoot(FunctionsController.popRoot());
                }
            }
            
        }
    }
    @FXML 
    private void loadFoodToDatabase(ActionEvent e) throws SQLException, IOException{
        
        if (isValidate() && !isFoodNameExist()) {
            loadFood();
            FunctionsController.popRoot();
            AlertDialog.display("AddData", "Thêm thành công");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/CategoryView.fxml"));
            Parent categoryView = loader.load();
            AddFoodView.getScene().setRoot(categoryView);
        }
    }
    
    private boolean isFoodNameExist() throws SQLException {
        boolean b = false;
        FoodDAOImpl fImpl = new FoodDAOImpl();
        List<LogicFood> foods = fImpl.getAll();
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).getName().equals(txt_name.getText()) && TextFieldValidation.isTextFieldNotEmpty(txt_name)) {
                b = true;
                error_food.setText("Tên món ăn đã tồn tại");
                AlertDialog.display("Thất bại", "Tên món ăn đã tồn tại. Hãy nhập tên khác");
            }
        }
        return b;
    }

    private boolean isValidate() throws SQLException {
        boolean b = false;
        boolean isFoodNameValidate = false;

        if (TextFieldValidation.isTextFieldNotEmpty(txt_name)) {
            isFoodNameValidate = true;
            boolean isAlliNameNotEmpty = false;
            int t = 0;
            for (int i = 0; i < IngredientName.size(); i++) {
                if (!TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i))) {
                    TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i));
                    t = 1;
                }
            }
            if (t != 1) {
                isAlliNameNotEmpty = true;
                boolean isAllStepNotEmpty = false;
                t = 0;
                for (int i = 0; i < Step.size(); i++) {
                    if (!TextFieldValidation.isTextFieldNotEmpty(Step.get(i))) {
                        TextFieldValidation.isTextFieldNotEmpty(Step.get(i));
                        t = 1;
                    }
                }
                if (t != 1) {
                    isAllStepNotEmpty = true;

                    t = 0;
                    boolean isValidate = false;
                    for (int i = 0; i < IngredientName.size(); i++) {
                        if (!TextFieldValidation.checkAmountAndUnit(IngredientAmount.get(i), IngredientUnit.get(i))) {

                            t = 1;
                        }
                        if (TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                            boolean f = (TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i)) && TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i)));
                            if (!f) {
                                t = 1;
                            }

                        }
                    }
                    if (t != 1) {
                        isValidate = true;
                        if(TextFieldValidation.isTextFieldNotEmpty(txt_time) && TextFieldValidation.textFieldTypeNumber(txt_time)){
                            b = true;
                        }
                        if(!TextFieldValidation.isTextFieldNotEmpty(txt_time)){
                            b = true;
                        }
                        if(TextFieldValidation.isTextFieldNotEmpty(txt_time) && !TextFieldValidation.textFieldTypeNumber(txt_time)){
                            AlertDialog.display("Thất bại", "Thời gian nấu là chữ số");
                        }
                    }else{
                        AlertDialog.display("Thất bại", "Hãy nhập đúng số lượng và đơn vị nguyên liệu");
                    }
                }else{
                    AlertDialog.display("Thất bại", "Hãy nhập đủ bước làm");
                }
            }else{
                AlertDialog.display("Thất bại", "Hãy nhập đủ tên nguyên liệu");
            }
            
        }else{
            AlertDialog.display("Thất bại","Hãy nhập tên món ăn");
        }

        
        return b;
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
    
    private void CheckTextField() throws SQLException{
        FoodDAOImpl fImpl = new FoodDAOImpl();
        List<LogicFood> foods = fImpl.getAll();
        if("Thêm món ăn".equals(function.getText())){
            boolean isNameExist = false;
            if (!TextFieldValidation.isTextFieldNotEmpty(txt_name)){
                TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food, 
                        "Tên món ăn không được trống");
            }
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getName().equalsIgnoreCase(txt_name.getText())
                        && TextFieldValidation.isTextFieldNotEmpty(txt_name)) {
                    isNameExist = true;
                    error_food.setText("Tên món ăn đã tồn tại"); 
                }
            }
            if (TextFieldValidation.isTextFieldNotEmpty(txt_name)
                    && !isNameExist){
                error_food.setText(null);
            }
        }else {
//            if (TextFieldValidation.isTextFieldNotEmpty(txt_name)) {
//                error_food.setText(null);
//            } else {
//                TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food, "Tên món ăn không được trống");
//            }
            boolean isNameExist = false;
            if (!TextFieldValidation.isTextFieldNotEmpty(txt_name)) {
                TextFieldValidation.isTextFieldNotEmpty(txt_name, error_food,
                        "Tên món ăn không được trống");
            }
            for (int i = 0; i < foods.size(); i++) {
                if (foods.get(i).getName().equalsIgnoreCase(txt_name.getText())
                        && TextFieldValidation.isTextFieldNotEmpty(txt_name)
                       && ( !txt_name.getText().equals(FoodSP.getName())) ) {
                    isNameExist = true;
                    error_food.setText("Tên món ăn đã tồn tại");
                }
            }
            if (TextFieldValidation.isTextFieldNotEmpty(txt_name)
                    && !isNameExist) {
                error_food.setText(null);
            }
        }
        if (TextFieldValidation.isTextFieldNotEmpty(txt_time)) {
            TextFieldValidation.textFieldTypeNumber(txt_time, error_time, "Nhập kí tự số");
            if (TextFieldValidation.textFieldTypeNumber(txt_time)) {
                error_time.setText(null);
            }
        } else {
            error_time.setText(null);
        }

        for (int i = 0; i < IngredientName.size(); i++) {
            if (TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i))) {
                IngredientNameError.get(i).setText(null);
            } else {
                TextFieldValidation.isTextFieldNotEmpty(IngredientName.get(i), IngredientNameError.get(i), "Nhập tên nguyên liệu");
            }
        }

        for (int i = 0; i < Step.size(); i++) {
            if (TextFieldValidation.isTextFieldNotEmpty(Step.get(i))) {
                StepError.get(i).setText(null);
            } else {
                boolean isStepEmpty = TextFieldValidation.isTextFieldNotEmpty(Step.get(i), StepError.get(i), "Bước làm không được để trống");
            }
        }
        for (int i = 0; i < IngredientName.size(); i++) {
            if (!TextFieldValidation.checkAmountAndUnit(IngredientAmount.get(i), IngredientUnit.get(i))) {
                if (TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                    IngredientUnitError.get(i).setText("Hãy nhập đơn vị tính");
                    if (!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))) {
                        IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                    } else {
                        IngredientAmountError.get(i).setText(null);
                    }
                }
                if (!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                    IngredientAmountError.get(i).setText("Hãy nhập số lượng");
                    if (!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))) {
                        IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                    } else {
                        IngredientUnitError.get(i).setText(null);
                    }
                }
            }
            if (TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                if (!TextFieldValidation.textFieldTypeNumber(IngredientAmount.get(i))) {
                    IngredientAmountError.get(i).setText("Hãy nhập kí tự số");
                } else {
                    IngredientAmountError.get(i).setText(null);
                }
                if (!TextFieldValidation.textFieldTypeChar(IngredientUnit.get(i))) {
                    IngredientUnitError.get(i).setText("Hãy nhập kí tự chữ");
                } else {
                    IngredientUnitError.get(i).setText(null);
                }

            }
            if (!TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i)) && !TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                IngredientAmountError.get(i).setText(null);
                IngredientUnitError.get(i).setText(null);
            }
        }
    }
    @FXML
    private void checkTextField(KeyEvent event) throws SQLException {
        CheckTextField();
    }
    @FXML
    private void checkTextField1(MouseEvent event) throws SQLException {
//        CheckTextField();
    }
    @FXML
    private void ChooseImageFood(MouseEvent event) throws MalformedURLException {
       
       stage = ((Stage) AddFoodView.getScene().getWindow());
       file = fileChooser.showOpenDialog(stage);
       Pane pane = (Pane) ((Pane)(event.getSource())).getParent(); 
        
       if(file!=null){
           chooseFoodImg.setVisible(false);
           Button del  = new Button();
           del.setText("x");
           del.setPrefWidth(3);
           del.setPrefHeight(3);
           del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold;"
                   + "-fx-background-radius: 20");
           del.setLayoutX(494);
           del.setLayoutY(-10);
           del.setOnAction(e -> DeleteFoodImg(e));
           del.setCursor(Cursor.HAND);
           pane.getChildren().add(del);
           Image img = new Image(file.getAbsoluteFile().toURL().toString());       
           imgFood1.setImage(img);
           imgFood1.setVisible(true);
       }
    } 
    private void DeleteFoodImg(ActionEvent event){
        Button del = (Button) event.getSource();
        Pane pane = (Pane)del.getParent();
        pane.getChildren().remove(del);
        imgFood1.setImage(null);
        imgFood1.setVisible(false);
        chooseFoodImg.setVisible(true);
    }
    @FXML
    private void loadCategory(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Pane pane = (Pane) mi.getGraphic();
        String category = ((Label) pane.getChildren().get(0)).getText();
        choiceBox.setText(category);
        loaiMon.setText(category.toUpperCase());
    }
    @FXML
    private void AddIngrLine(ActionEvent event) throws FileNotFoundException{
        Pane pane = AddIngrLine();
    }
    private Pane AddIngrLine() throws FileNotFoundException{
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
        IngrAmountTf.setOnKeyReleased(e -> {
            try {
                checkTextField(e);
            } catch (SQLException ex) {
            }
        });
        IngredientAmount.add(IngrAmountTf);
        Label IngrAmountLbError = new Label();
        IngrAmountLbError.setStyle("-fx-text-fill:red");
        IngrAmountLbError.setLayoutX(338);
        IngrAmountLbError.setLayoutY(37);
        IngredientAmountError.add(IngrAmountLbError);
        Label IngrAmountLb = new Label();
        IngrAmountLb.setText("Số lượng:");
        IngrAmountLb.setContentDisplay(ContentDisplay.RIGHT);
        IngrAmountLb.setStyle("-fx-font-size:18;");
        IngrAmountLb.setGraphic(IngrAmountTf);
        IngrAmountLb.setLayoutX(256);
        pane.getChildren().add(IngrAmountLb);
        pane.getChildren().add(IngrAmountLbError);

        TextField IngrUnitTf = new TextField();
        IngrUnitTf.setPrefWidth(173);
        IngrUnitTf.setPrefHeight(30);
        IngrUnitTf.setStyle("-fx-font-size:16");
        IngrUnitTf.setOnKeyReleased(e -> {
            try {
                checkTextField(e);
            } catch (SQLException ex) {
            }
        });
        IngredientUnit.add(IngrUnitTf);
        Label IngrUnitLbError = new Label();
        IngrUnitLbError.setStyle("-fx-text-fill:red");
        IngrUnitLbError.setLayoutX(666);
        IngrUnitLbError.setLayoutY(37);
        IngredientUnitError.add(IngrUnitLbError);
        Label IngrUnitLb = new Label();
        IngrUnitLb.setText("Đơn vị tính:");
        IngrUnitLb.setContentDisplay(ContentDisplay.RIGHT);
        IngrUnitLb.setStyle("-fx-font-size:18;");
        IngrUnitLb.setGraphic(IngrUnitTf);
        IngrUnitLb.setLayoutX(567);
        pane.getChildren().add(IngrUnitLb);
        pane.getChildren().add(IngrUnitLbError);

        InputStream inputstream = new FileInputStream("./src/resource/trash.png");
        ImageView delete_ico = new ImageView(new Image(inputstream));
        delete_ico.setPreserveRatio(false);
        delete_ico.setFitWidth(20);
        delete_ico.setFitHeight(22);
        AnchorPane delete = new AnchorPane();
        delete.setLayoutX(890);
        delete.setLayoutY(7);
        delete.setPrefWidth(20);
        delete.setPrefHeight(22);
        delete.setCursor(Cursor.HAND);
        delete.setOnMouseClicked(eh -> {
            DeleteIngrLine(eh);
        });
        delete.getChildren().add(delete_ico);
        pane.getChildren().add(delete);
        return pane;
    }
    private void DeleteIngrLine(MouseEvent event) {
        Pane pane = (Pane) ((Pane) (event.getSource())).getParent();
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
    private void AddStep(ActionEvent event) throws FileNotFoundException{
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
        
        Pane selectImg_pane = new Pane();
        selectImg_pane.setLayoutX(305);
        selectImg_pane.setLayoutY(109);
        selectImg_pane.setCursor(Cursor.HAND);
        selectImg_pane.setPrefHeight(158);
        selectImg_pane.setPrefWidth(200);
        selectImg_pane.setStyle("-fx-border-color: #768591;-fx-border-style: dashed");
        selectImg_pane.setOnMouseClicked(e -> {
            try {
                chooseStepImg(e);
            } catch (MalformedURLException ex) {}
        });
        Label plus = new Label();
        plus.setText("+");
        plus.setStyle("-fx-font-size:48px");
        plus.setTextFill(new Color(0.4627, 0.5216, 0.5686, 1.0));
        plus.setLayoutX(84);
        plus.setLayoutY(44);
        selectImg_pane.getChildren().add(plus);
        
        Label StepErr = new Label();
        StepErr.setStyle("-fx-text-fill:red");
        StepErr.setLayoutX(101);
        StepErr.setLayoutY(92);
        StepError.add(StepErr);
        
        Label txt_img = new Label();
        txt_img.setText("Ảnh bước làm: ");
        txt_img.setLayoutX(95);
        txt_img.setLayoutY(124);
        txt_img.setStyle("-fx-font-family:Times New Roman;-fx-font-size:18px");
        txt_img.setTextFill(new Color(0.4627, 0.5216, 0.5686, 1.0));
        
        InputStream inputstream = new FileInputStream("./src/resource/trash.png");
        ImageView delete_ico = new ImageView(new Image(inputstream));
        delete_ico.setPreserveRatio(false);
        delete_ico.setFitWidth(20);
        delete_ico.setFitHeight(22);
        AnchorPane delete = new AnchorPane();
        delete.setLayoutX(892);
        delete.setLayoutY(35);
        delete.setPrefWidth(20);
        delete.setPrefHeight(22);
        delete.setCursor(Cursor.HAND);
        delete.setOnMouseClicked(eh -> {
            DeleteStep(eh);
        });
        delete.getChildren().add(delete_ico);
        
        pane.getChildren().add(StepTa);
        pane.getChildren().add(StepErr);
        pane.getChildren().add(txt);
        pane.getChildren().add(index);
        pane.getChildren().add(selectImg_pane);
        pane.getChildren().add(delete);
        pane.getChildren().add(txt_img);
        
        VboxStep.getChildren().add(pane);
        
        
    }
    List<Integer> stepIndex = new ArrayList<>();
    private Pane AddStep(LogicFood f) throws FileNotFoundException{
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
        
        Pane selectImg_pane = new Pane();
        selectImg_pane.setLayoutX(305);
        selectImg_pane.setLayoutY(109);
        selectImg_pane.setCursor(Cursor.HAND);
        selectImg_pane.setPrefHeight(158);
        selectImg_pane.setPrefWidth(200);
        selectImg_pane.setStyle("-fx-border-color: #768591;-fx-border-style: dashed");
        selectImg_pane.setOnMouseClicked(e -> {
            try {
                chooseStepImg(e);
            } catch (MalformedURLException ex) {}
        });
        Label plus = new Label();
        plus.setText("+");
        plus.setStyle("-fx-font-size:48px");
        plus.setTextFill(new Color(0.4627, 0.5216, 0.5686, 1.0));
        plus.setLayoutX(84);
        plus.setLayoutY(44);
        selectImg_pane.getChildren().add(plus);
        
        Label StepErr = new Label();
        StepErr.setStyle("-fx-text-fill:red");
        StepErr.setLayoutX(101);
        StepErr.setLayoutY(92);
        StepError.add(StepErr);
        
        Label txt_img = new Label();
        txt_img.setText("Ảnh bước làm: ");
        txt_img.setLayoutX(95);
        txt_img.setLayoutY(124);
        txt_img.setStyle("-fx-font-family:Times New Roman;-fx-font-size:18px");
        txt_img.setTextFill(new Color(0.4627, 0.5216, 0.5686, 1.0));
        
        InputStream inputstream = new FileInputStream("./src/resource/trash.png");
        ImageView delete_ico = new ImageView(new Image(inputstream));
        delete_ico.setPreserveRatio(false);
        delete_ico.setFitWidth(20);
        delete_ico.setFitHeight(22);
        AnchorPane delete = new AnchorPane();
        delete.setLayoutX(892);
        delete.setLayoutY(35);
        delete.setPrefWidth(20);
        delete.setPrefHeight(22);
        delete.setCursor(Cursor.HAND);
        delete.setOnMouseClicked(eh -> {
            try {
                stepIndex.add(DeleteStep(eh,f)) ;
            } catch (SQLException ex) {}
        });
        delete.getChildren().add(delete_ico);
        
        pane.getChildren().add(StepTa);
        pane.getChildren().add(StepErr);
        pane.getChildren().add(txt);
        pane.getChildren().add(index);
        pane.getChildren().add(selectImg_pane);
        pane.getChildren().add(delete);
        pane.getChildren().add(txt_img);
 
        VboxStep.getChildren().add(pane);
        return pane;
    }
    private void DeleteStep(MouseEvent event){
        Pane pane = (Pane) ((Pane) event.getSource()).getParent();
        VboxStep.getChildren().remove(pane);
        Step.remove((TextArea) pane.getChildren().get(0));
        StepError.remove((Label) pane.getChildren().get(1));
        for(int i = 1 ; i < Step.size() ; i ++ ){
            ((Label)((Pane) Step.get(i).getParent()).getChildren().get(3)).setText(""+(i+1));
        }
    }
    private int DeleteStep(MouseEvent event,LogicFood f) throws SQLException{
        Pane pane = (Pane) ((Pane) event.getSource()).getParent();
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
    private void chooseStepImg(MouseEvent event) throws MalformedURLException {
        stage = ((Stage) AddFoodView.getScene().getWindow());
        file1 = fileChooser.showOpenDialog(stage);
        Pane pane = (Pane) ((Pane) event.getSource()).getParent();
        Button del = new Button();

        if (file1 != null) {
            Pane hide = (Pane) event.getSource();
            hide.setVisible(false);
            ImageView view = new ImageView();
            view.setPreserveRatio(false);
            view.setLayoutX(305);
            view.setLayoutY(109);
            view.setFitWidth(200);
            view.setFitHeight(153);
            Image img = new Image(file1.getAbsoluteFile().toURL().toString());
            view.setImage(img);

            del.setText("x");
            del.setPrefWidth(3);
            del.setPrefHeight(3);
            del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold;"
                    + "-fx-background-radius: 20");
            del.setLayoutX(494);
            del.setLayoutY(97);
            del.setOnAction(e -> DeleteStepImg(e));
            del.setCursor(Cursor.HAND);
            pane.getChildren().add(view);
            pane.getChildren().add(del);
        }
    }

    private void DeleteStepImg(ActionEvent event) {
        Pane pane = (Pane) ((Button)event.getSource()).getParent();
        Pane hidden = new Pane();
        ImageView img = new ImageView();
        Button btn = new Button();
        for(int i = 0; i< pane.getChildren().size(); i++){
            String class_name = pane.getChildren().get(i).getClass().getName();
            if(class_name.equals("javafx.scene.layout.Pane")){
                hidden = (Pane) pane.getChildren().get(i);
            }
            if(class_name.equals("javafx.scene.image.ImageView")){
                img = (ImageView) pane.getChildren().get(i);
            }
            if(class_name.equals("javafx.scene.control.Button")){
                btn = (Button) pane.getChildren().get(i);
            }
        }
        hidden.setVisible(true);
        pane.getChildren().remove(btn);
        pane.getChildren().remove(img);
    }
    
    public void loadData(LogicFood food) throws SQLException, FileNotFoundException {
        function.setText("Sửa món ăn");
        txt_name.setText(food.getName());
        txt_time.setText("" + food.getCookTime());
        if (food.getLinkImg() != null) {
            chooseFoodImg.setVisible(false);
            imgFood1.setImage(food.convertImg());
            imgFood1.setVisible(true);
            Button del = new Button();
            del.setText("x");
            del.setPrefWidth(3);
            del.setPrefHeight(3);
            del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold;"
                    + "-fx-background-radius: 20");
            del.setLayoutX(494);
            del.setLayoutY(-10);
            del.setOnAction(e -> DeleteFoodImg(e));
            del.setCursor(Cursor.HAND);
            foodImg.getChildren().add(del);
        }
        loaiMon.setText(food.getCategory());
        List<Ingredient> ingrs = food.getIngredientsDB();
        List<FoodAndIngredient> fais = new ArrayList<>();
        FoodAndIngredientDAOImpl impl = new FoodAndIngredientDAOImpl();
        for (int i = 0; i < ingrs.size(); i++) {
            fais.add(impl.get(food.getId(), ingrs.get(i).getId()));
        }
        txt_ten_nl.setText(ingrs.get(0).getName());
        if (fais.get(0).getAmount() != null && fais.get(0).getUnit() != null) {
            txt_soluong_nl.setText("" + format.format(fais.get(0).getAmount()));
            txt_donvitinh.setText("" + fais.get(0).getUnit());
        }
        for (int i = 1; i < ingrs.size(); i++) {
            Pane pane = AddIngrLine();
            ((TextField) ((Label) pane.getChildren().get(0)).getGraphic()).setText(ingrs.get(i).getName());
            if (fais.get(i).getAmount() != null && fais.get(i).getUnit() != null) {
                ((TextField) ((Label) pane.getChildren().get(2)).getGraphic()).setText((format.format(fais.get(i).getAmount())));
                ((TextField) ((Label) pane.getChildren().get(4)).getGraphic()).setText(fais.get(i).getUnit());
            }
        }
        Pane hide = new Pane();
        for(int i = 0 ; i<step1.getChildren().size();i++){
            if(step1.getChildren().get(i).getClass().getName()
                    .equals("javafx.scene.layout.Pane;")){
                hide = (Pane) step1.getChildren().get(i);
            }
        }
        List<Step> steps = food.getStepsDB();
        txt_buoclam.setText(steps.get(0).getContent());
        if (steps.get(0).getLinkImg() != null) {
            hide.setVisible(false);
            ImageView view = new ImageView();
            view.setPreserveRatio(false);
            view.setLayoutX(305);
            view.setLayoutY(109);
            view.setFitWidth(200);
            view.setFitHeight(158);
            view.setImage(steps.get(0).convertImg());

            Button del = new Button();
            del.setText("x");
            del.setPrefWidth(3);
            del.setPrefHeight(3);
            del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold;"
                    + "-fx-background-radius: 20");
            del.setLayoutX(494);
            del.setLayoutY(97);
            del.setOnAction(e -> DeleteStepImg(e));
            del.setCursor(Cursor.HAND);
            step1.getChildren().add(view);
            step1.getChildren().add(del);
        }
        for (int i = 1; i < steps.size(); i++) {
            Pane pane = AddStep(food);
            ((TextArea) pane.getChildren().get(0)).setText(steps.get(i).getContent());
            for (int j = 0; j < pane.getChildren().size(); j++) {
                if (pane.getChildren().get(j).getClass().getName()
                        .equals("javafx.scene.layout.Pane;")) {
                    hide = (Pane) pane.getChildren().get(j);
                }
            }
            if (steps.get(i).getLinkImg() != null) {
                hide.setVisible(false);
                ImageView view = new ImageView();
                view.setPreserveRatio(false);
                view.setLayoutX(305);
                view.setLayoutY(109);
                view.setFitWidth(200);
                view.setFitHeight(158);
                view.setImage(steps.get(i).convertImg());

                Button del = new Button();
                del.setText("x");
                del.setPrefWidth(3);
                del.setPrefHeight(3);
                del.setStyle("-fx-text-fill: white; -fx-background-color: red; -fx-font-weight: bold;"
                        + "-fx-background-radius: 20");
                del.setLayoutX(494);
                del.setLayoutY(97);
                del.setOnAction(e -> DeleteStepImg(e));
                del.setCursor(Cursor.HAND);

                pane.getChildren().add(view);
                pane.getChildren().add(del);

            }
        }
    }
    void UpdateFood(ActionEvent eh, LogicFood f) throws SQLException, IOException {
        PathDeleted pd = new PathDeleted();
        String category = choiceBox.getText();
        if(!FoodSP.getName().equalsIgnoreCase(txt_name.getText()) && isFoodNameExist()){
            
        }else{
            if (isValidate()) {
                StepDAOImpl sImpl = new StepDAOImpl();
                FoodDAOImpl fImpl = new FoodDAOImpl();
                IngredientDAOImpl iImpl = new IngredientDAOImpl();
                FoodAndIngredientDAOImpl faiImpl = new FoodAndIngredientDAOImpl();
                List<Ingredient> ingrs = new ArrayList<>();
                List<FoodAndIngredient> fais = new ArrayList<>();
                List<Ingredient> ingrsDB = f.getIngredientsDB();
                List<Step> steps = f.getStepsDB();

                String name_ = txt_name.getText();
                FoodSP.setName(name_);
                int cooktime = Integer.parseInt(txt_time.getText());
                String imgFood_ = f.getLinkImg();
                if (imgFood1.getImage() != null) {
                    if (file != null) {
                        if (imgFood_ != null) {
                            pd.add(imgFood_);
                        }
                        Path src = file.toPath();
                        Path des = FileSystems.getDefault().getPath("./src/resource/imgs", txt_name.getText() + imgFood1.getImage() + ".png");
                        try {
                            Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);

                        } catch (IOException ex) {
                            Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        imgFood_ = "./src/resource/imgs/" + txt_name.getText() + imgFood1.getImage() + ".png";
                    }
                } else {
                    imgFood_ = null;
                }
                LogicFood food = new LogicFood(f.getId(), name_, cooktime, category.toUpperCase(), imgFood_);

                fImpl.update(food);
                for (int i = 0; i < ingrsDB.size(); i++) {
                    FoodAndIngredient a = faiImpl.get(f.getId(), ingrsDB.get(i).getId());
                    faiImpl.delete(a);
                }
                for (int i = 0; i < IngredientName.size(); i++) {
                    Ingredient ingr_ = new Ingredient(1, IngredientName.get(i).getText());
                    iImpl.save(ingr_);
                    ingrs.add(iImpl.getIngr(IngredientName.get(i).getText()));
                    double amount = Double.NaN;
                    String unit = null;
                    if (TextFieldValidation.isTextFieldNotEmpty(IngredientAmount.get(i))) {
                        amount = Double.parseDouble(IngredientAmount.get(i).getText());
                    }
                    if (TextFieldValidation.isTextFieldNotEmpty(IngredientUnit.get(i))) {
                        unit = IngredientUnit.get(i).getText();
                    }
                    FoodAndIngredient fai
                            = new FoodAndIngredient(f.getId(), ingrs.get(i).getId(), amount, unit);
                    faiImpl.save(fai);
                }
                for (int i = 0; i < stepIndex.size(); i++) {
                    for (int j = 0; j < steps.size(); j++) {
                        if (Objects.equals(steps.get(j).getIdStep(), sImpl.get(stepIndex.get(i)).getIdStep())) {
                            steps.remove(j);
                        }
                    }
                    if (sImpl.get(stepIndex.get(i)).getLinkImg() != null) {
                        pd.add(sImpl.get(stepIndex.get(i)).getLinkImg());
                    }
                    sImpl.delete(sImpl.get(stepIndex.get(i)));
                }
                for (int i = 0; i < steps.size(); i++) {

                    String imgStep = steps.get(i).getLinkImg();
                    Pane pane = (Pane) VboxStep.getChildren().get(i);
                    Step step = steps.get(i);
                    ImageView img_view = new ImageView();
                    for (int j = 0; j < pane.getChildren().size(); j++) {
                        if (pane.getChildren().get(j).getClass().getName()
                                .equals("javafx.scene.image.ImageView")) {
                            img_view = ((ImageView) pane.getChildren().get(j));
                        }
                    }
                    if (img_view.getImage() != null) {
                        if (img_view.getImage().getUrl() != null) {
                            Image img = img_view.getImage();
                            if (steps.get(i).getLinkImg() != null) {
                                pd.add(steps.get(i).getLinkImg());
                            }
                            Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
                            Path des = FileSystems.getDefault().getPath("./src/resource/imgs", txt_name.getText() + "_step" + (i + 1) + img + ".png");
                            try {
                                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException ex) {
                                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            imgStep = "./src/resource/imgs/" + txt_name.getText() + "_step" + (i + 1) + img + ".png";
                        }
                    } else {
                        imgStep = null;
                    }
                    if (steps.get(i).getLinkImg() != null) {
                        pd.add(steps.get(i).getLinkImg());
                    }
                    step.setContent(((TextArea) pane.getChildren().get(0)).getText());
                    step.setLinkImg(imgStep);
                    sImpl.update(step);
                }

                for (int i = steps.size(); i < Step.size(); i++) {
                    String imgStep = null;
                    Pane pane = (Pane) VboxStep.getChildren().get(i);
                    String content = ((TextArea) pane.getChildren().get(0)).getText();
                    ImageView img_view = new ImageView();
                    for (int j = 0; j < pane.getChildren().size(); j++) {
                        if (pane.getChildren().get(j).getClass().getName()
                                .equals("javafx.scene.image.ImageView")) {
                            img_view = ((ImageView) pane.getChildren().get(j));
                        }
                    }
                    if (img_view.getImage() != null) {
                        if (img_view.getImage().getUrl() != null) {
                            Image img = img_view.getImage();
                            Path src = FileSystems.getDefault().getPath(img.getUrl().substring(6));
                            Path des = FileSystems.getDefault().getPath("./src/resource/imgs", txt_name.getText() + "_step" + (i + 1) + img + ".png");
                            try {
                                Files.copy(src, des, StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException ex) {
                                Logger.getLogger(AddFoodViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            imgStep = "./src/resource/imgs/" + txt_name.getText() + "_step" + (i + 1) + img + ".png";
                        }
                    }
                    Step step = new Step(f.getId(), 0, content, imgStep);
                    sImpl.insert(step);
                }

                AlertDialog.display("AddData", "Sửa thành công");
                FunctionsController.popRoot();
                FoodSP.setFood(null);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/FoodView.fxml"));
                Parent foodView = loader.load();
                FoodViewController c = loader.getController();
                c.back_pane.setOnMouseClicked(e -> {
                    try {
                        c.goBackAndLoad(e);
                    } catch (IOException ex) {
                    }
                });
                AddFoodView.getScene().setRoot(foodView);
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!FoodSP.AddFood){
            try {
                loadData(FoodSP.getFood());
            } catch (SQLException | FileNotFoundException ex) {  }
            btn_save.setOnAction(eh -> {
                try {
                    UpdateFood(eh, FoodSP.getFood());
                } catch (SQLException | IOException ex) {}
            });
        }
        try {
            CheckTextField();
        } catch (SQLException ex) {}
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

    
}
