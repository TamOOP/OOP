/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.models;

/**
 *
 * @author Admin
 */
public class LogicMenu {
    private static int m_id;
    private static String m_name;
    
    public static void setMenuId(int id){
        LogicMenu.m_id=id;
    }
    public static void setMenuName(String name){
        LogicMenu.m_name=name;
    }
    public static int getMenuId(){
        return LogicMenu.m_id;
    }
    public static String getMenuName(){
        return LogicMenu.m_name;
    }
}
