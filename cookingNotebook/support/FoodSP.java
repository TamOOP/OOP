/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.support;

import cookingNotebook.models.LogicFood;

/**
 *
 * @author Admin
 */
public class FoodSP {
    public static String name;
    public static LogicFood Lfood;
    public static boolean AddFood;
    public static String category;
    public static void setName(String name){
        FoodSP.name=name;
    }
    public static String getName(){
        return FoodSP.name;
    }
    public static void setFood(LogicFood food){
        FoodSP.Lfood=food;
    }
    public static LogicFood getFood(){
        return FoodSP.Lfood;
    }
}
