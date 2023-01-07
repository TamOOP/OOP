/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cookingNotebook.DAO;

import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Ingredient;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface MenuDAO extends DAO<Menu> {
	public List<Menu_Ingredient> getIngredient(int mid) throws SQLException;
}
