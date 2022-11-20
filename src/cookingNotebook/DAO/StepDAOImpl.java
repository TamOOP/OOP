package cookingNotebook.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cookingNotebook.models.Step;

public class StepDAOImpl implements StepDAO {

	@Override
	public Step get(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Step> getAll() throws SQLException {
		List<Step> steps = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT * FROM Tb3_Step";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			steps = new ArrayList<>();
			while(rs.next()) {
				steps.add(new Step(rs.getInt("FoodID"), rs.getInt("StepIndex"), rs.getString("StepContent"), rs.getString("StepImage")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return steps;
	}

	@Override
	public int insert(Step step) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "INSERT INTO Tb3_Step(FoodID, StepContent, StepImage) VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, step.getIdFood());
		ps.setString(2, step.getContent());
		ps.setString(3, step.getLinkImg());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int update(Step step) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "UPDATE Tb3_Step SET StepContent = ?, StepImage = ? WHERE StepIndex = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, step.getContent());
		ps.setString(2, step.getLinkImg());
		ps.setInt(3, step.getIdStep());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public int save(Step step) throws SQLException {
		List<Integer> idFoods = null;
		List<Integer> idSteps = null;
		Connection con = FunctionsDatabase.getConnection();
		String sql = "SELECT FoodID, StepIndex FROM Tb3_Step";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			idFoods = new ArrayList<Integer>();
			idSteps = new ArrayList<Integer>();
			while(rs.next()) {
				idFoods.add(rs.getInt("FoodID"));
				idSteps.add(rs.getInt("IngrID"));
			}
		}
		rs.close();
		ps.close();
		con.close();
		if(idFoods.contains(step.getIdFood()) && idSteps.contains(step.getIdStep())) update(step);
		else insert(step);
		return 1;
	}

	@Override
	public int delete(Step step) throws SQLException {
		Connection con = FunctionsDatabase.getConnection();
		String sql = "DELETE FROM Tb3_Step WHERE StepIndex = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, step.getIdStep());
		int result = ps.executeUpdate();
		ps.close();
		con.close();
		return result;
	}

	@Override
	public List<Step> getSteps(int idFood) throws SQLException {
		List<Step> steps = null;
		Connection con = FunctionsDatabase.getConnection();	
		String sql = "SELECT * FROM Tb3_Step";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs != null) {
			steps = new ArrayList<Step>();
			while(rs.next()) {
				steps.add(new Step(rs.getInt("FoodID"), rs.getInt("StepIndex"), rs.getString("StepContent"), rs.getString("StepImage")));
			}
		}
		rs.close();
		ps.close();
		con.close();
		return steps;
	}

}
