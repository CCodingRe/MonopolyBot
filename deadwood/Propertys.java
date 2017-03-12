package deadwood;

public class Propertys extends Locations{

	int value;
	Players owner = null;
	int rent;
	String group;
	int house_Cost;
	int house_Num;

	Propertys(String n, int loc, int v, int r, String g, int h_Cost){
		super(n, loc);
		setName(n);
		setLocation(loc);
		value = v;
		rent = r;
		group = g;
		house_Cost = h_Cost;
	}


	public void setOwner(Players obj){
		owner = obj;
	}

	public int getValue(){
		return value;
	}
	public int getRent(){
		return rent;
	}
	public String getOwnerName(){
		return owner.getName();
	}
	public Players getOwner(){
		return owner;
	}

}
