package cookingNotebook.DAO;

import cookingNotebook.models.Menu;
import cookingNotebook.models.Menu_Ingredient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAOImpl implements MenuDAO{

	@Override
	public Menu get(int id) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		Menu m = null;
		String sql = "SELECT * FROM T4_Menu WHERE Mid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, id);;
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			m = new Menu(rs.getInt(1), rs.getString(2));
		}
		rs.close();
		ps.close();
		c.close();
		return m;
	}
    public Menu get(String name) throws SQLException {
        Connection c = FunctionsDatabase.getConnection();
        Menu m = null;
        String sql = "SELECT * FROM T4_Menu WHERE Mname = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, name);;
        ResultSet rs = ps.executeQuery();
        if (rs != null) {
            m = new Menu(rs.getInt(1), rs.getString(2));
        }
        rs.close();
        ps.close();
        c.close();
        return m;
    }
	@Override
	public List<Menu> getAll() throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		List<Menu> m = null;
		String sql = "SELECT * FROM T4_Menu";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			m = new ArrayList<Menu>();
			while(rs.next()) {
				m.add(new Menu(rs.getInt(1), rs.getString(2)));
			}
		}
		rs.close();
		ps.close();
		c.close();
		return m;
	}

	@Override
	public int save(Menu t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		List<Integer> l = null;
		String sql = "SELECT Mid FROM T4_Menu";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			l = new ArrayList<Integer>();
			while(rs.next()) {
				l.add(rs.getInt(1));
			}
		}
		rs.close();
		ps.close();
		c.close();
		if(l.contains(t.getMid())) update(t);
		else insert(t);
		return 1;
	}

	@Override
	public int insert(Menu t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO T4_Menu(Mname) VALUES (?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getMname());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

	@Override
	public int update(Menu t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "UPDATE T4_Menu SET Mname = ? WHERE Mid=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getMname());
                ps.setInt(2, t.getMid());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

	@Override
	public int delete(Menu t) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM T4_Menu WHERE Mid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getMid());
		int result = ps.executeUpdate();
		ps.close();
		c.close();
		return result;
	}

	@Override
	public List<Menu_Ingredient> getIngredient(int mid) throws SQLException {
		Connection c = FunctionsDatabase.getConnection();
		List<Menu_Ingredient> mil = null;
		String sql = "SELECT * FROM V3_Menu_Ingredient WHERE Mid = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, mid);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			mil = new ArrayList<>();
			while(rs.next()) {
				mil.add(new Menu_Ingredient(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getString(5)));
			}
		}
		rs.close();
		ps.close();
		c.close();
		return mil;
	}

}