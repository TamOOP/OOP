package cookingNotebook.models;

public class Menu_Food {

	private Integer mid;
	private Integer fid;
	private String  tid;
        
	public Menu_Food(Integer mid, Integer fid, String tid) {
		this.mid = mid;
		this.fid = fid;
                this.tid = tid;
	}
        public Menu_Food(){
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
        public String getTid() {
		return tid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
        public void setTid(String tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "Menu_Food [mid=" + mid + ", fid=" + fid + "]";
	}
	
}