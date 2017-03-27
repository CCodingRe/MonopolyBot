package deadwood;

public class Propertys extends Locations{

	String inputName;
	int value;
	int mortgageValue;
	Players owner = null;
	int[] rent = new int[6];
	String group;
	int house_Cost;
	int units = 0;
	Boolean isMortgaged;

	Propertys(String n, String iN, int loc, int v, int mV, int rOrg, int r1, int r2, int r3, int r4, int r5, String g, int h_Cost){
		super(n, loc);
		setName(n);
		setInputName(iN); // input name will be used to compare with user inputs when mortgaging, etc.
		setLocation(loc);
		value = v;
		mortgageValue = mV;
		rent[0] = rOrg;
		rent[1] = r1;
		rent[2] = r2;
		rent[3] = r3;
		rent[4] = r4;
		rent[5] = r5;
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
		return rent[units];
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
	public int getUnits(){
		return units;
	}
	public void addUnits(int n){
		units += n;
		owner.deductBalance(house_Cost*n);
	}
	public void removeUnits(int n){
		units -= n;
		owner.addBalance((int) (house_Cost*n*0.5));
	}
	public String getGroup(){
		return group;
	}

}
