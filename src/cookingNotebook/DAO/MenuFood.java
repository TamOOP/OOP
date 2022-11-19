package cookingNotebook.DAO;

public class MenuFood {
	
	private Integer fid;
	private Integer mid;
	
	public MenuFood(Integer fid, Integer mid) {
		this.fid = fid;
		this.mid = mid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	// Testing purpose only
	@Override
	public String toString() {
		return "MenuFood [fid=" + fid + ", mid=" + mid + "]";
	}
	
}
