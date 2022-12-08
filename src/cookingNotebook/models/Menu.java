package cookingNotebook.models;

public class Menu {

	private Integer mid;
	private String mname;
	
	public Menu(Integer mid, String mname) {
		this.mid = mid;
		this.mname = mname;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mnane) {
		this.mname = mnane;
	}

	@Override
	public String toString() {
		return "Menu [mid=" + mid + ", mnane=" + mname + "]";
	}
	
}