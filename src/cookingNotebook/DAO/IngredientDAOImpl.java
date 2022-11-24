package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cookingNotebook.models.Ingredient;

public class IngredientDAOImpl implements IngredientDAO {

	@Override
	public Ingredient get(int idIngredient) throws SQLException {
		Ingredient ingredient = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb2_Ingredient WHERE IngrID = ?";
		PreparedStatement ps = con.prepareCall(sql);
		ps.setInt(1, idIngredient);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			ingredient = new Ingredient(rs.getInt("IngrID"), rs.getString("IngrName"));
		}
		rs.close();
		ps.close();
		con.close();
		return ingredient;
	}

	@Override
	public List<Ingredient> getAll() throws SQLException {
		List<Ingredient> ingredients = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb2_Ingredient";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			ingredients = new ArrayList<Ingredient>();
			while(rs.next()) {
				ingredients.add(new Ingredient(rs.getInt("IngrID"), rs.getString("IngrName")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return ingredients;
	}

	@Override
	public int insert(Ingredient ingredient) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO Tb2_Ingredient (IngrName) VALUES (?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, ingredient.getName());	
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(Ingredient ingredient) throws SQLException {
		
	    return 0;
		
	}

	@Override
	public int save(Ingredient ingredient) throws SQLException {
		List<String> ingredients = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT IngrName FROM Tb2_Ingredient";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			ingredients = new ArrayList<>();
			while(rs.next()) {
				ingredients.add(rs.getString("IngrName"));
			}
		}
		rs.close();
		ps.close();
		con.close();
		if(ingredients.contains(ingredient.getName())) update(ingredient);
		else 
                    insert(ingredient);
		return 1;
	}

	@Override
	public int delete(Ingredient ingredient) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM Tb2_Ingredient WHERE IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, ingredient.getId());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public List<Ingredient> getIngredientList(int idFood) throws SQLException {
		List<Ingredient> ingredients  = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT IngrID, IngrName FROM GetFoodIngredient WHERE FoodID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, idFood);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			ingredients = new ArrayList<Ingredient>();
			while(rs.next()) {
				ingredients.add(new Ingredient(rs.getInt("IngrID"), rs.getString("IngrName")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return ingredients;
	}

	@Override
	public Ingredient getIngredient(String name) throws SQLException {
		Ingredient ingredient = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb2_Ingredient WHERE IngrName = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			while(rs.next()) {
				ingredient = new Ingredient(rs.getInt("IngrID"), rs.getString("IngrName"));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return ingredient;
	}
        public Ingredient getIngr(String name) throws SQLException{
            Connection con = FunctionsDatabase.getConnection();
            Ingredient Ingr = null;
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Tb2_Ingredient WHERE IngrName = ?");
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int ID = rs.getInt("IngrID");
                String iname = rs.getString("IngrName");     
                Ingr = new Ingredient(ID,iname);
            }
            rs.close();
            ps.close();
            con.close();
            return Ingr;
        }
	
}
