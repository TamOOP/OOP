package cookingNotebook.models;

public class Menu_Food {

	private Integer mid;
	private Integer fid;
	
	public Menu_Food(Integer mid, Integer fid) {
		this.mid = mid;
		this.fid = fid;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	@Override
	public String toString() {
		return "Menu_Food [mid=" + mid + ", fid=" + fid + "]";
	}
	
}