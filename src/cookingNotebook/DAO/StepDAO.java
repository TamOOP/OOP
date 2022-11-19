package cookingNotebook.DAO;

import java.sql.SQLException;
import java.util.List;

public interface StepDAO extends DAO<Step> {
	
	public List<Step> getSteps(int fid) throws SQLException;
	
}
