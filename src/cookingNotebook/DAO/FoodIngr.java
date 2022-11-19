package cookingNotebook.DAO;

public class FoodIngr {
	
	private Integer fid;
	private Integer iid;
	private Double amount;
	
	public FoodIngr(Integer fid, Integer iid, Double amount) {
		this.fid = fid;
		this.iid = iid;
		this.amount = amount;
	}

    public FoodIngr() {
         }

	public Integer getFid() {
		return fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getIid() {
		return iid;
	}

	public void setIid(Integer iid) {
		this.iid = iid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	// Testing purpose only
	@Override
	public String toString() {
		return "FoodIngr [fid=" + fid + ", iid=" + iid + ", amount=" + amount + "]";
	}
	
}
