package cookingNotebook.models;

public class Menu_Ingredient {

	private int mid;
	private int iid;
	private String iname;
	private double iamount;
	private String iunit;
	
	public Menu_Ingredient(int mid, int iid, String iname, double iamount, String iunit) {
		this.mid = mid;
		this.iid = iid;
		this.iname = iname;
		this.iamount = iamount;
		this.iunit = iunit;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public double getIamount() {
		return iamount;
	}

	public void setIamount(double iamount) {
		this.iamount = iamount;
	}

	public String getIunit() {
		return iunit;
	}

	public void setIunit(String iunit) {
		this.iunit = iunit;
	}

	@Override
	public String toString() {
		return "Menu_Ingredient [mid=" + mid + ", iid=" + iid + ", iname=" + iname + ", iamount=" + iamount + ", iunit="
				+ iunit + "]";
	}
	
}