/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.support;

import cookingNotebook.models.LogicFood;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MenuSP {
    private static boolean handMenuState = false;
    private static boolean loadDB = true;

    public static boolean isLoadDB() {
        return loadDB;
    }

    public static void setLoadDB(boolean loadDB) {
        MenuSP.loadDB = loadDB;
    }
    private static String timeInDay;
    private static List<LogicFood> morning = new ArrayList<>();
    private static List<LogicFood> noon = new ArrayList<>();
    private static List<LogicFood> night = new ArrayList<>();
    public static boolean getHandMenuState(){
        return handMenuState;
    }
    public static void setHandMenuState(boolean b){
        handMenuState = b;
    }

    public static String getTimeInDay() {
        return timeInDay;
    }

    public static void setTimeInDay(String timeInDay) {
        MenuSP.timeInDay = timeInDay;
    }public static List<LogicFood> getMorning() {
        return morning;
    }

    public static void setMorning(List<LogicFood> morning) {
        MenuSP.morning = morning;
    }

    public static List<LogicFood> getNoon() {
        return noon;
    }

    public static void setNoon(List<LogicFood> noon) {
        MenuSP.noon = noon;
    }

    public static List<LogicFood> getNight() {
        return night;
    }

    public static void setNight(List<LogicFood> night) {
        MenuSP.night = night;
    }

    public static boolean isHandMenuState() {
        return handMenuState;
    }
    
}
