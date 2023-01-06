/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.support;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
/**
 *
 * @author Admin
 */
public class TextFieldValidation {
    public static boolean isTextFieldNotEmpty(TextField tf){
        boolean b = false;
        if(tf.getText().length() != 0 || !tf.getText().isEmpty()){
            b = true;
        }
        return b;
    } 
    public static boolean isTextFieldNotEmpty(TextField tf, Label lb, String ErrorMess){
        boolean b = true;
        if(!isTextFieldNotEmpty(tf)){
            b = false;
            String msg = ErrorMess;    
            lb.setText(msg);
        }
        return b;
    } 
    public static boolean isTextFieldNotEmpty(TextArea ta){
        boolean b = false;
        if(ta.getText().length() != 0 || !ta.getText().isEmpty()){
            b = true;
        }
        return b;
    } 
    public static boolean isTextFieldNotEmpty(TextArea ta, Label lb, String ErrorMess){
        boolean b = true;
        if(!isTextFieldNotEmpty(ta)){
            b = false;
            String msg = ErrorMess;    
            lb.setText(msg);
        }
        return b;
    }
    public static boolean textFieldTypeNumber(TextField tf){
        boolean b = false;
        if(tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")){
            b = true;
        }
        return b;
    }
    public static boolean textFieldTypeNumber(TextField tf, Label lb, String mess){
        boolean b = true;
        if(!textFieldTypeNumber(tf) && isTextFieldNotEmpty(tf)){
            b = false;
            String msg = mess;
            lb.setText(msg);
        }
        return b;
    }
    public static boolean checkAmountAndUnit(TextField amount, TextField unit){
        boolean b = false;
        if(!isTextFieldNotEmpty(amount) && !isTextFieldNotEmpty(unit)){
            b = true;
        }
        if(isTextFieldNotEmpty(unit) && isTextFieldNotEmpty(amount)){
            b = true;
        }
        return b;
    }
    public static boolean textFieldTypeChar(TextField tf, Label lb, String mess){
        boolean b = true;
        if(!textFieldTypeChar(tf) && isTextFieldNotEmpty(tf)){
            b = false;
            String msg = mess;
            lb.setText(msg);
        }
        return b;
    }
    public static boolean textFieldTypeChar(TextField tf){
        boolean b = false;
        if(!tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")){
            b = true;
        }
        return b;
    }
}
