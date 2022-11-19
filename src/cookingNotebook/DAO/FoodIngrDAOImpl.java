package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FoodIngrDAOImpl implements FoodIngrDAO {

	// DAO's methods
	@Override
	public FoodIngr get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FoodIngr> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(FoodIngr t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(FoodIngr t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO R1_Food_Ingredient VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getFid());
		ps.setInt(2, t.getIid());
		ps.setDouble(3, t.getAmount());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(FoodIngr t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "UPDATE R1_Food_Ingredient SET Amount = ? WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, t.getAmount());
		ps.setInt(2, t.getFid());
		ps.setInt(3, t.getIid());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int delete(FoodIngr t) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, t.getFid());
		ps.setInt(2, t.getIid());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	// Added methods
	@Override
	public FoodIngr get(int fid, int iid) throws SQLException {
		Connection con = Database.getConnection();
		FoodIngr fi = null;
		String sql = "SELECT * FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fid);
		ps.setInt(2, iid);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int id1 = rs.getInt("FoodID");
			int id2 = rs.getInt("IngrID");
			Double amount = rs.getDouble("Amount");
			fi = new FoodIngr(id1, id2, amount);
		}
		rs.close();
		ps.close();
		con.close();
		return fi;
	}

	@Override
	public int insertAmount(int fid, int iid, double amount) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "INSERT INTO R1_Food_Ingredient VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fid);
		ps.setInt(2, iid);
		ps.setDouble(3, amount);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int removeAmount(int fid, int iid) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "DELETE FROM R1_Food_Ingredient WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, fid);
		ps.setInt(2, iid);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int updateAmount(int fid, int iid, double amount) throws SQLException {
		Connection con = Database.getConnection();
		String sql = "UPDATE R1_Food_Ingredient SET Amount = ? WHERE FoodID = ? AND IngrID = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, amount);
		ps.setInt(2, fid);
		ps.setInt(3, iid);
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}
	
}