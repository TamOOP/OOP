package cookingNotebook.models;

import java.util.ArrayList;
import java.util.List;

public class Menu_Ingredient {

	public int mid;
	public  List<Integer> iid= new ArrayList<>();
	public  List<String> iname=new ArrayList<>();
	public  List<String> iamount=new ArrayList<>();
	public  List<String> iunit=new ArrayList<>();
        
	public int getMid() {
		return mid;
	}
        public void setMid(int id){
            mid = id;
        }
	public List<Integer> getIid() {
		return this.iid;
	}
	public List<String> getIname() {
		return iname;
	}
	public List<String> getIamount() {
		return iamount;
	}
	public List<String> getIunit() {
		return iunit;
	}


	@Override
	public String toString() {
		return "Menu_Ingredient [mid=" + mid + ", iid=" + iid + ", iname=" + iname + ", iamount=" + iamount + ", iunit="
				+ iunit + "]";
	}
	
}