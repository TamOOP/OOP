package cookingNotebook.DAO;

import cookingNotebook.models.Food;
import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Food;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Menu_FoodDAOimpl implements Menu_FoodDAO {

	@Override
	public Menu_Food get(int id) throws SQLException {
		
	    return null;
		
	}

	@Override
	public List<Menu_Food> getAll() throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		List<Menu_Food> mf = null;
		String sql = "SELECT * FROM R2_Menu_Food";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			mf = new ArrayList<Menu_Food>();
			while(rs.next()) {
				int i1 = rs.getInt(1);
				int i2 = rs.getInt(2);
				Menu_Food f = new Menu_Food(i1, i2);
				mf.add(f);
			}
		}
		rs.close();
		ps.close();
		c.close();
		return mf;
	}

	@Override
	public int save(Menu_Food t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Menu_Food t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO R2_Menu_Food VALUES (?, ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getMid());
		ps.setInt(2, t.getFid());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

	@Override
	public int update(Menu_Food t) throws SQLException {
	    return 0;
	}
        public void deleteAllFood(int menuID) throws SQLException{
            Connection c = FunctionsDatabase.getConnection();
            String sql = "DELETE FROM R2_Menu_Food WHERE Mid = ? ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, menuID);
            ps.executeUpdate();
            ps.close();
            c.close();
        }
	@Override
	public int delete(Menu_Food t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM R2_Menu_Food WHERE Mid = ? AND Fid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getMid());
		ps.setInt(2, t.getFid());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

    @Override
    public List<String> getAllFoodName(int mid) throws SQLException{
        Connection c = FunctionsDatabase.getConnection();
        List<String> f_name = null;
        String sql = "SELECT * FROM R2_Menu_Food WHERE Mid=?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, mid);
        ResultSet rs = ps.executeQuery();
        if (rs != null) {
            f_name=new ArrayList<String>();
            while(rs.next()) {
		int f_id = rs.getInt(2);
                FoodDAOImpl fImpl = new FoodDAOImpl();
                f_name.add(fImpl.get(f_id).getName());
            }
        }
        return f_name;
    }

}