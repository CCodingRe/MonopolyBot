package deadwood;

public class Propertys extends Locations{

	String inputName;
	int value;
	int mortgageValue;
	Players owner = null;
	int rent;
	String group;
	int house_Cost;
	int house_Num;
	Boolean isMortgaged;

	Propertys(String n, String iN, int loc, int v, int mV, int r, String g, int h_Cost){
		super(n, loc);
		setName(n);
		setInputName(iN); // input name will be used to compare with user inputs when mortgaging, etc.
		setLocation(loc);
		value = v;
		mortgageValue = mV;
		rent = r;
		group = g;
		house_Cost = h_Cost;
		isMortgaged = false;
	}

	public void setInputName(String name) {
		inputName = name;
	}
	public String getInputName() {
		return inputName;
	}
	public void setOwner(Players obj){
		owner = obj;
	}
	public int getValue(){
		return value;
	}
	public int getMortgageValue() {
		return mortgageValue;
	}
	public int getRedeemValue() {
		return (int) (mortgageValue + mortgageValue*.1);
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
	public void mortgage() {
		isMortgaged = true;
	}
	public void redeem() {
		isMortgaged = false;
	}
	public Boolean isMortgaged() {
		return isMortgaged;
	}

}
