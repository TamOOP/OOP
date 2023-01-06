package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

import cookingNotebook.models.Step;

public interface StepDAO extends DAO<Step>{
	public List<Step> getSteps(int idFood) throws SQLException;

	public static StepDAO createInstance() {
		return new StepDAOImpl();
	}
}
