package deadwood;

public class Propertys extends Locations{

	int value;
	Players owner = null;
	int rent;

	Propertys(String n, int loc, int v, int r){
		super(n, loc);
		setName(n);
		setLocation(loc);
		value = v;
		rent = r;
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
